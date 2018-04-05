package com.example.kod45.bookapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.kod45.bookapplication.R;
import com.example.kod45.bookapplication.entity.Book;
import com.example.kod45.bookapplication.entity.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

public class AddBookActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;

    private EditText titleText, authorText, priceText, quantityText;
    private Button mAddButton, chooseButton;
    private ImageView imageView;
    private Uri filePath;
    private Spinner mSpinner;
    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    Book book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        titleText = (EditText)findViewById(R.id.TitleAdd);
        authorText = (EditText)findViewById(R.id.AuthorAdd);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        priceText = (EditText)findViewById(R.id.PriceAdd);
        quantityText = (EditText)findViewById(R.id.QuantityAdd);

        mAddButton = (Button)findViewById(R.id.addBook);
        chooseButton = (Button)findViewById(R.id.chooseImage);
        imageView = (ImageView)findViewById(R.id.imageView);

        final String[] category = getResources().getStringArray(R.array.categories);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, category);
        mSpinner.setAdapter(arrayAdapter);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Book");

        mAddButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String bookID = mFirebaseDatabase.push().getKey();
                final String title = titleText.getText().toString();
                final String author = authorText.getText().toString();
                final String price = priceText.getText().toString();

                int quantity = Integer.parseInt(quantityText.getText().toString());
                final String selectedCategory = mSpinner.getSelectedItem().toString();

                if(title == null)
                {
                    Toast.makeText(AddBookActivity.this, "Please enter in a Title", Toast.LENGTH_SHORT).show();
                }
                else if (author == null)
                {
                    Toast.makeText(AddBookActivity.this, "Please enter in author", Toast.LENGTH_SHORT).show();
                }
                else if (price == null)
                {
                    Toast.makeText(AddBookActivity.this, "Please enter in a price", Toast.LENGTH_SHORT).show();
                }
                else if (quantity <= 0)
                {
                    Toast.makeText(AddBookActivity.this, "Please enter in a quantity", Toast.LENGTH_SHORT).show();
                }

                else
                {

                }
                final String finalQuantity = Integer.toString(quantity);


                StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                @SuppressWarnings("VisibleForTests")
                                Uri uri = taskSnapshot.getDownloadUrl();
                                String imagePath = uri.toString();
                                Toast.makeText(AddBookActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                book = new Book(title, author, selectedCategory, imagePath, price, finalQuantity);
                                mFirebaseDatabase.child(bookID).setValue(book);
                                Toast.makeText(getApplicationContext(), "Book Added", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddBookActivity.this, AdminWelcomePage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        });


            }
        });

        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

    }

    private void chooseFile()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {

            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);

                StorageReference reference = storageReference.child("images").child(filePath.getLastPathSegment());
                reference.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(AddBookActivity.this, "Uploaded", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }

}
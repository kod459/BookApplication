package com.example.kod45.bookapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kod45.bookapplication.R;
import com.example.kod45.bookapplication.entity.Book;
import com.example.kod45.bookapplication.entity.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;

    private EditText titleText, authorText, categoryText, priceText, quantityText;
    private Button mAddButton;
    private Spinner mSpinner;
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

        final String[] category = getResources().getStringArray(R.array.categories);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, category);
        mSpinner.setAdapter(arrayAdapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Book");

        mAddButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String bookID = mFirebaseDatabase.push().getKey();
                String title = titleText.getText().toString();
                String author = authorText.getText().toString();
                String price = priceText.getText().toString();

                int quantity = Integer.parseInt(quantityText.getText().toString());
                String selectedCategory = mSpinner.getSelectedItem().toString();

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
                String finalQuantity = Integer.toString(quantity);

                book = new Book(title, author, selectedCategory, finalQuantity, price);
                mFirebaseDatabase.child(bookID).setValue(book);
                Toast.makeText(getApplicationContext(), "Book Added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddBookActivity.this, AdminWelcomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}

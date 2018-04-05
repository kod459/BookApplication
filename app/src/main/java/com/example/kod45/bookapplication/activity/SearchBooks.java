package com.example.kod45.bookapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.kod45.bookapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchBooks extends AppCompatActivity {

    EditText searchBook;
    RecyclerView recyclerView;
    DatabaseReference mDatabase;
    DatabaseReference mFirebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    ArrayList<String> titleList;
    ArrayList<String> authorList;
    ArrayList<String> categoryList;
    ArrayList<String> priceList;
    ArrayList<String> quantityList;
    ArrayList <String> bookImageList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        searchBook = (EditText) findViewById(R.id.searchBook);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        titleList = new ArrayList<>();
        authorList = new ArrayList<>();
        categoryList = new ArrayList<>();
        priceList = new ArrayList<>();
        quantityList = new ArrayList<>();
        bookImageList = new ArrayList<>();

        searchBook.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    setAdapter(s.toString());
                } else {
                    titleList.clear();
                    authorList.clear();
                    categoryList.clear();
                    priceList.clear();
                    quantityList.clear();
                    bookImageList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });

    }

    private void setAdapter(final String searchedString) {
        mDatabase.child("Book").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear the list for each new search
                titleList.clear();
                authorList.clear();
                categoryList.clear();
                priceList.clear();
                quantityList.clear();
                bookImageList.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String uid = ds.getKey();
                    String title = ds.child("title").getValue(String.class);
                    String author = ds.child("author").getValue(String.class);
                    String category = ds.child("category").getValue(String.class);
                    String price = ds.child("price").getValue(String.class);
                    String quantity = ds.child("quantity").getValue(String.class);
                    String image = ds.child("image").getValue(String.class);

                    if (title.contains(searchedString)){
                        titleList.add(title);
                        authorList.add(author);
                        categoryList.add(category);
                        priceList.add(price);
                        quantityList.add(quantity);
                        bookImageList.add(image);
                        counter++;
                    } else if (author.contains(searchedString)) {
                        titleList.add(title);
                        authorList.add(author);
                        categoryList.add(category);
                        priceList.add(price);
                        quantityList.add(quantity);
                        bookImageList.add(image);
                        counter++;
                    } else if (category.contains(searchedString)){
                        titleList.add(title);
                        authorList.add(author);
                        categoryList.add(category);
                        priceList.add(price);
                        quantityList.add(quantity);
                        bookImageList.add(image);
                        counter++;
                    }
                    if(counter == 15){
                        break;
                    }

                    searchAdapter = new SearchAdapter(SearchBooks.this, titleList, authorList, categoryList, priceList, quantityList, bookImageList);
                    recyclerView.setAdapter(searchAdapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
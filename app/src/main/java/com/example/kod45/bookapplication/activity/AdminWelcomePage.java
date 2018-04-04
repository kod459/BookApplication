package com.example.kod45.bookapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kod45.bookapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminWelcomePage extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button signOut, addBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome_page);
        mAuth = FirebaseAuth.getInstance();

        signOut = (Button)findViewById(R.id.button3);
        addBook = (Button)findViewById(R.id.GoToAdd);

        signOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(getApplicationContext(), "Signing out...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminWelcomePage.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        addBook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminWelcomePage.this, AddBookActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




    }
}


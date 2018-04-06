package com.example.kod45.bookapplication.entity;

import android.app.Application;

/**
 * Created by kod45 on 06/04/2018.
 */
public class GlobalVariables extends Application{

    String currentBook;

    public String getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(String currentBook) {
        this.currentBook = currentBook;
    }
}
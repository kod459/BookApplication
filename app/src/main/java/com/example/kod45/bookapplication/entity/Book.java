package com.example.kod45.bookapplication.entity;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by kod45 on 26/03/2018.
 */

@IgnoreExtraProperties
public class Book {

    private String title, author, category, image, price, quantity;

    public Book(String title, String author, String category, String image, String price, String quantity) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public Book()
    {

    }

    public Book(String title, String author, String category, String quantity, String price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public Book(String title, String author, String category, String price) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

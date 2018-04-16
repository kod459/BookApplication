package com.example.kod45.bookapplication.entity;

/**
 * Created by kod45 on 06/04/2018.
 */public class Comment {

    String userName, bookTitle, rating, comment, bookId;

    public Comment(){}

    public Comment(String userName, String bookTitle, String rating, String comment, String bookId) {
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.rating = rating;
        this.comment = comment;
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
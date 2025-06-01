package com.dto;

public class Book {
    private int id;
    private int sr_no;
    private String BookName;
    private String AuthorName;
    private int qty;

    public int getId() {
        return id;
    }

    public int getSr_no() {
        return sr_no;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public int getQty() {
        return qty;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}

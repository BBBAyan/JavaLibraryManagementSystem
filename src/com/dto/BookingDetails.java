package com.dto;

public class BookingDetails {
    private int id;
    private int std_id;
    private int book_id;
    private int qty;

    private int sr_no;
    private String BookName;
    private String AuthorName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
}

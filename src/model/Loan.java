package model;

import common.Identifiable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Loan implements Identifiable, Serializable {
    private final String id;
    private final String userEmail;
    private final String bookId;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(String userEmail, String bookId) {
        id = UUID.randomUUID().toString();
        this.userEmail = userEmail;
        this.bookId = bookId;
        borrowDate = LocalDate.now();
        dueDate = borrowDate.plusWeeks(2);
        returnDate = null;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public boolean isOverDue() {
        return isActive() && LocalDate.now().isAfter(dueDate);
    }

    public void markAsReturned() {
        this.returnDate = LocalDate.now();
    }

    @Override
    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id='" + id + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", bookId='" + bookId + '\'' +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

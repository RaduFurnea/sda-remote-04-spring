package ro.sda.boot.intro.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String author;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 64)
    private String publisher;

    public Book(String name, String author, BigDecimal price, String publisher) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.publisher = publisher;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(author, book.author) &&
                Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, price);
    }
}

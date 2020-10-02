package ro.sda.boot.intro.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "library")
public class Library extends BaseEntity {

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 64)
    private String street;

    @Column(nullable = false, length = 32)
    private String city;

    @Column(nullable = false, length = 32)
    private String postcode;

    public Library(String name, String street, String city, String postcode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
    }

    public Library() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(name, library.name) &&
                Objects.equals(street, library.street) &&
                Objects.equals(city, library.city) &&
                Objects.equals(postcode, library.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, city, postcode);
    }
}

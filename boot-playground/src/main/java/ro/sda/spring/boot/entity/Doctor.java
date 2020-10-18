package ro.sda.spring.boot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Doctor extends BaseEntity {

    @Column(nullable = false, length = 32)
    @NotBlank(message = "First name cannot be blank.")
    private String firstName;

    @Column(nullable = false, length = 32)
    @NotBlank(message = "Last name cannot be blank.")
    private String lastName;

    @Column(nullable = true, length = 128)
    private String street;

    @Column(nullable = true)
    private Long streetNr;

    @Column(nullable = true, length = 32)
    private String postCode;

    @Column(nullable = false, length = 64)
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email not valid.")
    private String email;

    @Column(nullable = false)
    @NotNull(message = "Date of birth cannot be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Patient> patients = new ArrayList();

    public Doctor(String firstName, String lastName, String street, Long streetNr, String postCode, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNr = streetNr;
        this.postCode = postCode;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public Doctor() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getStreetNr() {
        return streetNr;
    }

    public void setStreetNr(Long streetNr) {
        this.streetNr = streetNr;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", streetNr=" + streetNr +
                ", postCode='" + postCode + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) &&
                Objects.equals(firstName, doctor.firstName) &&
                Objects.equals(lastName, doctor.lastName) &&
                Objects.equals(street, doctor.street) &&
                Objects.equals(streetNr, doctor.streetNr) &&
                Objects.equals(postCode, doctor.postCode) &&
                Objects.equals(email, doctor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, street, streetNr, postCode, email);
    }
}

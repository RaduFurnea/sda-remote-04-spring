package ro.sda.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.sda.spring.boot.entity.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByFirstName(String firstName);

    List<Doctor> findByFirstNameOrLastName(String firstName, String lastName);

    List<Doctor> findByStreetNrGreaterThan(Long streetNr);

    // value is an SQL query, so the query will run directly on DB
    @Query(value = "SELECT count(*) from doctor where first_name = :firstName", nativeQuery = true)
    Integer countDoctorsByFirstName(@Param("firstName") String firstName);

    // value is a HQL query, so the query will run on Entity properties
    @Query(value = "SELECT count(d) from Doctor as d where d.lastName = :lastName", nativeQuery = false)
    Integer countDoctorsByLastName(@Param("lastName") String lastName);

    // in case of fetch type LAZY, you can use left join fetch to get the desired relations
    @Query(value = "SELECT d from Doctor as d left join fetch d.patients where d.id = :id")
    Optional<Doctor> findByIdFull(@Param("id") Long id);
}

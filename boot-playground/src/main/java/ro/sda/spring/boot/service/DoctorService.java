package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.repository.DoctorRepository;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostConstruct
    public void init() {
        this.createDefaultDoctors();
        this.getDoctor(2l);
        this.countDoctors();
        this.findAllDoctorsPageable(0, 2);
        System.out.println("------------- next page --------------");
        this.findAllDoctorsPageable(1, 2);
        System.out.println("------------- next page --------------");
        this.findAllDoctorsPageable(2, 2);
        this.findByFirstName("Adrian");
        this.findByFirstNameOrLastName("Bogdan", "Juncu");
        this.findByStreetNumberGreaterThan(1l);
        System.out.println(this.doctorRepository.countDoctorsByFirstName("Adrian"));
        System.out.println(this.doctorRepository.countDoctorsByLastName("Gabor"));
    }

    private void createDefaultDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Adrian", "Bobocel", "Str. Carpenului", 12l, "500412", "a.bobocel@gmail.com"));
        doctors.add(new Doctor("Adrian", "Rotila", "Str. Socului", 45l, "500435", "a.rotila@gmail.com"));
        doctors.add(new Doctor("Bogdan", "Gabor", "Str. Nucului", 5l, "500987", "bogdan.gabor@yahoo.com"));
        doctors.add(new Doctor("Constantin", "Juncu", "Str. Ciresului", 59l, "500654", "c.juncu@yahoo.com"));
        doctors.add(new Doctor("George", "Niculae", "Str. Calea Bucuresti", 255l, "500487", "g.nc12@gmail.com"));
        doctorRepository.saveAll(doctors);
    }

    public List<Doctor> findByStreetNumberGreaterThan(Long id) {
        Doctor doctor = this.getDoctor(id);
        List<Doctor> doctors = doctorRepository.findByStreetNrGreaterThan(doctor.getStreetNr());
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    public List<Doctor> findByFirstName(String firstName) {
        List<Doctor> doctors = doctorRepository.findByFirstName(firstName);
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    public List<Doctor> findByFirstNameOrLastName(String firstName, String lastName) {
        List<Doctor> doctors = doctorRepository.findByFirstNameOrLastName(firstName, lastName);
        doctors.forEach(d -> System.out.println(d.toString()));
        return doctors;
    }

    private List<Doctor> findAllDoctorsPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Doctor> doctors = doctorRepository.findAll(pageable).get().collect(Collectors.toList());
        doctors.forEach(doctor ->
                System.out.println(doctor.toString())
        );
        return doctors;
    }

    private Long countDoctors() {
        System.out.println("There are " + doctorRepository.count() + " doctors.");
        return doctorRepository.count();
    }

    private Doctor getDoctor(Long id) {
        Optional<Doctor> optDoctor = doctorRepository.findById(id);
        if (optDoctor.isPresent()) {
            Doctor doctor = optDoctor.get();
            System.out.println(doctor.toString());
            return doctor;
        } else {
            System.out.println("Doctor with ID " + id + " does not exist.");
            throw new RuntimeException();
        }
    }

}

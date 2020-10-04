package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.repository.DoctorRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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

}

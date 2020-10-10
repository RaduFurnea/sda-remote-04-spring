package ro.sda.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sda.spring.boot.entity.Patient;
import ro.sda.spring.boot.repository.PatientRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {
        Optional<Patient> optPatient = patientRepository.findById(id);
        if (optPatient.isPresent()) {
            return optPatient.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findPatientsWithBirthdayBefore(LocalDate dateBefore) {
        return patientRepository.findByDateOfBirthBefore(dateBefore);
    }
}
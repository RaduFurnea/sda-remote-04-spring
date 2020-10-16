package ro.sda.spring.boot.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.dto.PatientDTO;
import ro.sda.spring.boot.service.PatientService;
import ro.sda.spring.boot.transformer.PatientTransformer;

@RestController
@RequestMapping("/api/patient")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;
    private final PatientTransformer patientTransformer;

    @Autowired
    public PatientController(PatientService patientService, PatientTransformer patientTransformer) {
        this.patientService = patientService;
        this.patientTransformer = patientTransformer;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientTransformer.transformReversed(patientService.findPatientById(id)));
    }

    @PostMapping
    public ResponseEntity<PatientDTO> savePatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientTransformer.transformReversed(patientService.savePatient(patientTransformer.transform(patientDTO))));
    }

    @PutMapping
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientTransformer.transformReversed(patientService.savePatient(patientTransformer.transform(patientDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
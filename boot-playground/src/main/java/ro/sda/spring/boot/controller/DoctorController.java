package ro.sda.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.spring.boot.dto.DoctorDTO;
import ro.sda.spring.boot.dto.PageableDoctorResponseDTO;
import ro.sda.spring.boot.entity.Doctor;
import ro.sda.spring.boot.service.DoctorService;
import ro.sda.spring.boot.transformer.DoctorTransformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    private final DoctorTransformer doctorTransformer;

    @Autowired
    public DoctorController(DoctorService doctorService, DoctorTransformer doctorTransformer) {
        this.doctorService = doctorService;
        this.doctorTransformer = doctorTransformer;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DoctorDTO> findDoctorById(@PathVariable("id") Long id) {
        // gets value from service
        Doctor doctor = doctorService.findDoctorById(id);
        // transform from entity to DTO (data transfer object)
        DoctorDTO doctorDTO = doctorTransformer.transformReversed(doctor);
        // put doctorDTO into the response entity
        return ResponseEntity.ok(doctorDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable("id") Long id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        // construct the required entity object
        Doctor doctor = doctorTransformer.transform(doctorDTO);
        // assign saved entity to new object
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        // transform from entity to DTO (data transfer object)
        DoctorDTO savedDoctorDTO = doctorTransformer.transformReversed(savedDoctor);
        // put doctorDTO into the response entity
        return ResponseEntity.ok(savedDoctorDTO);
    }

    @PutMapping
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO) {
        // construct the required entity object
        Doctor doctor = doctorTransformer.transform(doctorDTO);
        // assign saved entity to new object
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        // transform from entity to DTO (data transfer object)
        DoctorDTO savedDoctorDTO = doctorTransformer.transformReversed(savedDoctor);
        // put doctorDTO into the response entity
        return ResponseEntity.ok(savedDoctorDTO);
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getByFirstName(@RequestParam(value = "first-name") String firstName) {
        List<Doctor> doctors = doctorService.findByFirstName(firstName);
        List<DoctorDTO> doctorDTOS = doctors.stream().map(d -> doctorTransformer.transformReversed(d)).collect(Collectors.toList());
        return ResponseEntity.ok(doctorDTOS);
    }

    @GetMapping(path = "/pageable")
    public ResponseEntity<PageableDoctorResponseDTO> getDoctorsPageable(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        List<Doctor> doctors = doctorService.findAllDoctorsPageable(page, size);
        List<DoctorDTO> doctorDTOS = doctors.stream().map(doctorTransformer::transformReversed).collect(Collectors.toList());

        // create pageable response
        PageableDoctorResponseDTO responseDTO = new PageableDoctorResponseDTO();
        responseDTO.setDoctors(doctorDTOS);
        responseDTO.setPage(page);
        responseDTO.setSize(size);
        responseDTO.setTotal(doctorService.countDoctors());

        return ResponseEntity.ok(responseDTO);
    }


    // this is just a method that exemplifies some stream methods, is not in fact an endpoint mapping
    private void streamExamples() {
        List<Doctor> doctors = doctorService.findByFirstName("Adrian");

        // filter filters the stream based on the condition given
        // findFirst and findAny returns an optional with a single value of the stream
        Optional<Doctor> optDoc = doctors.stream().filter(d -> d.getId().equals(1l)).findFirst();

        // map changes the type of the elements in stream
        // collect takes all values remaining in the stream and converts it to the specified collection (list in below case)
        List<Long> list = doctors.stream().map(d -> d.getId()).filter(l -> l.equals(1l)).collect(Collectors.toList());
        List<Long> list2 = doctors.stream().map(Doctor::getId).filter(l -> l.equals(1l)).collect(Collectors.toList());

        // anyMatch is a terminal operator that returns true if any of the elements in the stream match the given condition, false otherwise.
        boolean b = doctors.stream().anyMatch(d -> d.getId().equals(8l));
    }

}
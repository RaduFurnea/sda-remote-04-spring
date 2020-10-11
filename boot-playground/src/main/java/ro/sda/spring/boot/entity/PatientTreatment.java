package ro.sda.spring.boot.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class PatientTreatment {

    @EmbeddedId
    private PatientTreatmentKey patientTreatmentKey;

    @ManyToOne
    @MapsId("patientId")
    private Patient patient;

    @ManyToOne
    @MapsId("treatmentId")
    private Treatment treatment;

    private LocalDate treatmentUntil;

    private String notes;

    private Integer numberOfMeds;

}

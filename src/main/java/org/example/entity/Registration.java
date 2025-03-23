package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "registration")
public class Registration implements SuperEntity {
   @EmbeddedId
   private RegistrationId id;

   @ManyToOne
   @MapsId("patientId")
   @JoinColumn(name = "patient_id")
   private Patients patient;

   @ManyToOne
   @MapsId("programId")
   @JoinColumn(name = "program_id")
   private TherapyProgram program;

    private String date;
    private String time;

}

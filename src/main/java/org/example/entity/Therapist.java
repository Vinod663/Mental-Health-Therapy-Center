package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "therapist")
public class Therapist implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "therapist_id")
    private int id;

    private String name;
    private String email;
    private String phone;
    private String specialization;

    @OneToMany(mappedBy = "therapist")
    private List<TherapySession> therapySessions;

    @OneToMany(mappedBy = "therapist")
    private List<TherapyDetail> therapyDetails;



}

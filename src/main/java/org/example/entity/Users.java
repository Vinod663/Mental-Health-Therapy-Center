package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data//getter,setter,tostring

@Entity
@Table(name = "user_table")

public class Users implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Patients> patients;

    @OneToMany(mappedBy = "user")
    private List<TherapyProgram> therapyPrograms;
}

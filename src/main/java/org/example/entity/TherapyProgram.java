package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "program")
public class TherapyProgram implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private int programId;

    @Column(name = "program_name")
    private String programName;

    private String duration;

    @Column(name = "program_cost", precision = 10, scale = 2)
    private BigDecimal programCost;

    @Column(name = "program_description")
    @Lob
    private String programDescription;

   /* @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;*/

    @OneToMany(mappedBy = "program")
    private List<Registration> registrations;

    @OneToMany(mappedBy = "program")
    private List<Payment> payments;

    @OneToMany(mappedBy = "program")
    private List<TherapyDetail> therapyDetails;


}

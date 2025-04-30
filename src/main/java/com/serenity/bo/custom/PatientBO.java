package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.PatientDto;
import com.serenity.view.tdm.PatientTm;

import java.sql.SQLException;
import java.util.List;

public interface PatientBO extends SuperBO {
    String getNextPatientId() throws SQLException;
    boolean savePatient(PatientDto patientDTO) throws SQLException;
    List<PatientTm> getAllPatients() throws SQLException;
    PatientDto getPatientById(int id) throws SQLException;
    boolean deletePatient(int id) throws SQLException;
    boolean updatePatient(PatientDto patientDTO) throws SQLException;

    int getRemainingSessions(int patientId);
}

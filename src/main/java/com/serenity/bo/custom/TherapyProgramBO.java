package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.TherapyProgramDto;

import java.sql.SQLException;
import java.util.List;

public interface TherapyProgramBO extends SuperBO {
    boolean exists(String programId) throws SQLException;
    boolean save(TherapyProgramDto programDto) throws Exception;
    List<TherapyProgramDto> getAll() throws Exception;
    boolean update(TherapyProgramDto programDto) throws SQLException;
    boolean deleteByPK(String programId) throws SQLException;
    public TherapyProgramDto getProgram(String programId) throws SQLException;

    List<TherapyProgramDto> getAllProgramOptions(String patientId);
}

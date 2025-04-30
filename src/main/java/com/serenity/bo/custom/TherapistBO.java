package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.TherapistDto;


import java.sql.SQLException;
import java.util.List;


public interface TherapistBO extends SuperBO {
    boolean save(TherapistDto therapistDto) throws Exception;
    boolean update(TherapistDto therapistDto) throws SQLException;
    List<TherapistDto> getAll() throws Exception;
    boolean deleteByPK(String therapistId) throws SQLException;
    List<TherapistDto> getAllTherapistOptions(String patientId) throws Exception;
}

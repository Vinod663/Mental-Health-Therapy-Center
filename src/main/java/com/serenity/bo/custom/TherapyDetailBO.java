package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.TherapyDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface TherapyDetailBO extends SuperBO {
    boolean save(TherapyDetailDto therapyDetailDto) throws Exception;
    List<TherapyDetailDto> getAll() throws Exception;
    boolean update(TherapyDetailDto therapyDetailDto) throws SQLException;
    boolean deleteByPK(int therapistId, String programId) throws SQLException;
}

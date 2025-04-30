package com.serenity.dao.custom;

import com.serenity.dao.CrudDAO;
import com.serenity.entity.TherapyProgram;

import java.sql.SQLException;

public interface TherapyProgramDAO extends CrudDAO<TherapyProgram,String> {
    boolean exists(String programId) throws SQLException;
    TherapyProgram getProgram(String programId) throws SQLException;
}

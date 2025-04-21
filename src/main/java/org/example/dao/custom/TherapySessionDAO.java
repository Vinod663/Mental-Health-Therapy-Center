package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.TherapySession;
import org.example.entity.TherapySessionId;
import org.hibernate.Session;

import java.sql.SQLException;

public interface TherapySessionDAO extends CrudDAO<TherapySession, TherapySessionId> {
    boolean isSessionConflict(int patientId, int therapistId, String date, String time);
    boolean save(TherapySession therapySession, Session session) throws SQLException;
}

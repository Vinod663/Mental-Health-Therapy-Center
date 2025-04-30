package com.serenity.dao.custom;

import com.serenity.dao.CrudDAO;
import com.serenity.entity.TherapySession;
import com.serenity.entity.TherapySessionId;
import org.hibernate.Session;

import java.sql.SQLException;

public interface TherapySessionDAO extends CrudDAO<TherapySession, TherapySessionId> {
    boolean isSessionConflict(int patientId, int therapistId, String date, String time);

    boolean isSessionConflictOnUpdate(int patientId, int therapistId, String date, String time, TherapySessionId currentSessionId);

    boolean save(TherapySession therapySession, Session session) throws SQLException;

    boolean updateSession(TherapySession therapySession, Session session);
}

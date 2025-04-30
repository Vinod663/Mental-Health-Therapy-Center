package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.TherapySessionDto;
import com.serenity.entity.TherapySessionId;

import java.util.List;

public interface TherapySessionBO extends SuperBO {
    boolean scheduleSession(TherapySessionDto therapySessionDTO, double paymentAmount);
    boolean isSessionConflict(int patientId, int therapistId, String date, String time);
    List<TherapySessionDto> getAllSessions();

    boolean ReScheduleSession(TherapySessionDto sessionDTO, double paymentAmount, TherapySessionId id);

    boolean isSessionConflictUpdate(int patientId, int therapistId, String date, String time, TherapySessionId id);
}

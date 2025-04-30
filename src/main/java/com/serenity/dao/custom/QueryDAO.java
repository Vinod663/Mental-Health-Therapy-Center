package com.serenity.dao.custom;

import com.serenity.dao.SuperDAO;
import com.serenity.entity.Therapist;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Therapist> getTherapistsByPatientId(String patientId) throws Exception;
}

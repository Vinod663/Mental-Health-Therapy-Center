package com.serenity.dao.custom;

import com.serenity.dao.CrudDAO;
import com.serenity.entity.Therapist;
import org.hibernate.Session;

public interface TherapistDAO extends CrudDAO<Therapist,String> {

    Therapist get(int therapistId, Session session);
}

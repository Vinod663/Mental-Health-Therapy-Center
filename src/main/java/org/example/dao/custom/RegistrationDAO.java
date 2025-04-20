package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Registration;
import org.example.entity.RegistrationId;
import org.hibernate.Session;

import java.sql.SQLException;

public interface RegistrationDAO extends CrudDAO<Registration, RegistrationId> {
    boolean save(Registration registration, Session session) throws SQLException;

    Registration get(int id, Session session) throws SQLException;

    boolean update(Registration registration, Session session) throws SQLException;

    void delete(Registration existingRegistration, Session session);
}

package org.example.dao;

import org.example.dao.custom.impl.LoginDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        return switch(daoType){
            case LOGIN -> (T) new LoginDAOImpl();
            /*default -> null;*/
        };
    }
}

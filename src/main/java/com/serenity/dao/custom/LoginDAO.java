package com.serenity.dao.custom;

import com.serenity.dao.CrudDAO;
import com.serenity.entity.Users;

public interface LoginDAO extends CrudDAO<Users,String> {
    boolean authenticate(String username, String password);
    String getRole();

    boolean isWrongPsw();

    Users getUser();
}

package com.serenity.bo.custom.impl;

import com.serenity.bo.custom.LoginBO;
import com.serenity.dao.DAOFactory;
import com.serenity.dao.DAOTypes;
import com.serenity.dao.custom.LoginDAO;
import com.serenity.dto.UserDto;
import com.serenity.entity.Users;

public class LoginBOImpl implements LoginBO {
    LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOTypes.LOGIN);

    @Override
    public boolean authenticate(String username, String password) {
        try {
            return loginDAO.authenticate(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRole() {
        return loginDAO.getRole();
    }

    @Override
    public boolean isWrongPsw() {
        return loginDAO.isWrongPsw();
    }

    @Override
    public UserDto getUser() {
        Users user = loginDAO.getUser();
        if(user != null){
            return new UserDto(user.getId(), user.getUsername(), user.getFullname(), user.getEmail(), user.getPassword(), user.getRole());
        }
        return null;
    }
}

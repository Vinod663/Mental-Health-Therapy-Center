package com.serenity.bo.custom.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.serenity.bo.custom.UserBO;
import com.serenity.dao.DAOFactory;
import com.serenity.dao.DAOTypes;
import com.serenity.dao.custom.UserDAO;
import com.serenity.dto.UserDto;
import com.serenity.entity.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOTypes.USER);

    @Override
    public List<UserDto> getAllReceptionist() throws Exception {
        List<Users> allReceptionist = userDAO.getAllReceptionist();
        List<UserDto> userDtos = new ArrayList<>();

        for (Users user : allReceptionist) {
            UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getFullname(), user.getEmail(), user.getPassword(), user.getRole());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public boolean save(UserDto userDto) throws Exception {
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setFullname(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(BCrypt.withDefaults().hashToString(12, userDto.getPassword().toCharArray()));
        user.setRole(userDto.getRole());
        return userDAO.save(user);
    }

    @Override
    public boolean update(UserDto userDto) throws SQLException {
        Users user = new Users();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFullname(userDto.getFullName());
        user.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, userDto.getPassword().toCharArray());
            user.setPassword(hashedPassword);
        } else {
            user.setPassword(null);
        }

        return userDAO.update(user);
    }

    @Override
    public boolean deleteByPK(String cusId) {
        try{
            return userDAO.deleteByPK(cusId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

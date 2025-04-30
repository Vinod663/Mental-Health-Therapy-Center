package com.serenity.dao.custom;

import com.serenity.dao.CrudDAO;
import com.serenity.entity.Users;

import java.util.List;

public interface UserDAO extends CrudDAO<Users,String> {
    List<Users> getAllReceptionist() throws Exception;

}

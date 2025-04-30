package com.serenity.bo.custom;

import com.serenity.bo.SuperBO;
import com.serenity.dto.UserDto;

public interface LoginBO extends SuperBO {
    boolean authenticate(String username, String password);
    String getRole();

    boolean isWrongPsw();

    UserDto getUser();
}

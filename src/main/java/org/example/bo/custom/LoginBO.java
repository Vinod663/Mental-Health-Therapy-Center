package org.example.bo.custom;

import org.example.bo.SuperBO;

public interface LoginBO extends SuperBO {
    boolean authenticate(String username, String password);
    String getRole();
}

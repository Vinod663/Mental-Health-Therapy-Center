package org.example.dao.custom.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.example.config.FactoryConfiguration;
import org.example.dao.custom.LoginDAO;
import org.example.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    private String role;

    @Override
    public boolean authenticate(String username, String password) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();

            Query<Users> query = session.createQuery("from Users u where u.username=:username", Users.class);
            query.setParameter("username", username);
            /*query.setParameter("password", BCrypt.withDefaults().hashToString(12, PswField.getText().toCharArray()));*/
            List<Users> list = query.list();

            if (!list.isEmpty()) {
                /*System.out.println(list.get(0).getRole());*/

                /*System.out.println(list.get(0).getPassword());*/
                if(!list.isEmpty() && BCrypt.verifyer().verify(password.toCharArray(), list.get(0).getPassword()).verified) {
                    System.out.println("Login successful!");
                    role = list.get(0).getRole();
                    return true;
                } else {
                    System.out.println("Login failed password incorrect");
                    return false;
                }

            }
            System.out.println("Login failed Username and Password Incorrect");

            session.close();
        } catch (HibernateException e) {
            System.out.println("Login failed Username and Password Incorrect");
            return false;
        }
        return false;
    }

    @Override
    public String getRole() {
        return role;
    }
}

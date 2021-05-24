package com.example.service;

import com.example.dao.UserDaoImpl;
import com.example.model.User;

import java.sql.SQLException;

public class UserService {

    private UserDaoImpl userDao;



    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User getUserVerify(String username, String password)
    {
    	//System.out.println("In getUserVerify");
    	//System.out.println("username: "+ username);
    	
        User ersUser = userDao.getByUsername(username);
        
        
        if(ersUser != null){
            if(ersUser.getPassword().equals(password))
            {
                return ersUser;
            }
        }
        return null;
    }
    
    
}

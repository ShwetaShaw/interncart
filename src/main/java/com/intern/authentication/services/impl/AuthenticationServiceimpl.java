package com.intern.authentication.services.impl;

import com.intern.authentication.services.AuthenticationService;
import com.intern.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceimpl implements AuthenticationService{

    @Autowired
    UserService userservice;

    @Override
    public String login(String username,String password){
        String userid = userservice.getuserbyname(username);
        if(userservice.checkifuserexists(userid) && userservice.getuserDetails(userid).getPassword().equals(password))
        {
            return userid;
        }
        else
        {
            return "null";
        }
    }
}

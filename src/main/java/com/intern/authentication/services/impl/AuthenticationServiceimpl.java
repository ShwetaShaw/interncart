package com.intern.authentication.services.impl;

import com.intern.authentication.config.PasswordEncrypt;
import com.intern.authentication.services.AuthenticationService;
import com.intern.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceimpl implements AuthenticationService{

    @Autowired
    UserService userservice;

    @Autowired
    PasswordEncrypt encryptedpassword;

    @Override
    public String login(String username,String password){
        try{
            String userid = userservice.getuserbyname(username);
            if(encryptedpassword.checkPassword(password,userservice.getuserDetails(userid).getPassword()))
            {
                return userid;
            }
            else
            {
                return "null";
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }

    }
}

package com.intern.authentication.services.impl;

import com.intern.authentication.config.PasswordEncrypt;
import com.intern.authentication.entity.User;
import com.intern.authentication.repository.UserRepository;
import com.intern.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRES_NEW)
public class UserServiceimpl implements UserService {

    @Autowired
    UserRepository userrepository;

    @Autowired
    PasswordEncrypt passwordEncrypt;


    @Override
    @Transactional(readOnly = false)
    public User save(User user){
        user.setPassword(passwordEncrypt.hashPassword(user.getPassword()));
        return userrepository.save(user);
    }

    @Override
    public String getuserbyname(String username){
        try{
            return userrepository.findByUserName(username).getUserId();
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public User getuserDetails(String userId)
    {
        return userrepository.findOne(userId);
    }

    @Override
    public boolean checkifuserexists(String userId){
        try {
            return userrepository.exists(userId);
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Iterable<User> getAllUsers(){
        return userrepository.findAll();
    }
}
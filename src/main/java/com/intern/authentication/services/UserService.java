package com.intern.authentication.services;

import com.intern.authentication.entity.User;

public interface UserService {
    String getuserbyname(String username);
    User getuserDetails(String userId);
    User save(User userentity);
    boolean checkifuserexists(String userId);
    Iterable<User> getAllUsers();
}

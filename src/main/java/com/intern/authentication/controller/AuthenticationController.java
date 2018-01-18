package com.intern.authentication.controller;

import com.intern.authentication.dto.LoginDTO;
import com.intern.authentication.dto.UserDTO;
import com.intern.authentication.services.AuthenticationService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<JSONObject> login(@RequestBody LoginDTO userobject)
    {
        JSONObject user = new JSONObject();
        String uid = authenticationService.login(userobject.getUsername(),userobject.getPassword());
        user.put("uid",uid);
        if (uid != "null")
        {
            return new ResponseEntity<JSONObject>(user, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<JSONObject>(user, HttpStatus.UNAUTHORIZED);
        }
    }

}

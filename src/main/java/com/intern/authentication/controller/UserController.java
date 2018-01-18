package com.intern.authentication.controller;

import com.intern.authentication.dto.UserDTO;
import com.intern.authentication.entity.User;
import com.intern.authentication.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/addorupdate")
    public ResponseEntity<String> addOrUpdateuser(@RequestBody UserDTO userDTO)
    {
        if(userService.getuserbyname(userDTO.getUserName())!=null)
        {
            return new ResponseEntity<String>("User Exists",HttpStatus.NOT_ACCEPTABLE);
        }
        else
        {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            User userCreated = userService.save(user);
            return new ResponseEntity<String>("{ \"uid\": \"" +userCreated.getUserId()+"\" }",HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOne")
    public ResponseEntity<?> getOne(@RequestParam("uid") String userId){
        User user = userService.getuserDetails(userId);
        UserDTO userDTO = new UserDTO();
        if(user == null){
            return new ResponseEntity<String>("", HttpStatus.OK);
        }
        BeanUtils.copyProperties(user,userDTO);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

}

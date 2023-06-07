package com.providermanagement.businessuser.controller;

import com.providermanagement.businessuser.entity.BusinessUser;
import com.providermanagement.businessuser.service.BusinessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/provider-management/businessUser")
public class BusinessUserController {

    @Autowired
    private BusinessUserService businessUserService;

    @PostMapping("/register")
    public List<String> registerUser(@RequestBody BusinessUser signUp) throws ParseException {


        BusinessUser user = new BusinessUser();
        String registration = signUp.getEmailId();
        String status = businessUserService.findByBusinessUser(registration);
        if(status==null) {
            user.setFirstName(signUp.getFirstName());
            user.setLastName(signUp.getLastName());
            user.setPassword(signUp.getPassword());
            user.setAddress(signUp.getAddress());
            user.setEmailId(signUp.getEmailId());
            user.setContactNo(signUp.getContactNo());
            user.setDob(signUp.getDob());
            user.setUserId(signUp.getUserId());

            BusinessUser businessUser = businessUserService.save(user);
            String userId = businessUser.getUserId();
            List<String> list = new ArrayList<String>();
            list.add("User registered successfully, login with the <"+userId+"> and the provided password");
            return list;
        }
        else {
            List<String> list = new ArrayList<String>();
            list.add("User already registered, please login ");
            return list;
        }



    }

    @PostMapping("/login")
    public List<String> login(@RequestBody BusinessUser login) {
        String username = login.getUserId();
        String password = login.getPassword();
        String pwd = businessUserService.authenticateUser(username, password);
        List<String> list = new ArrayList<String>();

        if (pwd != null) {
            if (pwd.equals(password)) {
                list.add("User logged in successfully");
                return list;

            } else {
                list.add("failed to login");
                return list;
            }
        } else {
            list.add("Username not exists");
            return list;
        }

    }


    @GetMapping("/getBusinessUsers")
    public List<BusinessUser> getCustomers() {
        return businessUserService.getAllBusinessUsers();
    }

    @GetMapping("/getBusinessUser/{id}")
    public Optional<BusinessUser> getBusinessUserByID(@PathVariable int id) {
        return businessUserService.getBusinessUserById(id);
    }

}

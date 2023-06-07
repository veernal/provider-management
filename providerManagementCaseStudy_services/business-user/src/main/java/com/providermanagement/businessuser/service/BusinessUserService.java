package com.providermanagement.businessuser.service;

import com.providermanagement.businessuser.entity.BusinessUser;
import com.providermanagement.businessuser.repository.BusinessUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessUserService {
    @Autowired
    private BusinessUserRepository repo;

    public BusinessUser save(BusinessUser user) throws ParseException {
    String firstName = user.getFirstName();
    String dob = user.getDob();
        Date date = null;
        try {
            date = StringToDate(dob);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("ddMM");
    String strDob = formatter.format(date);
    user.setUserId(firstName.concat(strDob));

        return repo.save(user);
    }

    public String authenticateUser(String username, String password) {
        String pwd = repo.findByName(username);

        return pwd;
    }

    public List<BusinessUser> getAllBusinessUsers() {
        return repo.findAll();
    }

    public String findByBusinessUser(String registration) {
        return repo.findByEmail(registration);
    }

    public Optional<BusinessUser> getBusinessUserById(int id) {
        return repo.findById(id);
    }

    public static Date StringToDate(String dob) throws ParseException {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //Parsing the given String to Date object
        Date date = formatter.parse(dob);
        System.out.println("Date object value: "+date);
        return date;
    }
}

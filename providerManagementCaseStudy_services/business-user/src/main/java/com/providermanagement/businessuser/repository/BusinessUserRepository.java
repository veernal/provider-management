package com.providermanagement.businessuser.repository;

import com.providermanagement.businessuser.entity.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUserRepository extends JpaRepository<BusinessUser,Integer> {

    @Query("select a.password from BusinessUser a where a.userId=:username")
    public String findByName(String username);

    @Query("select a.password from BusinessUser a where a.emailId=:emailId")
    public String findByEmail(String emailId);

}

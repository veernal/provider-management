package com.providermanagement.provider.repository;

import com.providermanagement.provider.entity.Address;
import com.providermanagement.provider.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {


    @Query("select D from Provider D where D.userId=:userId and D.id=:providerId")
    Optional<Provider> fetchProvider(String userId, int providerId);

    @Query("select p from Provider p where CONCAT(p.firstName, p.lastName, p.dba) LIKE %?1% AND CONCAT(p.address.city, p.address.state, p.address.zip) LIKE %?2% " +
            "AND p.classification like ?3 AND p.gender like ?4 AND p.active like ?5")
    List<Provider> searchProvider(String keyword, String anotherKeyword, String classification, String gender, String active);




}

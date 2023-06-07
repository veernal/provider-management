package com.providermanagement.provider.repository;

import com.providermanagement.provider.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select D from Address D where D.id=:addressId")
    Optional<Address> fetchAddress(int addressId);
}

package com.providermanagement.provider.client;

import com.providermanagement.provider.entity.BusinessUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "businessUser", url = "http://localhost:9091")
public interface BusinessUserClient {
    @GetMapping("/api/provider-management/businessUser/getBusinessUser/{id}")
    Optional<BusinessUser> getBusinessUserById(@PathVariable int id);


}

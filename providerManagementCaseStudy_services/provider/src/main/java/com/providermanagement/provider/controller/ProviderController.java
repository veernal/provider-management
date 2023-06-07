package com.providermanagement.provider.controller;

import com.providermanagement.provider.entity.Address;
import com.providermanagement.provider.entity.Provider;
import com.providermanagement.provider.exception.ProviderException;
import com.providermanagement.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/provider-management/provider")
public class ProviderController {
    @Autowired
    private ProviderService providerService;

    @PostMapping("/{id}/addProvider")
    public Provider createRequest(@PathVariable int id, @RequestBody Provider provider) throws ProviderException {
        Provider providerDetails = providerService.addProvider(id, provider);
        return providerDetails;

    }

    @GetMapping("/getAllProviders")
    public List<Provider> getListOfProviders() {
        return providerService.getAllProviders();
    }

    @PutMapping("/{userId}/updateProvider/{providerId}")
    public Provider updateProvider(@PathVariable String userId, @PathVariable int providerId, @RequestBody Provider provider) throws ProviderException {
        Provider providerDetails = providerService.updateProvider(userId, providerId, provider);
        return providerDetails;
    }

    @GetMapping("/search")
    public List<Provider> searchProvider(@Param("keyword") String keyword,
                                         @Param("anotherKeyword") String anotherKeyword,
                                         @RequestParam("classification") String classification,
                                         @RequestParam("gender") String gender,
                                         @RequestParam("active") String active) throws ProviderException {
        return providerService.searchProviders(keyword, anotherKeyword, classification, gender, active);

    }

    @GetMapping("/findProviderById/{providerId}")
    public Optional<Provider> findrequests(@PathVariable int providerId) {
        Optional<Provider> provider =providerService.findProviderById(providerId);
        return provider;
    }

    @PutMapping("/updateAddress/{addressId}")
    public Address updateAddress(@PathVariable int addressId, @RequestBody Address address){
        return providerService.updateAddress(addressId, address);
    }

    @DeleteMapping("/deleteProvider/{userId}/{providerId}")
    public List<String> deleteProvider(@PathVariable String userId, @PathVariable int providerId){
        return providerService.deleteProvider(userId, providerId);
    }
}

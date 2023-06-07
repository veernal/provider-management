package com.providermanagement.provider.service;

import com.providermanagement.provider.client.BusinessUserClient;
import com.providermanagement.provider.entity.Address;
import com.providermanagement.provider.entity.BusinessUser;
import com.providermanagement.provider.entity.License;
import com.providermanagement.provider.entity.Provider;
import com.providermanagement.provider.exception.ProviderException;
import com.providermanagement.provider.repository.AddressRepository;
import com.providermanagement.provider.repository.ProviderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepo;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BusinessUserClient businessUserClient;

    public Provider addProvider(int id, Provider provider) throws ProviderException {
        // TODO Auto-generated method stub

        Optional<BusinessUser> businessUser = businessUserClient.getBusinessUserById(id);

        Provider providerDet = new Provider();
        Address address = new Address();
        License license = new License();
        if (businessUser.isPresent()) {
            if (provider.getProviderType() != null) {
                providerDet.setBusinessUserId(provider.getBusinessUserId());
                providerDet.setUserId(provider.getUserId());
                providerDet.setFirstName(provider.getFirstName());
                providerDet.setLastName(provider.getLastName());
                providerDet.setMiddleName(provider.getMiddleName());
                providerDet.setDba(provider.getDba());
                providerDet.setDob(provider.getDob());
                providerDet.setGender(provider.getGender());
                providerDet.setClassification(provider.getClassification());
                providerDet.setProviderType(provider.getProviderType());
                providerDet.setSpeciality(provider.getSpeciality());
                providerDet.setActive(provider.getActive());

                address.setAddressType(provider.getAddress().getAddressType());
                address.setCity(provider.getAddress().getCity());
                address.setState(provider.getAddress().getState());
                address.setZip(provider.getAddress().getZip());
                address.setStreetAddress(provider.getAddress().getStreetAddress());

                license.setLicenseNumber(provider.getLicense().getLicenseNumber());
                license.setAgency(provider.getLicense().getAgency());
                license.setLicenseType(provider.getLicense().getLicenseType());
                license.setEffectiveDate(provider.getLicense().getEffectiveDate());
                license.setLicenseVerified(provider.getLicense().getLicenseVerified());

                providerDet.setAddress(address);
                providerDet.setLicense(license);
                providerRepo.save(providerDet);
            } else {
                throw new ProviderException("give all details");
            }

        } else {
            throw new ProviderException("invalid BusinessUserId ");
        }
        return providerDet;
    }

    public List<Provider> getAllProviders() {
        return providerRepo.findAll();
    }

    public Provider updateProvider(String userId, int providerId, Provider provider) throws ProviderException {

        Optional<Provider> provider1 = providerRepo.fetchProvider(userId, providerId);
        if (!provider1.isEmpty()) {
            if (provider.getProviderType() != null) {
                provider1.get().setProviderType(provider.getProviderType());
            }
            if (provider.getLastName() != null) {
                provider1.get().setLastName(provider.getLastName());
            }
            if (provider.getDba() != null) {
                provider1.get().setDba(provider.getDba());
            }
            if (provider.getClassification() != null) {
                provider1.get().setClassification(provider.getClassification());
            }
            if (provider.getSpeciality() != null) {
                provider1.get().setSpeciality(provider.getSpeciality());
            }
            if(provider.getActive() !=null){
                provider1.get().setActive(provider.getActive());
            }
        }
        return providerRepo.saveAndFlush(provider1.get());
    }

    public List<Provider> searchProviders(String firstSearchBy, String secondSearchBy, String classification, String gender, String active) throws ProviderException {
        List<Provider> searchList = new ArrayList<Provider>();
        List<Provider> providerList = providerRepo.findAll();
        for(Provider provider: providerList) {
            if (provider.getClassification().equalsIgnoreCase(classification) && provider.getGender().equalsIgnoreCase(gender) && provider.getActive().equalsIgnoreCase(active)) {
                if (!(classification.isEmpty() || classification.isBlank() && gender.isEmpty() || gender.isBlank() && active.isBlank())) {
                    searchList = providerRepo.searchProvider(firstSearchBy, secondSearchBy, classification, gender, active);

                    if (!searchList.isEmpty()) {
                        return searchList;
                    } else {
                        throw new ProviderException("No matching records found");
                    }

                }
                throw new ProviderException("some fields are not present");

            }
            throw new ProviderException("Search keywords are missing");
        }
        return searchList;
    }



    public Address updateAddress(int addressId, Address address) {
        Optional<Address> address1 = addressRepository.fetchAddress(addressId);
        if (!address1.isEmpty()) {
            if (address.getCity() != null) {
                address1.get().setCity(address.getCity());

            }
            if (address.getState() != null) {
                address1.get().setState(address.getState());
            }

            if (address.getAddressType() != null) {
                address1.get().setAddressType(address.getAddressType());
            }

            if(address.getZip()!= null){
                address1.get().setZip(address.getZip());
            }
        }
        return addressRepository.save(address1.get());
    }

    public List<String> deleteProvider(String userId, int providerId) {

        Optional<Provider> provider = providerRepo.fetchProvider(userId, providerId);
        List<String> list = new ArrayList<String>();
        if (!provider.isEmpty() && provider.get().getActive().equalsIgnoreCase("no")) {
            providerRepo.deleteById(provider.get().getId());
            list.add("deleted successfully");
        }else {
            list.add("provider Id not found or the provider is still active");
        }
        return list;


    }


    public Optional<Provider> findProviderById(int providerId) {
        // TODO Auto-generated method stub
        return providerRepo.findById(providerId);
    }
}

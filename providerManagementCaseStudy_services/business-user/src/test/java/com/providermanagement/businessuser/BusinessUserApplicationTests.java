package com.providermanagement.businessuser;

import com.providermanagement.businessuser.entity.BusinessUser;
import com.providermanagement.businessuser.repository.BusinessUserRepository;
import com.providermanagement.businessuser.service.BusinessUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BusinessUserApplicationTests {

	@Autowired
	private BusinessUserService businessUserService;

	@MockBean
	private BusinessUserRepository repo;

	@Test
	void testGetAllBusinessUsers() {
		List<BusinessUser> businessUser = dummyListOfBusinessUser();
		when(repo.findAll()).thenReturn((List<BusinessUser>) businessUser);
		when(businessUserService.getAllBusinessUsers()).thenReturn(businessUser);
		assertNotNull(businessUser);
		assertEquals("bangalore",businessUser.get(0).getAddress());
		assertEquals("veernal",businessUser.get(0).getFirstName());
	}


	private List<BusinessUser> dummyListOfBusinessUser(){
		BusinessUser businessUser = new BusinessUser();
		businessUser.setFirstName("veernal");
		businessUser.setLastName("verma");
		businessUser.setAddress("bangalore");
		List<BusinessUser> list = new ArrayList<>();
		list.add(businessUser);
		return list;
	}



}

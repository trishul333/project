package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.persistance.Guest;
import com.example.persistance.GuestDAO;
import com.example.persistance.GuestVO;
import com.example.service.GuestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class GuestService_UT {

	@Autowired
	private GuestService guestService;
	
	@Autowired
	private GuestDAO guestDAO;
	
	@Test
	public void retrieveGuestList() throws Exception {
		Guest guest1 = new Guest();
		//guest1.setId(1);
		guest1.setFirstName("FIRST1");
		guest1.setLastName("LAST1");
		guestDAO.save(guest1);
		
		Guest guest2 = new Guest();
		//guest2.setId(2);
		guest2.setFirstName("FIRST2");
		guest2.setLastName("LAST2");
		guestDAO.save(guest2);
		
		List<GuestVO> guestList = guestService.getGuestList();
		
		assertNotNull(guestList);
	}
	
	@Test
	public void retrieveGuestDetails() throws Exception {
		
		GuestVO guestDetails = guestService.getGuestDetails(1);
		
		assertNotNull(guestDetails);
		assertEquals("ANDY",guestDetails.getFirstName());
	}
	
	@Test
	public void updateGuestLastName() throws Exception {
		
		guestService.updateGuestLastName(2, "NEWLAST2");
		
		GuestVO guest2 = guestService.getGuestDetails(2);
		assertNotNull(guest2);
		assertEquals("NEWLAST2", guest2.getLastName());
	}
}

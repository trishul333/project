package com.example.persistance;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class GuestDAOImpl_UT {
	
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
		
		List<Guest> guestList = guestDAO.getGuestList();
		
		assertNotNull(guestList);
	}
	
	@Test
	public void retrieveGuestDetails() throws Exception {
		Guest guest1 = new Guest();
		guest1.setFirstName("FIRST1");
		guest1.setLastName("LAST1");
		guestDAO.save(guest1);
		
		Guest guest2 = new Guest();
		guest2.setFirstName("FIRST2");
		guest2.setLastName("LAST2");
		guestDAO.save(guest2);
		
		Guest guestDetails = guestDAO.getGuestDetails(1);
		
		assertNotNull(guestDetails);
	}
	
	@Test
	public void updateGuestLastName() throws Exception {
		Guest guest1 = guestDAO.getGuestDetails(1);
		guest1.setLastName("NEWLAST1");
		
		guestDAO.update(guest1);
		
		Guest newGuest1 = guestDAO.getGuestDetails(1);
		assertNotNull(newGuest1);
		assertEquals("NEWLAST1", newGuest1.getLastName());
	}
}

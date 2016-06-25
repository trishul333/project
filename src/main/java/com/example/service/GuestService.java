package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.message.GuestMessageSender;
import com.example.persistance.Guest;
import com.example.persistance.GuestDAO;
import com.example.persistance.GuestVO;

@WebService
public class GuestService {

	@Autowired
	GuestDAO guestDAO;
	
	@WebMethod(exclude=true)
	public void setSender(GuestMessageSender sender) {
		this.sender = sender;
	}

	@Autowired
	GuestMessageSender sender;
	
	@WebMethod(operationName="getGuestDetails")
	public GuestVO getGuestDetails(int id) {
		if(Double.isNaN(id) || id <= 0) {
			throw new IllegalArgumentException("Invalid Guest Number");
		} else {
			Guest guest = guestDAO.getGuestDetails(id);
			GuestVO guestDetails = new GuestVO();
			guestDetails.setId(guest.getId());
			guestDetails.setFirstName(guest.getFirstName());
			guestDetails.setLastName(guest.getLastName());
			return guestDetails;
		}
	}
	
	@WebMethod(operationName="getGuestList")
	public List<GuestVO> getGuestList() {
		List<Guest> guestDaoList = guestDAO.getGuestList();
		List<GuestVO> guestList = new ArrayList<GuestVO>();
		for(Guest guestDao:guestDaoList) {
			GuestVO guestDetails = new GuestVO();
			guestDetails.setId(guestDao.getId());
			guestDetails.setFirstName(guestDao.getFirstName());
			guestDetails.setLastName(guestDao.getLastName());
			guestList.add(guestDetails);
		}
		return guestList;
	}
	
	@WebMethod(operationName="updateGuestLastName")
	public void updateGuestLastName(int id, String lastName) {
		Guest guest = guestDAO.getGuestDetails(id);
		guest.setLastName(lastName);
		guestDAO.update(guest);
		sender.send("Successfully updated DB");
	}

	@WebMethod(exclude=true)
	public void setGuestDAO(GuestDAO guestDAO) {
		this.guestDAO = guestDAO;
	}

}

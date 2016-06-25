package com.example.persistance;

import java.util.List;

public interface GuestDAO {

	public void save(Guest guest);
	
	public List<Guest> getGuestList();
	
	public void update(Guest guest);
	
	public Guest getGuestDetails(int id);
	
}

package com.example.persistance;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GuestDAOImpl implements GuestDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	public void save(Guest guest) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(guest);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Guest> getGuestList() {
		Session session = this.sessionFactory.openSession();
		List<Guest> guestList = session.createQuery("from Guest").list();
		session.close();
		return guestList;
	}

	public void update(Guest guest) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(guest);
		tx.commit();
		session.close();
		
	}

	public Guest getGuestDetails(int id) {
		Session session = this.sessionFactory.openSession();
		Guest guestDetails = (Guest) session.get(Guest.class, id);
		session.close();
		return guestDetails;
	}

}

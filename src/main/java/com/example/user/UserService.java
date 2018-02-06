package com.example.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import com.example.userprofile.UserProfile;

@Service
public class UserService {
	SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(User.class)
								 .addAnnotatedClass(UserProfile.class)
								 .buildSessionFactory();
	
	public UserService() {
		
	}
	
	public ArrayList<User> getAllUsers(){
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			ArrayList<User> users = new ArrayList<User>( session.createQuery("from User").list());
			/*session.getTransaction().commit();
			session.close();*/
			return users;
	}
	
	
	public User getUser(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		User user = session.get(User.class, id);
		/*session.getTransaction().commit();
		session.close();*/
		return user;
	}

	public void addUser(User user) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		System.out.println("done");
	}
	
	
}

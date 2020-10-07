package com.vastika.uise.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastika.uise.model.User;
import com.vastika.uise.util.HibernateUtil;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveUser(User user) {
		Session session = HibernateUtil.getSession(sessionFactory);
		session.save(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = HibernateUtil.getSession(sessionFactory);
		session.update(user);
	}

	@Override
	public void deleteUser(int id) {
		User user = getUserById(id);
		if (user != null) {
			Session session = HibernateUtil.getSession(sessionFactory);
			session.delete(user);
		}

	}

	@Override
	public List<User> getAllUser() {
		Session session = HibernateUtil.getSession(sessionFactory);
		Criteria criteria = session.createCriteria(User.class);
		return criteria.list();
	}

	@Override
	public User getUserById(int id) {
		Session session = HibernateUtil.getSession(sessionFactory);
		User user = (User) session.get(User.class, id);
		//System.out.println(user);
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		Session session = HibernateUtil.getSession(sessionFactory);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", username));
		return (User)criteria.uniqueResult();
	}

	@Override
	public User getUserByEmail(String email) {
		Session session = HibernateUtil.getSession(sessionFactory);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		return (User)criteria.uniqueResult();
	}
}

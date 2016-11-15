package neu.edu.project.dao;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.project.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public Collection<User> listUsers() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public User getUser(long Id) {
		
		return (User)sessionFactory.getCurrentSession().load(User.class, Id);
	}

	@Override
	public User getByUserName(String username) {
		String Q = "FROM User ur WHERE ur.userName = :username";
		return (User) sessionFactory.getCurrentSession().createQuery(Q).setString("username", username).uniqueResult();
	}

	@Override
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeUser(long Id) {
		Session session = this.sessionFactory.getCurrentSession();
        User user=(User)session.load(User.class, Id);
        if(user!=null){
        	session.delete(user);
        }
	}

}

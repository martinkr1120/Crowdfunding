package neu.edu.project.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import neu.edu.project.dao.UserDAO;
import neu.edu.project.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public void createUser(User user) {
		userDAO.createUser(user);
	}

	@Override
	@Transactional
	public Collection<User> listUsers() {
		return userDAO.listUsers();
	}

	@Override
	@Transactional
	public User getUser(long Id) {
		return userDAO.getUser(Id);
	}

	@Override
	@Transactional
	public User getByUserName(String username) {
		return userDAO.getByUserName(username);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	@Transactional
	public void removeUser(long Id) {
		userDAO.removeUser(Id);
	}

}

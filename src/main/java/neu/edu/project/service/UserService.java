package neu.edu.project.service;

import java.util.Collection;

import neu.edu.project.domain.User;

public interface UserService {
	
	public void createUser(User user);
	public Collection<User> listUsers();
	public User getUser(long Id);
	public User getByUserName(String username);
	public void updateUser(User user);
	public void removeUser(long Id);

}

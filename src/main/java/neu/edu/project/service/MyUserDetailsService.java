package neu.edu.project.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import neu.edu.project.dao.UserDAO;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		System.out.println(userDAO.getByUserName(username).getUserName());
		neu.edu.project.domain.User user = userDAO.getByUserName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Username or Password not valid, please check!");
		}
		GrantedAuthority authorities;
		if(user.getRole().equals("Creator")){
			authorities = new SimpleGrantedAuthority("Creator");
		}else{
			authorities = new SimpleGrantedAuthority("Patron");
		}
		List<GrantedAuthority> grantedAuthorities = Arrays.asList(authorities);
		return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}

}

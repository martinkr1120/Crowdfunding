package neu.edu.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetURL = determineTargetURL(authentication);
		if (response.isCommitted()) {
			System.out.println("Cannot redirect");
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetURL);
	}
	
	protected String determineTargetURL(Authentication authentication){
		String url="";
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority a:authorities){
			roles.add(a.getAuthority());
		}
		if(isCreator(roles)||isPatron(roles)){
			url = "/";
		}else if(isAdmin(roles)){
			url = "/editHome";
		}else{
		
			url = "/accessDenied";
		}
		return url;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
	private boolean isCreator(List<String> roles){
		if(roles.contains("ROLE_Creator")){
			return true;
		}
		return false;
	}
	
	private boolean isPatron(List<String> roles){
		if(roles.contains("ROLE_Patron")){
			return true;
		}
		return false;
	}
	private boolean isAdmin(List<String> roles){
		if(roles.contains("ROLE_Admin")){
			return true;
		}
		return false;
	}

}

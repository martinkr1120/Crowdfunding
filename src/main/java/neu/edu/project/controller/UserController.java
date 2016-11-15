package neu.edu.project.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import neu.edu.project.domain.Post;
import neu.edu.project.domain.User;
import neu.edu.project.service.PostService;
import neu.edu.project.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showhome(Model model){
		if(postService.listRecentPosts()!=null){
		List postlist = (List)postService.listRecentPosts();
		model.addAttribute("postLists", postlist);
		}else{
			model.addAttribute("postLists", "");
		}
		if(postService.getRecentTitle()!=null){
		String recentTitle = postService.getRecentTitle();
		model.addAttribute("recentTitle", recentTitle);
		}else{
			model.addAttribute("recentTitle", " ");
		}
		if(postService.getRecentPostVid()!=null){
		String recentVid = postService.getRecentPostVid();
		model.addAttribute("vid", recentVid);
		}else{
			String recentVid="<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/wH-IDF809fQ\" frameborder=\"0\" allowfullscreen></iframe>";
			model.addAttribute("vid", "recentVid");
		}
		if(postService.getRecentDate()!=null){
		Date recentDate = postService.getRecentDate();
		String theDate = recentDate.toString();
		model.addAttribute("recenDate", theDate);
		}else{
			model.addAttribute("recenDate", new Date().getTime());
		}
		model.addAttribute("user", new User());
		//model.addAttribute("recentPosts", recentPosts);
		return "welcome";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model){
		model.addAttribute("user", new User());
		return "welcome";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult bindingResult, Model model){
		if(bindingResult.hasErrors()){
			return "registration";
		}
		model.addAttribute("user", user);
		userService.createUser(user);
		return "welcome";
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String showProfile(Model model){
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
		  model.addAttribute("user",userService.getByUserName(name));
		  return "profile";
	}

	////////////
	     
	    @RequestMapping(value= "/profile/modify", method = RequestMethod.POST)
	    public String addPerson(@ModelAttribute("user") User user){
	    
		      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
              User olduser = userService.getByUserName(auth.getName());
              System.out.println(user.getUserName());
              user.setId(olduser.getId());
	            userService.updateUser(user);
	         return "redirect:/profile";
	         
	    }
	    
	    ///////
	
	
	@RequestMapping(value="/photo/{username}", method=RequestMethod.GET)
	public @ResponseBody String viewPhoto(@PathVariable String username, HttpServletResponse response) throws IOException {
		User user = userService.getByUserName(username);
		byte[] photoBytes = user.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(user.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + user.getPhotoFilename() + "\"");
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";
	}
	
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String showNew(Model model){
		User user = userService.getByUserName("martin");
		System.out.println(user.getRole());
		return "new";
	}
	
	@RequestMapping(value="/Access_Denied", method=RequestMethod.GET)
	public String accessDeniedPage(ModelMap model){
		model.addAttribute("user", new User());
		//model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}
	
	public String getPrincipal(){
		String userName=null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails){
			userName = ((UserDetails)principal).getUsername();
		}else{
			userName = principal.toString();
		}
		return userName;
	}
	@RequestMapping(value="/editHome", method=RequestMethod.GET)
	public String editHome(){
		return "homeEdit";
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String errorPage(Model model){
		model.addAttribute("user", new User());
		return "403";
	}
	
	

}

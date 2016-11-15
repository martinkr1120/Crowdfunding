package neu.edu.project.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import neu.edu.project.dao.DonationDAO;
import neu.edu.project.domain.Post;
import neu.edu.project.domain.User;
import neu.edu.project.service.PostService;
import neu.edu.project.service.UserService;

@Controller
@RequestMapping("/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private DonationDAO donationDAO;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String showPost(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.getByUserName(name);
		Long creatorId = user.getId();
		System.out.println();
		model.addAttribute("user", user);
		model.addAttribute("post", new Post());
		if(postService.getPostCount(creatorId)!=null){
		BigInteger countPost = postService.getPostCount(creatorId);
		model.addAttribute("count", countPost);
		}else{
			model.addAttribute("count", 0);
		}
		if(donationDAO.getDonNum(creatorId)!=null){
		BigInteger countPatron = donationDAO.getDonNum(creatorId);
		model.addAttribute("countPatron", countPatron);
		}else{
			model.addAttribute("countPatron", 0);
		}
		if(donationDAO.getRecSum(creatorId)!=null){
		model.addAttribute("total", donationDAO.getRecSum(creatorId));
		}else{
		model.addAttribute("total",0);}
		
		if(postService.listMyPosts(user.getId())!=null){
			System.out.println(postService.listMyPosts(creatorId));
		List myPostList = (List)postService.listMyPosts(creatorId);
		model.addAttribute("myPostList", myPostList);
		}else{
			model.addAttribute("myPostList", null);
		}
		return "post";
	}

	@RequestMapping(value = "/post/new", method = RequestMethod.POST)
	public String createPost(@Valid Post post, BindingResult bindingResult, Model model) {
		// if(bindingResult.hasErrors()){
		// return "error";
		// }
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.getByUserName(name);
		post.setUser(user);
		model.addAttribute("post", post);
		postService.createPost(post);
		return "redirect:/explore";
	}

	@RequestMapping(value = "/post/photo/{postID}", method = RequestMethod.GET)
	public @ResponseBody String viewPhoto(@PathVariable long postID, HttpServletResponse response) throws IOException {
		Post post = postService.getPost(postID);
		byte[] photoBytes = post.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(post.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + post.getPhotoFilename() + "\"");
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";
	}
	
	@RequestMapping(value ="/post/delete/{postID}", method=RequestMethod.GET)
	public String deletePost(@PathVariable long postID){
		postService.removePost(postID);
		return "redirect:/post";
	}
	
	@RequestMapping(value="/post/viewPatrons", method = RequestMethod.GET)
	public String viewPatrons(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.getByUserName(name);
		System.out.println(user.getId());
		if((List)donationDAO.listPatrons(user.getId())!=null){
		List PatronList = (List)donationDAO.listPatrons(user.getId());
		User usertest = (User)PatronList.get(0);
		System.out.println(usertest.getEmail());
		model.addAttribute("PatronList", PatronList);
		}else{
			model.addAttribute("PatronList", null);
		}
		return "PatronList";
		
	}

}

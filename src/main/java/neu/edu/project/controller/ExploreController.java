package neu.edu.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neu.edu.project.domain.Comment;
import neu.edu.project.domain.Post;
import neu.edu.project.domain.User;
import neu.edu.project.service.CommentService;
import neu.edu.project.service.PostService;
import neu.edu.project.service.UserService;

@Controller
@RequestMapping("/")
public class ExploreController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/explore", method = RequestMethod.GET)
	public String showAllPost(Model model, Post post) {
		List postList = (List) postService.listPosts();
		// model.addAttribute("post", post);
		model.addAttribute("postList", postList);
		model.addAttribute("user", new User());

		return "explore";
	}
	
	@RequestMapping(value = "/explore/view/{postID}", method = RequestMethod.GET)
	public String showSinglePost(@PathVariable long postID,Model model, Post post,Comment comment) {
		post = postService.getPost(postID);
		model.addAttribute("post", post);
		model.addAttribute("user", new User());
		List commentList = (List)commentService.listComments(postID);
		model.addAttribute("comment", comment);
		model.addAttribute("commentList",commentList);
		return "viewExplorer";
	}
	
	@RequestMapping(value = "/explore/view/{postID}", method = RequestMethod.POST)
	public String CommentPost(@PathVariable long postID, @ModelAttribute("comment")Comment comment, Model model, Post post, User user,BindingResult bindingResult) {
		post = postService.getPost(postID);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		user = userService.getByUserName(name);
		comment.setUser(user);
		comment.setPost(post);
		//model.addAttribute("comment", comment);
		commentService.createComment(comment);
		return "redirect:/explore/view/{postID}";
	}
	
	@RequestMapping(value = "/explore/view/{postID}/download", method = RequestMethod.GET)
	public void downloadFile(@PathVariable long postID,Model model, Post post, HttpServletResponse response)throws IOException {
		post = postService.getPost(postID);
		response.setContentType(post.getPhotoContentType());
		response.setContentLength(post.getPhotoBytes().length);
		response.setHeader("Content-Disposition", "attachment;filename=\""+post.getTitle()+"\"");
		FileCopyUtils.copy(post.getPhotoBytes(), response.getOutputStream());
		
	}
	

}

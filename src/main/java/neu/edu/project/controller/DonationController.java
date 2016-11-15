package neu.edu.project.controller;


import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neu.edu.project.domain.Donation;
import neu.edu.project.domain.User;
import neu.edu.project.service.DonationService;
import neu.edu.project.service.PostService;
import neu.edu.project.service.UserService;

@Controller
@RequestMapping("/")
public class DonationController {
	
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private DonationService donationService;
	
	@RequestMapping(value = "/donate/{userID}", method = RequestMethod.GET)
	public String showDonate(@PathVariable long userID,Model model, Donation donation,User user) {
		user = userService.getUser(userID);
		model.addAttribute("user",user);
		return "donation";
	}
	
	@RequestMapping(value="/donate/{creatorID}", method=RequestMethod.POST)
	public String executeDonate(@PathVariable long creatorID,Model model, Donation donation,User creator,User currUser, Double amount){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String currName = auth.getName();
	    currUser = userService.getByUserName(currName);
		creator = userService.getUser(creatorID);
		donation.setCreator(creator);
		donation.setPatron(currUser);
		donation.setAmount(amount);
		donationService.createDonation(donation);
		System.out.println(donationService.getRecSum(creatorID));
		return "redirect:/donationSuccess";
	}
	
	@RequestMapping(value="/donationSuccess", method=RequestMethod.GET)
	public String getDonaSuccess(){
		return "donationSuccess";
	}
}

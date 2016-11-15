package neu.edu.project.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neu.edu.project.domain.User;
import neu.edu.project.service.DonationService;
import neu.edu.project.service.UserService;

@Controller
public class MailController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DonationService donationService;
	
	private JavaMailSenderImpl sender = new JavaMailSenderImpl();

	
//	@RequestMapping(value="/send-mail")
//	public String sendEmail(){
//		sender.setHost("smtp.gmail.com");
//		sender.setUsername("martinkr921120@gmail.com");
//		sender.setPassword("QweR1234.");
//		sender.setPort(587);
//		Properties pro = new Properties();
//		pro.setProperty("mail.smtp.auth", "true");
//		pro.setProperty("mail.smtp.starttls.enable", "true");
//		sender.setJavaMailProperties(pro);
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setFrom("martinkr1120@Gmail.com");
//		msg.setTo("martinkr921120@gmail.com");
//		msg.setReplyTo("martinkr921120@gmail.com");
//		msg.setSubject("Hi");
//		msg.setText("Test");
//		sender.send(msg);
//		this.sendEmail("martinkr921120@gmail.com", "QweR1234.", "martinkr1120@gmail.com", "fromEmailMethod", "See if success");
//		System.out.println("mail sent!");
//		return "mailsent";
//
//	}
	@RequestMapping(value="/send-mail/{PatronID}", method = RequestMethod.GET)
	public String showEmailForm(Model model,@PathVariable long PatronID, User patron, User creator){
		patron = userService.getUser(PatronID);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		creator = userService.getByUserName(name);
		model.addAttribute("Patron", patron);
		model.addAttribute("Creator",creator);
		return "EmailForm";
        
		
	}
	
	@RequestMapping(value="/send-mail/{PatronID}", method = RequestMethod.POST)
	public String sendEmailForm(Model model, @PathVariable long PatronID,User patron,HttpServletRequest request){
		patron = userService.getUser(PatronID);
		model.addAttribute("patron",patron);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User creator = userService.getByUserName(name);
		String from = creator.getEmail();
		String fromPass = creator.getPassword();
		String to = patron.getEmail();
		String subject = request.getParameter("Subject");
		String text = request.getParameter("textarea");
		this.sendEmail(from, fromPass, to, subject, text);
		return "EmailSent";		
	}
	
	public void sendEmail(String from, String fromPass,String to, String Subject, String Text){
		sender.setHost("smtp.gmail.com");
		sender.setUsername(from);
		sender.setPassword(fromPass);
		sender.setPort(587);
		Properties pro = new Properties();
		pro.setProperty("mail.smtp.auth", "true");
		pro.setProperty("mail.smtp.starttls.enable", "true");
		sender.setJavaMailProperties(pro);
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(to);
		//msg.setReplyTo(to);
		msg.setSubject(Subject);
		msg.setText(Text);
		sender.send(msg);
		System.out.println("mail sent!");
	}
	
	

}

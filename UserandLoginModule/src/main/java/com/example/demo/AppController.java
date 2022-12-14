package com.example.demo;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
 
@Controller
public class AppController {

	
	@Autowired
	private UserRepository repo;

	
	@GetMapping("")
	public String viewHomePage() {
        return "index";
    }
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
    model.addAttribute("user", new User());
     
    	return "signup_form";
}
    
	 
    @PostMapping("/process_register")
    public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
		repo.save(user);
         
        return "login";
    
}
    
    @GetMapping("/list_users")
    public String  getUsersList() {
    	
      return "users";
      
      
      
    }
     
    @GetMapping("/About_Us")
    public String  getAboutUs() {
      return "About_Us";
      
 
      
    }
     
  
     
    @GetMapping("/login")
    public String showLoginPage() {
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	 return "login";
    }

       return "users";
      
}

    @GetMapping("/logout")
    public String  getLogOut() {
      return "login";
}
    
    @GetMapping("/Vendor")
    public String  getLogin() {
      return "login";
}
}




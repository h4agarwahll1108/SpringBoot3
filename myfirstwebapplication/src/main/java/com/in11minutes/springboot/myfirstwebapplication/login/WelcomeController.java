package com.in11minutes.springboot.myfirstwebapplication.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUsername());
		return "welcome";
	}

	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getName();
	}
}

/*
 * public class LoginController {
 * 
 * private Logger logger =LoggerFactory.getLogger(getClass());
 * //@RequestMapping("login") public String gotoLoginPage(@RequestParam String
 * name,ModelMap model) { model.put("name", name); return "login"; Logger
 * setting should be done in application .properties
 * logger.debug("Request para is {}",name);
 * logger.info("I want this printed at info level is {}", name);
 * logger.warn("I want this printed at warn level");
 * System.out.println("Req param is "+ name); //Not good prog practice at pro
 * level }
 * 
 * public LoginController(AuthenticationService authentication) { super();
 * this.authentication = authentication; } private AuthenticationService
 * authentication; //@RequestMapping(value = "login", method =
 * RequestMethod.GET) public String gotoLoginPage() { return "login"; }
 * /*@RequestMapping(value ="login", method = RequestMethod.POST) public String
 * gotoWelcomePage(@RequestParam String name, @RequestParam String password,
 * ModelMap model)
 * 
 * { if(authentication.authenticate(name,password)) { model.put("name", name);
 * model.put("password", password); return "welcome"; }
 * model.put("errorMessage", "Invalid credential! Please try again"); return
 * "login"; }
 */

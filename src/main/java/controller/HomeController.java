package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.User;
import service.UserService;


/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * poprawic sciezke w request mapping - usunac /fantasy
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String login(Model model) {
		model.addAttribute("greeting", "Witaj w Fantasy Premier League!");
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getAddNewUserForm(Model model) {
		User user = new User();
		model.addAttribute("newUser", user);
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processAddNewUserForm(@ModelAttribute("newUser") User newUser, HttpServletRequest request) {
		userService.addUser(newUser);
		return "redirect:/";
	}
	
}

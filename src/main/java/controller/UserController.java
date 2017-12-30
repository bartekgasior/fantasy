package controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.UsersTeam;
import service.UserService;
import service.UsersTeamService;

@Controller
@RequestMapping("/userPanel")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UsersTeamService usersTeamService;
	
	@RequestMapping
	public String userFunctions(Model model) {
		return "userFunctionList";
	}
	
	@RequestMapping("/teams")
	public String userTeamsList(Model model) {
		
		return "userTeamsList";
	}
	
	@RequestMapping(value = "/teams/add", method = RequestMethod.GET)
	public String userAddTeamForm(Model model) {
		UsersTeam team = new UsersTeam();		
		model.addAttribute("team", team);
		return "userAddTeam";
	}
	
	@RequestMapping(value="/teams/add", method=RequestMethod.POST)
	public String processAddTeamForm(@ModelAttribute("team") UsersTeam team, HttpServletRequest request, Principal principal) {
		team.setUserId(userService.findUserByName((principal.getName())).getUserId());
		usersTeamService.addUsersTeam(team);
		return "redirect:/userPanel/teams";
	}

}

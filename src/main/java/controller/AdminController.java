package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.RealTeamService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RealTeamService realTeamService;
	
	@RequestMapping
	public String adminFunctions(Model model) {
		return "adminFunctionList";
	}
	
	@RequestMapping("/realTeams")
	public String adminRealTeamsList(Model model) {
		model.addAttribute("realTeamNames", realTeamService.getAllRealTeams());
		return "adminRealTeamsList";
	}
}

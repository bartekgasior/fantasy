package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Player;
import domain.RealTeam;
import service.PlayerService;
import service.RealTeamService;
import service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RealTeamService realTeamService;
	@Autowired
	private UserService userService;
	@Autowired
	private PlayerService playerService;
	
	public int realTeamId;
	
	@RequestMapping
	public String adminFunctions(Model model) {
		return "adminFunctionList";
	}
	
	/*
	 * Druzyny
	 */
	
	@RequestMapping("/realTeams")
	public String adminRealTeamsList(Model model) {
		model.addAttribute("realTeams", realTeamService.getAllRealTeams());
		return "adminRealTeamsList";
	}
	
	@RequestMapping(value = "/realTeams/addRealTeam", method = RequestMethod.GET)
	public String adminAddRealTeamForm(Model model) {
		RealTeam realTeam = new RealTeam();
		model.addAttribute("realTeam", realTeam);
		return "adminAddRealTeam";
	}
	
	@RequestMapping(value = "/realTeams/addRealTeam", method = RequestMethod.POST)
	public String processAddNewRealTeamForm(@ModelAttribute("realTeam") RealTeam realTeam, HttpServletRequest request) {
		realTeamService.addRealTeam(realTeam);
		return "redirect:/admin/realTeams";
	}
	
	@RequestMapping(value = "/deleteRealTeam/{id}", method = RequestMethod.GET)
	public String adminDeleteRealTeam(@PathVariable("id") Long id) {
		realTeamService.deleteRealTeam(id);
		return "redirect:/admin/realTeams";
	}
	
	
	@RequestMapping(value = "/realTeam/{id}/players", method = RequestMethod.GET)
	public String adminRealTeamPlayersList(@PathVariable("id") Long realTeamId, Model model) {
		model.addAttribute("realTeamPlayers", playerService.getRealTeamPlayers(realTeamId));
		model.addAttribute("realTeam", realTeamService.getRealTeam(realTeamId));
		return "adminRealTeamPlayersList";
	}
	
	@RequestMapping(value = "/realTeam/{id}/addPlayer", method = RequestMethod.GET)
	public String adminAddPlayerToRealTeam(Model model) {
		Player player = new Player();
		//model.addAttribute("realTeamId", realTeamId);
		model.addAttribute("player", player);
		return "adminAddPlayer";
	}
	
	@RequestMapping(value = "/realTeam/{id}/addPlayer", method = RequestMethod.POST)
	public String processAddPlayerToRealTeamForm(@ModelAttribute("player") Player player, @PathVariable("id") Long realTeamId, HttpServletRequest request) {
		player.setReal_team_id(realTeamId);
		playerService.addPlayer(player);
		return "redirect:/admin/realTeam/{id}/players";
	}
	
	
	/*
	 * Zawodnicy
	 */
	
	@RequestMapping(value = "/players/{pageId}")
	public String adminPlayersList(@PathVariable("pageId") int pageId, Model model) {
		int pages = playerService.getAllPlayers().size()/20 + 1;
		model.addAttribute("playersList", playerService.getPlayersByPage(pageId, 20));
		model.addAttribute("realTeamsList", realTeamService.getAllRealTeams());
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", pageId);
		return "adminPlayersList";
	}
	
	/*
	 * Uzytkownicy
	 */
	
	@RequestMapping("/usersList")
	public String adminUsersList(Model model) {
		model.addAttribute("usersList", userService.getAllUsers());
		return "adminUsersList";
	}
	
	@RequestMapping(value="/deleteUser/{userId}", method = RequestMethod.GET)
	public String adminDeleteUser(@PathVariable("userId") Long userId){
		userService.deleteUser(userId);
		return "redirect:/admin/usersList";
	}

}

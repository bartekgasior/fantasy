package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import domain.Match;
import domain.MatchPlayer;
import domain.Player;
import domain.RealTeam;
import service.MatchPlayerService;
import service.MatchService;
import service.PlayerResultService;
import service.PlayerService;
import service.PositionService;
import service.RealTeamService;
import service.ResultService;
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
	@Autowired
	private PositionService positionService;
	@Autowired  
	private MatchService matchService;
	@Autowired 
	private ResultService resultService;
	@Autowired
	private PlayerResultService playerResultService;
	@Autowired 
	private MatchPlayerService matchPlayerService;
	
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
	public String processAddNewRealTeamForm(@ModelAttribute("realTeam") @Valid RealTeam realTeam, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			return "adminAddRealTeam";
		}
		
		realTeamService.addRealTeam(realTeam);
		return "redirect:/admin/realTeams";
	}
	
	@RequestMapping(value = "/deleteRealTeam/{id}", method = RequestMethod.GET)
	public String adminDeleteRealTeam(@PathVariable("id") Long id) {
		realTeamService.deleteRealTeam(id);
		return "redirect:/admin/realTeams";
	}
	
	@RequestMapping(value ="/realTeam/edit", method = RequestMethod.GET)
	public String editRealTeam(@RequestParam("id") Long realTeamId, Model model) {
		model.addAttribute("realTeam", realTeamService.getRealTeam(realTeamId));
		return "adminEditRealTeam";
	}
	
	@RequestMapping(value ="/realTeam/edit", method = RequestMethod.POST)
	public String processEditRealTeam(@ModelAttribute("realTeam") RealTeam realTeam, HttpServletRequest request) {
		realTeamService.updateRealTeam(realTeam);
		return "redirect:/admin/realTeams";
	}
	
	@RequestMapping("/realTeam/{id}/players")
	public String adminRealTeamPlayersList(@PathVariable("id") Long realTeamId, Model model) {
		model.addAttribute("realTeamPlayers", playerService.getRealTeamPlayers(realTeamId));
		model.addAttribute("realTeam", realTeamService.getRealTeam(realTeamId));
		return "adminRealTeamPlayersList";
	}
	
	@RequestMapping(value = "/realTeam/{id}/addPlayer", method = RequestMethod.GET)
	public String adminAddPlayerToRealTeam(Model model) {
		Player player = new Player();
		model.addAttribute("positions", positionService.getAllPositions());
		model.addAttribute("player", player);
		return "adminAddPlayer";
	}
	
	@RequestMapping(value = "/realTeam/{id}/addPlayer", method = RequestMethod.POST)
	public String processAddPlayerToRealTeamForm(@ModelAttribute("player") @Valid Player player, BindingResult result, Model model,  @PathVariable("id") Long realTeamId, HttpServletRequest request) {
		player.setReal_team_id(realTeamId);
		model.addAttribute("positions", positionService.getAllPositions());
		model.addAttribute("player", player);
		if(result.hasErrors()) {
			
			return "adminAddPlayer";
		}
		playerService.addPlayer(player);
		return "redirect:/admin/realTeam/{id}/players";
	}
	
	/*
	 * Zawodnicy
	 */
	
	@RequestMapping("/players/{pageId}")
	public String adminPlayersList(@PathVariable("pageId") int pageId, Model model, HttpServletRequest request) {
		int pages = playerService.getAllPlayers().size()/20 + 1;
		model.addAttribute("playersList", playerService.getPlayersByPage(pageId, 20));
		model.addAttribute("realTeamsList", realTeamService.getAllRealTeams());
		model.addAttribute("positions", positionService.getAllPositions());
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", pageId);
		
		request.getSession().setAttribute("page",  pageId);
		return "adminPlayersList";
	}
	
	@RequestMapping("/players/{pageId}/filter/{ByCriteria}")
	public String getPlayersByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String, List<String>> filterParams, @PathVariable("pageId") int pageId, Model model) {
		//int pages = playerService.getAllPlayers().size()/20 + 1;
		model.addAttribute("playersList", playerService.getPlayersByFilter(filterParams));
		model.addAttribute("realTeamsList", realTeamService.getAllRealTeams());
		model.addAttribute("positions", positionService.getAllPositions());
		model.addAttribute("pages", 1);
		model.addAttribute("currentPage", pageId);
		return "adminPlayersList";
	}
	
	@RequestMapping(value = "/players/{pageId}/deletePlayer/{id}", method = RequestMethod.GET)
	public String adminDeletePlayer(@PathVariable("id") Long id, @PathVariable("pageId") int pageId) {
		playerService.deletePlayer(id);
		return "redirect:/admin/players/{pageId}";
	}
	
	@RequestMapping(value ="/players/edit", method = RequestMethod.GET)
	public String editPlayer(@RequestParam("id") Long playerId, Model model) {
		model.addAttribute("player", playerService.getPlayerById(playerId));
		model.addAttribute("realTeams", realTeamService.getAllRealTeams());
		model.addAttribute("positions", positionService.getAllPositions());
		return "adminEditPlayer";
	}
	
	@RequestMapping(value ="/players/edit", method = RequestMethod.POST)
	public String processEditPlayer(@ModelAttribute("player") Player player, HttpServletRequest request) {
		//realTeamService.updateRealTeam(realTeam);
		int pageId = (int)request.getSession().getAttribute("page");
		playerService.updatePlayer(player);
		return "redirect:/admin/players/" + pageId;
	}
	
	/*
	 * Mecze
	 */
	
	@RequestMapping("/matches/{pageId}")
	public String adminMatchesList(@PathVariable("pageId") int pageId, Model model) {
		int pages = matchService.getAllMatches().size()/20 + 1;
		model.addAttribute("matchesList", matchService.getMatchesByPage(pageId, 20));
		model.addAttribute("realTeamsList", realTeamService.getAllRealTeams());
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", pageId);
		return "adminMatchesList";
	}
	
	@RequestMapping(value = "/matches/addMatch", method = RequestMethod.GET)
	public String adminAddMatch(Model model) {
		Match match = new Match();
		model.addAttribute("realTeams", realTeamService.getAllRealTeams());
		model.addAttribute("results", resultService.getResultsNames());
		model.addAttribute("players", new Gson().toJson(playerService.getAllPlayers()));
		model.addAttribute("playerResults", new Gson().toJson(playerResultService.getAllResults()));
		model.addAttribute("match", match);
		return "adminAddMatch";
	}
	
	@RequestMapping(value = "/matches/addMatch", method = RequestMethod.POST)
	public String processAddMatchForm(
			@RequestParam(value="myArray", required=false) String[] homeTeamPlayersResultId, 
			@RequestParam(value="myArray1", required=false) String[] awayTeamPlayersResultId,
			@RequestParam(value="myArray2", required=false) String[] homeTeamPlayersResultValues,
			@RequestParam(value="myArray3", required=false) String[] awayTeamPlayersResultValues,
			@ModelAttribute("match") @Valid Match match, BindingResult result, HttpServletRequest request, Model model) {
		//match.setId(0);
		if(result.hasErrors()) {
			Match match1 = new Match();
			model.addAttribute("realTeams", realTeamService.getAllRealTeams());
			model.addAttribute("results", resultService.getResultsNames());
			model.addAttribute("players", new Gson().toJson(playerService.getAllPlayers()));
			model.addAttribute("playerResults", new Gson().toJson(playerResultService.getAllResults()));
			model.addAttribute("match", match1);
			return "adminAddMatch";
		}
		matchService.addMatch(match); 
		if(match.getAway_team_id() != 0 && match.getHome_team_id() != 0) {
			request.getSession().setAttribute("homeTeam", match.getHome_team_id());
			request.getSession().setAttribute("awayTeam", match.getAway_team_id());
		}
		
		if(homeTeamPlayersResultId!=null && awayTeamPlayersResultId != null && homeTeamPlayersResultValues != null && awayTeamPlayersResultValues != null) {
			int homeTeamId = (int)request.getSession().getAttribute("homeTeam");
			int awayTeamId = (int)request.getSession().getAttribute("awayTeam");
			Match matchTmp = matchService.findMatchByTeamsId(homeTeamId, awayTeamId);
			//System.out.println(matchTmp.getHome_team_id() + " - " + matchTmp.getAway_team_id() + " - " + matchTmp.getId());
			addPlayersToMatch(homeTeamPlayersResultId, awayTeamPlayersResultId, homeTeamPlayersResultValues, awayTeamPlayersResultValues, matchTmp.getId());
		}
		//model.addAttribute("match", match);
		return "redirect:/admin/matches/1";
	}
	
	private void addPlayersToMatch(String[] homeTeamIds, String[] awayTeamIds, String[] homeTeamResults, String[] awayTeamResults, int matchId) {
		for(int i=0;i<homeTeamIds.length; i++) {
			homeTeamIds[i]=homeTeamIds[i].substring(6);
			MatchPlayer player = new MatchPlayer(Long.valueOf(matchId), Long.parseLong(homeTeamIds[i]), Integer.parseInt(homeTeamResults[i])+1);
			matchPlayerService.addMatchPlayer(player);
		}
		for(int i=0;i<awayTeamIds.length; i++) {
			awayTeamIds[i]=awayTeamIds[i].substring(6);
			MatchPlayer player = new MatchPlayer(Long.valueOf(matchId), Long.parseLong(awayTeamIds[i]), Integer.parseInt(awayTeamResults[i])+1);
			matchPlayerService.addMatchPlayer(player);
		}
		
	}
	
	@RequestMapping(value = "/matches/deleteMatch/{id}", method = RequestMethod.GET)
	public String adminDeleteMatch(@PathVariable("id") Long id) {
		matchService.deleteMatch(id);
		return "redirect:/admin/matches/1";
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

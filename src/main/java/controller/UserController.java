package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import domain.League;
import domain.LeagueUsersTeam;
import domain.MatchPlayer;
import domain.Player;
import domain.UsersTeam;
import domain.UsersTeamPlayer;
import service.LeagueService;
import service.LeagueUsersTeamService;
import service.MatchPlayerService;
import service.PlayerService;
import service.UserService;
import service.UsersTeamPlayerService;
import service.UsersTeamService;

@Controller
@RequestMapping("/userPanel")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UsersTeamService usersTeamService;
	@Autowired
	private LeagueService leagueService;
	@Autowired 
	private LeagueUsersTeamService leagueUsersTeamService;
	@Autowired
	private PlayerService playerService;
	@Autowired 
	private UsersTeamPlayerService usersTeamPlayerService;
	@Autowired
	private MatchPlayerService matchPlayerService;
	
	@RequestMapping
	public String userFunctions(Model model) {
		return "userFunctionList";
	}
	
	@RequestMapping("/teams")
	public String userTeamsList(Model model, Principal principal) {
		Long userId = userService.findUserByName((principal.getName())).getUserId();
		
		List<UsersTeam> usersTeams = usersTeamService.getUsersTeams(userId);
		List<Player> players = playerService.getAllPlayers();
		List<UsersTeamPlayer> teamsPlayers = usersTeamPlayerService.getAll();
		List<MatchPlayer> matchPlayers = matchPlayerService.getAllMatchPlayers();

		usersTeams = setScore(usersTeams, players, teamsPlayers, matchPlayers);
		
		model.addAttribute("teams", usersTeams);
		model.addAttribute("leagueUsersTeam", leagueUsersTeamService.getUsersLeagues(userId));
		model.addAttribute("userId", userId);
		return "userTeamsList";
	}
	
	@RequestMapping(value = "/teams/edit", method = RequestMethod.GET)
	public String userTeamLineUp(Model model, HttpServletRequest request, Principal principal, @RequestParam("id") int teamId) {
		List<Player> gkList = playerService.getPlayersByPosition("GoalKeepers");
		List<Player> dfList = playerService.getPlayersByPosition("Defenders");
		List<Player> mfList = playerService.getPlayersByPosition("MidFielders");
		List<Player> fwList = playerService.getPlayersByPosition("Forwards");
		Long userId = userService.findUserByName((principal.getName())).getUserId();
		
		System.out.println(teamId + " " + userId);
		
		Long leagueId = leagueUsersTeamService.findLeagueByTeamAndUserId(teamId, userId);
		
		League league = leagueService.findLeagueById(leagueId.intValue());
		request.getSession().setAttribute("teamId",  teamId);
		
		model.addAttribute("leagueStartingMoney", league.getStartingMoney());
		model.addAttribute("gkList", new Gson().toJson(gkList));
		model.addAttribute("dfList", new Gson().toJson(dfList));
		model.addAttribute("mfList", new Gson().toJson(mfList));
		model.addAttribute("fwList", new Gson().toJson(fwList));
		return "userTeamLineUp";
	}
	
	@RequestMapping(value = "/teams/edit", method = RequestMethod.POST)
	public String processUserTeamLineUp(HttpServletRequest request,
			@RequestParam(value="gk", required=false) String[] gk,
			@RequestParam(value="df", required=false) String[] df,
			@RequestParam(value="mf", required=false) String[] mf,
			@RequestParam(value="fw", required=false) String[] fw
			) {
		int teamId = (int)request.getSession().getAttribute("teamId");
		
		if(gk != null) {
			for(int i=0;i<gk.length;i++) {
				Long playerId = Long.parseLong(gk[i].substring(10),10);
				UsersTeamPlayer usersTeamPlayer = new UsersTeamPlayer(playerId, teamId);
				usersTeamPlayerService.add(usersTeamPlayer);}}
		if(df != null) {
			for(int i=0;i<df.length;i++) {
				Long playerId = Long.parseLong(df[i].substring(10),10);
				UsersTeamPlayer usersTeamPlayer = new UsersTeamPlayer(playerId, teamId);
				usersTeamPlayerService.add(usersTeamPlayer);}}
		if(mf != null) {
			for(int i=0;i<mf.length;i++) {
				Long playerId = Long.parseLong(mf[i].substring(10),10);
				UsersTeamPlayer usersTeamPlayer = new UsersTeamPlayer(playerId, teamId);
				usersTeamPlayerService.add(usersTeamPlayer);}}
		if(fw != null) {
			for(int i=0;i<fw.length;i++) {
				Long playerId = Long.parseLong(fw[i].substring(10),10);
				UsersTeamPlayer usersTeamPlayer = new UsersTeamPlayer(playerId, teamId);
				usersTeamPlayerService.add(usersTeamPlayer);}}
		
		return "redirect:/userPanel/teams";
	}
	
	@RequestMapping(value = "leagues/teams/add", method = RequestMethod.GET)
	public String userAddTeamForm(Model model) {
		UsersTeam team = new UsersTeam();		
		model.addAttribute("team", team);
		return "userAddTeam";
	}
	
	@RequestMapping(value = "leagues/teams/add", method = RequestMethod.POST)
	public String processAddTeamForm(@ModelAttribute("team") @Valid UsersTeam team, BindingResult result, HttpServletRequest request, Principal principal) {
		team.setUserId(userService.findUserByName((principal.getName())).getUserId());
	
		if(result.hasErrors()) {	
			return "userAddTeam";
		}
		
		int leagueId = (int)request.getSession().getAttribute("leagueId");
		UsersTeam teamTMP = usersTeamService.addUsersTeam(team);
		
		LeagueUsersTeam leagueUsersTeam = new LeagueUsersTeam();
		leagueUsersTeam.setLeagueId(leagueId);
		leagueUsersTeam.setUsersTeamId(teamTMP.getId());
		leagueUsersTeam.setUsersTeamUserId(teamTMP.getUserId());
		leagueUsersTeamService.add(leagueUsersTeam);
		
		return "redirect:/userPanel/leagues";
	}
	
	@RequestMapping(value = "leagues/{id}/teams/add", method = RequestMethod.GET)
	public String userAddTeamToLeagueForm(Model model, @PathVariable("id") int LeagueId) {
		UsersTeam team = new UsersTeam();		
		model.addAttribute("team", team);
		return "userAddTeam";
	}
	
	@RequestMapping(value = "leagues/{id}/teams/add", method = RequestMethod.POST)
	public String processAddTeamToLeagueForm(@ModelAttribute("team") @Valid UsersTeam team, BindingResult result, HttpServletRequest request, Principal principal, @PathVariable("id") int leagueId) {
		team.setUserId(userService.findUserByName((principal.getName())).getUserId());
		team.setId(0);
		
		if(result.hasErrors()) {	
			return "userAddTeam";
		}
		UsersTeam teamTMP = usersTeamService.addUsersTeam(team);
		
		LeagueUsersTeam leagueUsersTeam = new LeagueUsersTeam();
		leagueUsersTeam.setLeagueId(leagueId);
		leagueUsersTeam.setUsersTeamId(teamTMP.getId());
		leagueUsersTeam.setUsersTeamUserId(teamTMP.getUserId());
		leagueUsersTeamService.add(leagueUsersTeam);
		
		return "redirect:/userPanel/leagues";
	}
	
	@RequestMapping("/leagues")
	public String userLeaguesList(Model model, Principal principal) {
		Long userId = userService.findUserByName((principal.getName())).getUserId();
		model.addAttribute("leagues", leagueService.getUserLeagues());
		model.addAttribute("allLeaguesTeams", leagueUsersTeamService.getAll());
		model.addAttribute("leagueUsersTeam", leagueUsersTeamService.getUsersLeagues(userId));
		model.addAttribute("userId", userId);
		List<League> availableUserLeagues = leaguesAvailableForUser(leagueService.getUserLeagues(), leagueUsersTeamService.getUsersLeagues(userId));
		model.addAttribute("availableUserLeagues", availableUserLeagues);
		return "userLeaguesList";
	}
	
	@RequestMapping(value = "/league/add", method=RequestMethod.GET)
	public String userAddLeagueForm(Model model) {
		League league = new League();
		model.addAttribute("league", league);
		return "userAddLeague";
	}

	@RequestMapping(value = "/league/add", method=RequestMethod.POST)
	public String processAddLeagueForm(@ModelAttribute("league") @Valid League league, BindingResult result, HttpServletRequest request, Principal principal) {
		league.setAdminId(userService.findUserByName((principal.getName())).getUserId());
		
		if(result.hasErrors()) {
			return "userAddLeague";
		}else {
			League leagueTMP = leagueService.addLeague(league);
			request.getSession().setAttribute("leagueId",  leagueTMP.getId());
			return "redirect:/userPanel/leagues/teams/add";
		}
	}
	
	@RequestMapping("/league")
	public String userTeamLeague(Model model, @RequestParam("id") Long leagueId) {
		List<UsersTeam> leagueTeamsList = new ArrayList<>();
		
		List<LeagueUsersTeam> leagueUsersTeam = leagueUsersTeamService.getAll();
		List<UsersTeam> usersTeam = usersTeamService.getAll();
		
		List<Player> players = playerService.getAllPlayers();
		List<UsersTeamPlayer> teamsPlayers = usersTeamPlayerService.getAll();
		List<MatchPlayer> matchPlayers = matchPlayerService.getAllMatchPlayers();
		
		for(int i=0;i<leagueUsersTeam.size(); i++) {
			if(leagueUsersTeam.get(i).getLeagueId() == leagueId) {
				for(int j=0;j<usersTeam.size();j++) {
					if(leagueUsersTeam.get(i).getUsersTeamId() == usersTeam.get(j).getId()) {
						leagueTeamsList.add(usersTeam.get(j));
					}
				}
			}
			
		}
		
		for(int i=0;i<leagueTeamsList.size(); i++) {
			int score=0;
			for(int j=0;j<teamsPlayers.size(); j++) {
				if(leagueTeamsList.get(i).getId() == teamsPlayers.get(j).getUsersTeamId()) {
					for(int z=0; z<players.size(); z++) {
						if(players.get(z).getId() == teamsPlayers.get(j).getPlayerId()) {
							for(int k=0;k<matchPlayers.size();k++) {
								if(matchPlayers.get(k).getPlayerId() == players.get(z).getId())
									score+=matchPlayers.get(k).getResult();
							}
						}
					}
				}
			}
			leagueTeamsList.get(i).setScore(score);
		}

		
		
		model.addAttribute("leagueTeamsList", leagueTeamsList);
		return "userTeamLeague";
	}
	
	public List<League> leaguesAvailableForUser(List<League> leagues, List<LeagueUsersTeam> userLeagues){
		List<League> list = new ArrayList<>();
		boolean flag;
		for (int i=0;i<leagues.size();i++) {
			flag = false;

					for(int k=0;k<userLeagues.size();k++) {
						if(leagues.get(i).getId() == userLeagues.get(k).getLeagueId())
							flag=true;
					}
					if(flag == false) {
						if(!list.contains(leagues.get(i))) {
							list.add(leagues.get(i));
						}
					}
				
			
			flag=false;
		}
		if(userLeagues.size() == 0)
			return leagues;
		else
			return list;
	}
	
	public List<UsersTeam> setScore(List<UsersTeam> usersTeams,	List<Player> players, List<UsersTeamPlayer> teamsPlayers, List<MatchPlayer> matchPlayers){
		for(int i=0;i<usersTeams.size(); i++) {
			int score=0;
			UsersTeam currentUT = usersTeams.get(i);
			for(int j=0;j<teamsPlayers.size(); j++) {
				if(currentUT.getId() == teamsPlayers.get(j).getUsersTeamId()) {
					usersTeams.get(i).setTeamSelected(1);
					for(int k=0;k<players.size();k++) {
						if(teamsPlayers.get(j).getPlayerId() == players.get(k).getId()) {
							for(int z=0;z<matchPlayers.size();z++) {
								if(players.get(k).getId() == matchPlayers.get(z).getPlayerId()) {
									score += matchPlayers.get(z).getResult();
								}
							}
						}
					}
				}
			}
			usersTeams.get(i).setScore(score);
		}
		return usersTeams;
	}
}

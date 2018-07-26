package com.capgemini.jstk.BoardGameCapmates.repository;

import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import static com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper.*;

@Repository
public class PlayerMapDAO {

	private Map<String, Player> playerMap;

	
	PlayerMapDAO() {
		this.playerMap = new HashMap<>();
	}
	
	@PostConstruct
	public void init() throws ExistingNicknameException {
		addPlayer(new Player("Sroka", 100000, "ten ktory to pisal"));
		addPlayer(new Player("szymek", 100000, "hehehe"));
		addPlayer(new Player("czesiek", 100000, "hahaha"));
		addPlayer(new Player("krzysiek", 100000, "hyhyhy"));
		addPlayer(new Player("heniek", 100000, "huehue"));
		addPlayer(new Player("zdzisiek", 100000, "husahusahu"));
		addPlayer(new Player("romek", 100000, "haaaaaaah"));
		addPlayer(new Player("davy", 100000, "jakis madry opis"));
	}

	public int size() {
		return playerMap.size();
	}

	public boolean isEmpty() {
		return playerMap.isEmpty();
	}

	public void addPlayer(Player newPlayer) throws ExistingNicknameException{
		if(playerMap.containsKey(newPlayer.getNickname())){
			throw new ExistingNicknameException();
		}
		playerMap.put(newPlayer.getNickname(), newPlayer);
	}
	
	public void updatePlayer(Player editedPlayer) {
		playerMap.put(editedPlayer.getNickname(), editedPlayer);
	}

	public Player getUserByNick(String nickname) throws NonExistingPlayerException {
		if(!playerMap.containsKey(nickname)){
			throw new NonExistingPlayerException();
		}
		return playerMap.get(nickname);
		
	}

	public Map<String, PlayerTO> searchPlayersByRank(Rank rank) {
		Map<String, PlayerTO> returnMap = new HashMap<>();
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			if (entry.getValue().getRank() == rank) {
				returnMap.put(entry.getKey(), makeTOFromPlayer(entry.getValue()));
			}
		}
		return returnMap;
	}

	public Map<String, PlayerTO> searchPlayersByGame(BoardGame boardGame) {
		Map<String, PlayerTO> returnMap = new HashMap<>();
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			List<BoardGame> gameList = entry.getValue().getOwnedGames();
			for (BoardGame game : gameList) {
				if (game.getName() == boardGame.getName()) {
					returnMap.put(entry.getKey(), makeTOFromPlayer(entry.getValue()));
				}
			}
		}
		return returnMap;
	}


	public void setPlayerDescription(String nickname, String description) throws NonExistingPlayerException {
		Player editedPlayer = this.getUserByNick(nickname);
		editedPlayer.setPlayerDescription(description);
		this.updatePlayer(editedPlayer);
	}

	public Map<String, PlayerTO> searchPlayersByAbilityTime(AbilityTime abilityTime) {
		Map<String, PlayerTO> returnMap = new HashMap<>();
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			AbilityTime matchedAbility = abilityTime.matchAbilityTime(entry.getValue().getAbilityTime());
			if (matchedAbility.getNumberOfDaysAvaliable() != 0) {
				returnMap.put(entry.getKey(), makeTOFromPlayer(entry.getValue()));
			}
		}
		//TODO CONVERT TO FILTER
		return returnMap;

	}

	public void addInvitationToPlayerByNickname(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		this.getUserByNick(nickname).addNewInvitation(challengeTO);
	}

	public void acceptInvitationByNickname(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		this.getUserByNick(nickname).acceptInvitation(challengeTO);
	}
	
	public void rejectInvitationByNickname(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		this.getUserByNick(nickname).rejectInvitation(challengeTO);
	}
	
	public void addThrownInvitationByNickname(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		this.getUserByNick(nickname).addThrowInvitation(challengeTO);
	}

	public void addScore(String nickname, int points){
		playerMap.get(nickname).addPoints(points);
	}
	
	public void addGameWhichTookPlace(String nickname, GameTO game){
		playerMap.get(nickname).addGameWhichTookPlace(game);
	}
}

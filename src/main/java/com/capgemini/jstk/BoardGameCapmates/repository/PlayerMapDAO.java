package com.capgemini.jstk.BoardGameCapmates.repository;

import com.capgemini.jstk.BoardGameCapmates.enums.Rank;
import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.TO.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.GameTO;
import com.capgemini.jstk.BoardGameCapmates.model.entity.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.entity.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class PlayerMapDAO {

	private Map<String, Player> playerMap;

	PlayerMapDAO() {
		this.playerMap = new HashMap<>();
	}

	@PostConstruct
	public void init() throws ExistingNicknameException {
		addPlayer(new Player("Sroka", 100000, "ten ktory to pisal"));
		addPlayer(new Player("szymek", 123, "hehehe"));
		addPlayer(new Player("czesiek", 42, "hahaha"));
		addPlayer(new Player("krzysiek", 1252, "hyhyhy"));
		addPlayer(new Player("heniek", 1521, "huehue"));
		addPlayer(new Player("zdzisiek", 124, "husahusahu"));
		addPlayer(new Player("romek", 554, "haaaaaaah"));
		addPlayer(new Player("god1", 100000, ""));
		addPlayer(new Player("god2", 100000, ""));
		addPlayer(new Player("god3", 100000, ""));
		addPlayer(new Player("god4", 100000, ""));
		addPlayer(new Player("god5", 100000, ""));
	}

	public void clear() {
		playerMap.clear();
	}

	public int size() {
		return playerMap.size();
	}

	public boolean isEmpty() {
		return playerMap.isEmpty();
	}

	public boolean addPlayer(Player newPlayer) throws ExistingNicknameException {
		if (playerMap.containsKey(newPlayer.getNickname())) {
			throw new ExistingNicknameException();
		}
		playerMap.put(newPlayer.getNickname(), newPlayer);
		return true;
	}

	public boolean updatePlayer(Player editedPlayer) throws NonExistingPlayerException {
		if (!playerMap.containsKey(editedPlayer.getNickname())) {
			throw new NonExistingPlayerException();
		}
		playerMap.put(editedPlayer.getNickname(), editedPlayer);
		return true;
	}

	public Player getUserByNick(String nickname) throws NonExistingPlayerException {
		if (!playerMap.containsKey(nickname)) {
			throw new NonExistingPlayerException();
		}
		return playerMap.get(nickname);
	}

	public List<Player> searchPlayersByRank(Rank expectedRank) {
		return playerMap.entrySet().stream().filter(e -> e.getValue().getRank().equals(expectedRank))
				.map(Map.Entry::getValue).collect(Collectors.toList());
	}

	public List<Player> searchPlayersByGame(BoardGame boardGame) {
		List<Player> returnList = new ArrayList<>();

		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			List<BoardGame> gameList = entry.getValue().getOwnedGames();
			for (BoardGame game : gameList) {
				if (game.getName() == boardGame.getName()) {
					returnList.add(entry.getValue());
				}
			}
		}
		return returnList;
	}

	public void setPlayerDescription(String nickname, String description)
			throws NonExistingPlayerException, ExistingNicknameException {
		Player editedPlayer = this.getUserByNick(nickname);
		editedPlayer.setPlayerDescription(description);
		this.updatePlayer(editedPlayer);
	}

	public List<Player> searchPlayersByAbilityTime(AbilityTime abilityTime) {
		List<Player> returnMap = new ArrayList<>();
		for (Map.Entry<String, Player> entry : playerMap.entrySet()) {
			AbilityTime matchedAbility = abilityTime.matchAbilityTime(entry.getValue().getAbilityTime());
			if (matchedAbility.getNumberOfDaysAvaliable() != 0) {
				returnMap.add(entry.getValue());
			}
		}
		return returnMap;
	}

	public void addInvitationToPlayerByNickname(String nickname, ChallengeTO challengeTO)
			throws NonExistingPlayerException {
		this.getUserByNick(nickname).addNewInvitation(challengeTO);
	}

	public void acceptInvitationByNickname(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		this.getUserByNick(nickname).acceptInvitation(challengeTO);
	}

	public void rejectInvitationByNickname(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		this.getUserByNick(nickname).rejectInvitation(challengeTO);
	}

	public void addThrownInvitationByNickname(String nickname, ChallengeTO challengeTO)
			throws NonExistingPlayerException {
		this.getUserByNick(nickname).addThrowInvitation(challengeTO);
	}

	public void addScore(String nickname, int points) {
		playerMap.get(nickname).addPoints(points);
	}

	public void addGameWhichTookPlace(String nickname, GameTO game) {
		playerMap.get(nickname).addGameWhichTookPlace(game);
	}
}

package com.capgemini.jstk.BoardGameCapmates.repository;

import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import static com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper.*;

@Repository
public class PlayerListDAO {

	private Map<String, Player> playerMap;

	
	PlayerListDAO() {
		this.playerMap = new HashMap<>();
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
		for (Map.Entry<String, Player> player : playerMap.entrySet()) {
			if (player.getKey().equals(nickname)) {
				return player.getValue();
			}
		}

		throw new NonExistingPlayerException();
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

	public void addInvitationToPlayerByNickname(String nickname, Integer challengeID) throws NonExistingPlayerException {
		//this.getUserByNick(nickname).addInvitation(challengeID);
	}

	public void acceptInvitationByNickname(String nickname, Integer challengeID) throws NonExistingPlayerException {
		//this.getUserByNick(nickname).acceptInvitation(challengeID);
	}
	
	public void rejectInvitationByNickname(String nickname, Integer challengeID) throws NonExistingPlayerException {
		//this.getUserByNick(nickname).rejectInvitation(challengeID);
	}
	
	public void addThrownInvitationByNickname(String nickname, Integer challengeID) throws NonExistingPlayerException {
		//this.getUserByNick(nickname).throwInvitation(challengeID);
	}
}

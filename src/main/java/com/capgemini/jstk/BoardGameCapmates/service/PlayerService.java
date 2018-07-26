package com.capgemini.jstk.BoardGameCapmates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.BoardGameCapmates.enums.ChallengeCreator;
import com.capgemini.jstk.BoardGameCapmates.enums.Rank;
import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper;
import com.capgemini.jstk.BoardGameCapmates.model.TO.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.GameTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.model.entity.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.entity.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.entity.Player;
import com.capgemini.jstk.BoardGameCapmates.repository.PlayerMapDAO;
import static com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

	private final PlayerMapDAO playerDAO;

	@Autowired
	PlayerService(PlayerMapDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public int getPlayersAmount() {
		return playerDAO.size();
	}

	public PlayerTO addPlayer(String nickname) throws ExistingNicknameException {
		PlayerTO newPlayerTO = new PlayerTO();
		newPlayerTO.setNickname(nickname);
		newPlayerTO.setOwnedGames(null);
		newPlayerTO.setPlayerDescription("");
		newPlayerTO.setRank(Rank.NEWBIE);
		Player newPlayer = PlayerMapper.makePlayerFromTO(newPlayerTO);
		playerDAO.addPlayer(newPlayer);
		return newPlayerTO;
	}

	public PlayerTO getPlayerInformations(String nickname) throws NonExistingPlayerException {
		Player player = playerDAO.getUserByNick(nickname);
		return makeTOFromPlayer(player);

	}

	public List<PlayerTO> searchPlayersByRank(Rank rank) {
		return playerDAO.searchPlayersByRank(rank).stream().map(PlayerMapper::makeTOFromPlayer)
				.collect(Collectors.toList());
	}

	public List<PlayerTO> searchPlayersByGame(BoardGame game) {
		return playerDAO.searchPlayersByGame(game).stream().map(PlayerMapper::makeTOFromPlayer)
				.collect(Collectors.toList());
	}

	public void setPlayerDescription(String nickname, String description)
			throws NonExistingPlayerException, ExistingNicknameException {
		playerDAO.setPlayerDescription(nickname, description);
	}

	public List<PlayerTO> searchPlayersByAbilityTime(AbilityTime abilityTime) {
		return playerDAO.searchPlayersByAbilityTime(abilityTime).stream().map(PlayerMapper::makeTOFromPlayer)
				.collect(Collectors.toList());
	}

	public void acceptInvitation(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		playerDAO.getUserByNick(nickname).acceptInvitation(challengeTO);
	}

	public void rejectInvitation(String nickname, ChallengeTO challengeTO) throws NonExistingPlayerException {
		playerDAO.getUserByNick(nickname).rejectInvitation(challengeTO);
	}

	public void invitePlayers(ChallengeTO challengeTO) throws NonExistingPlayerException {
		List<String> playerNicknamesList = challengeTO.getListOfPlayerNicknames();
		String ownerNickname = challengeTO.getOwnerNickname();
		for (int i = 0; i < playerNicknamesList.size(); i++) {
			if (playerNicknamesList.get(i).equals(ownerNickname)) {
				playerDAO.getUserByNick(playerNicknamesList.get(i)).addThrowInvitation(challengeTO);
			} else {
				playerDAO.getUserByNick(playerNicknamesList.get(i)).addNewInvitation(challengeTO);
			}
		}

	}

	public List<ChallengeTO> getChallengesAcceptedByPlayer(String myNickname) throws NonExistingPlayerException {
		return playerDAO.getUserByNick(myNickname).getAcceptedInvitations();
	}

	public List<ChallengeTO> getChallengesThrownAtOtherUsers(String myNickname) throws NonExistingPlayerException {
		return playerDAO.getUserByNick(myNickname).getAcceptedInvitations();
	}

	public List<ChallengeTO> getChallengesThrownByOtherUsers(String myNickname) throws NonExistingPlayerException {
		return playerDAO.getUserByNick(myNickname).getNewInvitations();
	}

	public List<ChallengeTO> getChallengesThrownBySystem(String myNickname) throws NonExistingPlayerException {
		List<ChallengeTO> returnList = new ArrayList<>();
		for (ChallengeTO challenge : playerDAO.getUserByNick(myNickname).getNewInvitations()) {
			if (challenge.getChallengeCreator() == ChallengeCreator.SYSTEM) {
				returnList.add(challenge);
			}
		}
		return returnList;
	}

	public List<PlayerTO> checkOpponentInfo(ChallengeTO challengeTO) throws NonExistingPlayerException {
		List<PlayerTO> playersList = new ArrayList<>();
		List<String> playersNicknames = challengeTO.getListOfPlayerNicknames();
		for (int i = 0; i < challengeTO.getNumberOfPlayers(); i++) {
			if (!challengeTO.getOwnerNickname().equals(playersNicknames.get(i))) {
				playersList.add(PlayerMapper.makeTOFromPlayer(playerDAO.getUserByNick(playersNicknames.get(i))));
			}
		}
		return playersList;
	}

	public void addScore(String nickname, int points) {
		playerDAO.addScore(nickname, points);
	}

	public void addGameWhichTookPlace(String nickname, GameTO game) {
		playerDAO.addGameWhichTookPlace(nickname, game);
	}
}

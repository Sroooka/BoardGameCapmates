package com.capgemini.jstk.BoardGameCapmates;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.BoardGameCapmates.enums.Rank;
import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.TO.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.TO.GameTO;
import com.capgemini.jstk.BoardGameCapmates.model.entity.AbilityTime;
import com.capgemini.jstk.BoardGameCapmates.model.entity.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.entity.Player;
import com.capgemini.jstk.BoardGameCapmates.repository.PlayerMapDAO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerServiceTest {

	@InjectMocks
	PlayerService playerService;

	@Mock
	PlayerMapDAO playerMapDAO;

	@SuppressWarnings("deprecation")
	@Test
	public void shouldReturnPlayersAmount() {
		// given
		Mockito.when(playerMapDAO.size()).thenReturn(500);
		// when
		int amount = playerService.getPlayersAmount();
		// then
		Mockito.verify(playerMapDAO).size();
		assertEquals(amount, 500);
	}

	@Test
	public void shouldAddPlayer() throws ExistingNicknameException, NonExistingPlayerException {
		// given
		// when
		playerService.addPlayer("Sroka");
		// then
		Mockito.verify(playerMapDAO).addPlayer(Mockito.any());
	}

	@Test
	public void shouldReturnPlayerInfo() throws NonExistingPlayerException, ExistingNicknameException {
		// given
		Player player = new Player("Sroka");
		Mockito.when(playerMapDAO.getUserByNick(Mockito.anyString())).thenReturn(player);
		// when
		playerService.getPlayerInformations("Sroka");
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.anyString());
	}

	@Test
	public void shouldSearchPlayersByRank() {
		// given
		Player player = new Player();
		List<Player> list = new ArrayList<>();
		list.add(player);
		Mockito.when(playerMapDAO.searchPlayersByRank(Mockito.any())).thenReturn(list);
		// when
		playerService.searchPlayersByRank(Rank.GOD_OF_BOARD_GAMES);
		// then
		Mockito.verify(playerMapDAO).searchPlayersByRank(Mockito.any());
	}

	@Test
	public void shouldSearchPlayersByGame() {
		// given
		List<Player> list = new ArrayList<>();
		Mockito.when(playerMapDAO.searchPlayersByGame(Mockito.any())).thenReturn(list);
		// when
		playerService.searchPlayersByGame(new BoardGame());
		// then
		Mockito.verify(playerMapDAO).searchPlayersByGame(Mockito.any());
	}

	@Test
	public void shouldSetPlayerDescription() throws NonExistingPlayerException, ExistingNicknameException {
		// given
		Mockito.when(playerMapDAO.searchPlayersByGame(Mockito.any())).thenReturn(null);
		// when
		playerService.setPlayerDescription("Sroka", "Fajny opis");
		// then
		Mockito.verify(playerMapDAO).setPlayerDescription(Mockito.anyString(), Mockito.anyString());
	}

	@Test
	public void shouldSearchPlayerByAbilityTime() throws NonExistingPlayerException {
		// given
		AbilityTime ability = new AbilityTime();
		List<Player> list = new ArrayList<>();
		Mockito.when(playerMapDAO.searchPlayersByAbilityTime(Mockito.any())).thenReturn(list);
		// when
		playerService.searchPlayersByAbilityTime(ability);
		// then
		Mockito.verify(playerMapDAO).searchPlayersByAbilityTime(Mockito.any());
	}

	@Test
	public void shouldAcceptInvitation() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		ChallengeTO challengeTO = new ChallengeTO();
		// when
		playerService.acceptInvitation("Sroka", challengeTO);
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldRejectInvitation() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		ChallengeTO challengeTO = new ChallengeTO();
		// when
		playerService.rejectInvitation("Sroka", challengeTO);
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldInvitePlayers() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		ChallengeTO challengeTO = new ChallengeTO();
		List<String> playerList = new ArrayList<>();
		playerList.add("Sroka");
		challengeTO.setListOfPlayerNicknames(playerList);
		challengeTO.setNumberOfPlayers(1);
		// when
		playerService.invitePlayers(challengeTO);
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldReturnChallengesAcceptedByPlayer() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		// when
		playerService.getChallengesAcceptedByPlayer("Sroka");
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldReturnChallengesThrownAtOtherUsers() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		// when
		playerService.getChallengesThrownAtOtherUsers("Sroka");
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldReturnChallengesThrownByOtherUsers() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		// when
		playerService.getChallengesThrownByOtherUsers("Sroka");
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldReturnChallengesThrownBySystem() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		List<ChallengeTO> newChallengesList = new ArrayList<>();
		ChallengeTO challengeTO = new ChallengeTO();
		newChallengesList.add(challengeTO);
		player.setNewInvitations(newChallengesList);
		// when
		playerService.getChallengesThrownBySystem("Sroka");
		// then
		Mockito.verify(playerMapDAO).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldCheckOpponentInfo() throws NonExistingPlayerException {
		// given
		Player player = new Player();
		Mockito.when(playerMapDAO.getUserByNick(Mockito.any())).thenReturn(player);
		List<String> playerList = new ArrayList<>();
		playerList.add("Sroka");
		ChallengeTO challengeTO = new ChallengeTO();
		challengeTO.setListOfPlayerNicknames(playerList);
		challengeTO.setNumberOfPlayers(1);
		challengeTO.setOwnerNickname("nope");
		// when
		playerService.checkOpponentInfo(challengeTO);
		// then
		Mockito.verify(playerMapDAO, atLeast(1)).getUserByNick(Mockito.any());
	}

	@Test
	public void shouldAddScore() throws NonExistingPlayerException {
		// given
		// when
		playerService.addScore("Sroka", 5);
		// then
		Mockito.verify(playerMapDAO).addScore(Mockito.anyString(), Mockito.anyInt());
	}

	@Test
	public void shouldAddGameWhichTookPlace() {
		// given
		GameTO game = new GameTO();
		// when
		playerService.addGameWhichTookPlace("Sroka", game);
		// then
		Mockito.verify(playerMapDAO).addGameWhichTookPlace(Mockito.anyString(), Mockito.any());
	}
}
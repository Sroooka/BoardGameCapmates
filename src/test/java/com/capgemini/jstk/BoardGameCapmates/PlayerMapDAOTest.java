package com.capgemini.jstk.BoardGameCapmates;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.atLeast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.capgemini.jstk.BoardGameCapmates.exceptions.ExistingNicknameException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.model.*;
import com.capgemini.jstk.BoardGameCapmates.repository.PlayerMapDAO;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@SuppressWarnings({ "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerMapDAOTest {

	@Autowired
	PlayerMapDAO playerMapDAO;

	@Before
	public void setUp() throws ExistingNicknameException {
		playerMapDAO.clear();
		playerMapDAO.init();
	}

	@Test
	public void shouldReturnCorrectSize() throws ExistingNicknameException {
		// given
		// when
		int amount = playerMapDAO.size();
		// then
		assertEquals(12, amount);
	}

	@Test
	public void shouldIsEmptyReturnCorrectValue() throws ExistingNicknameException {
		// given
		// when
		// then
		assertEquals(false, playerMapDAO.isEmpty());
	}

	@Test
	public void shouldAddPlayer() throws ExistingNicknameException {
		// given
		Player player = new Player("new player");
		// when
		boolean success = playerMapDAO.addPlayer(player);
		// then
		assertEquals(success, true);
	}

	@Test(expected = ExistingNicknameException.class)
	public void shouldAddExistingPlayer() throws ExistingNicknameException {
		// given
		Player player = new Player("Sroka");
		// when
		playerMapDAO.addPlayer(player);
		// then
	}

	@Test
	public void shouldUpdateExistingPlayer() throws NonExistingPlayerException {
		// given
		Player player = new Player("krzysiek");
		player.setPlayerDescription("New description");
		// when
		boolean success = playerMapDAO.updatePlayer(player);
		// then
		assertEquals(success, true);
	}

	@Test(expected = NonExistingPlayerException.class)
	public void shouldUpdateNonExistingPlayer() throws NonExistingPlayerException {
		// given
		Player player = new Player("NonExistingSroka");
		player.setPlayerDescription("New description");
		// when
		playerMapDAO.updatePlayer(player);
		// then
	}

	@Test
	public void shouldGetExistingPlayerByNick() throws NonExistingPlayerException {
		// given
		Player player;
		// when
		player = playerMapDAO.getUserByNick("Sroka");
		// then
		assertEquals(player.getNickname(), "Sroka");
		assertEquals(player.getPoints(), 100000);
		assertEquals(player.getRank(), Rank.GOD_OF_BOARD_GAMES);
		assertEquals(player.getPlayerDescription(), "ten ktory to pisal");
	}

	@Test(expected = NonExistingPlayerException.class)
	public void shouldGetNonExistingPlayerByNick() throws NonExistingPlayerException {
		// given
		// when
		playerMapDAO.getUserByNick("aokdoaksdosakdoasjfoasjf");
		// then
	}

	@Test
	public void shouldFindPlayersByRank() {
		// given
		List<Player> playersWithSpecifiedRank = new ArrayList<>();
		// when
		playersWithSpecifiedRank = playerMapDAO.searchPlayersByRank(Rank.GOD_OF_BOARD_GAMES);
		// then
		assertEquals(playersWithSpecifiedRank.size(), 6);
		for (int i = 0; i < playersWithSpecifiedRank.size(); i++) {
			assertTrue(playersWithSpecifiedRank.get(i).getRank().equals(Rank.GOD_OF_BOARD_GAMES));
		}
	}

	@Test
	public void shouldFindPlayersByOwnedGames() throws ExistingNicknameException {
		// given
		Player player1 = new Player("Player1", 1000, "");
		List<BoardGame> player1BoardGames = new ArrayList<>();
		player1BoardGames.add(new BoardGame("Chess", 2, 2));
		player1BoardGames.add(new BoardGame("X and O", 2, 2));
		player1BoardGames.add(new BoardGame("Dragons and wizards", 2, 8));
		Player player2 = new Player("Player2", 2000, "");

		List<BoardGame> player2BoardGames = new ArrayList<>();
		player2BoardGames.add(new BoardGame("Chess", 2, 2));
		player2BoardGames.add(new BoardGame("Dragons and wizards", 2, 8));
		player1.setOwnedGames(player1BoardGames);
		player2.setOwnedGames(player2BoardGames);
		playerMapDAO.addPlayer(player1);
		playerMapDAO.addPlayer(player2);

		BoardGame expectedBoardGame = new BoardGame("Dragons and wizards", 2, 8);
		// when
		List<Player> foundPlayers = playerMapDAO.searchPlayersByGame(expectedBoardGame);
		// then
		assertEquals(foundPlayers.size(), 2);
		for (int i = 0; i < foundPlayers.size(); i++) {
			assertTrue(foundPlayers.get(i).getNickname().equals("Player1")
					|| foundPlayers.get(i).getNickname().equals("Player2"));
		}
	}

	@Test
	public void shouldSetPlayerDescription() throws NonExistingPlayerException, ExistingNicknameException {
		// given
		// when
		playerMapDAO.setPlayerDescription("Sroka", "aaaaa");
		// then
		assertTrue(playerMapDAO.getUserByNick("Sroka").getPlayerDescription().equals("aaaaa"));
	}

	@Test(expected = NonExistingPlayerException.class)
	public void shouldSetNonExistingPlayerDescription() throws NonExistingPlayerException, ExistingNicknameException {
		// given
		// when
		playerMapDAO.setPlayerDescription("blablabla", "aaaaa");
		// then
	}

	@Test
	public void shouldFindPlayersWithTheSameAbilityTime() throws NonExistingPlayerException, ExistingNicknameException {
		// given
		Player player1 = new Player("Player1", 1000, "");
		AbilityTime player1Ability = new AbilityTime();
		player1Ability.setAbility(1, 7, 0, 15, 0);
		player1Ability.setAbility(2, 10, 30, 15, 45);
		player1.setAbilityTime(player1Ability);
		Player player2 = new Player("Player2", 2000, "");
		AbilityTime player2Ability = new AbilityTime();
		player2Ability.setAbility(1, 9, 20, 12, 0);
		player2Ability.setAbility(5, 10, 20, 15, 15);
		player2.setAbilityTime(player2Ability);

		playerMapDAO.addPlayer(player1);
		playerMapDAO.addPlayer(player2);

		AbilityTime expectedAbility = new AbilityTime();
		expectedAbility.setAbility(1, 10, 30, 11, 15);
		// when
		List<Player> playerWithMatchedAbility = playerMapDAO.searchPlayersByAbilityTime(expectedAbility);
		// then
		assertEquals(playerWithMatchedAbility.size(), 2);
		for (int i = 0; i < playerWithMatchedAbility.size(); i++) {
			assertTrue(playerWithMatchedAbility.get(i).getNickname().equals("Player1")
					|| playerWithMatchedAbility.get(i).getNickname().equals("Player2"));
		}

	}

	@Test
	public void shouldFindPlayersWithTheSameAbilityTimeWhenNoCooperation()
			throws NonExistingPlayerException, ExistingNicknameException {
		// given
		Player player1 = new Player("Player1", 1000, "");
		AbilityTime player1Ability = new AbilityTime();
		player1Ability.setAbility(1, 7, 0, 15, 0);
		player1Ability.setAbility(2, 10, 30, 15, 45);
		player1.setAbilityTime(player1Ability);
		Player player2 = new Player("Player2", 2000, "");
		AbilityTime player2Ability = new AbilityTime();
		player2Ability.setAbility(1, 9, 20, 12, 0);
		player2Ability.setAbility(5, 10, 20, 15, 15);
		player2.setAbilityTime(player2Ability);

		playerMapDAO.addPlayer(player1);
		playerMapDAO.addPlayer(player2);

		AbilityTime expectedAbility = new AbilityTime();
		expectedAbility.setAbility(5, 15, 16, 23, 59);
		// when
		List<Player> playerWithMatchedAbility = playerMapDAO.searchPlayersByAbilityTime(expectedAbility);
		// then
		assertTrue(playerWithMatchedAbility.isEmpty());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenAddingBadIndexesInSearchingByTime()
			throws NonExistingPlayerException, ExistingNicknameException {
		// given
		Player player1 = new Player("Player1", 1000, "");
		AbilityTime player1Ability = new AbilityTime();
		player1Ability.setAbility(1, 7, 0, 15, 0);
		player1.setAbilityTime(player1Ability);
		playerMapDAO.addPlayer(player1);

		AbilityTime expectedAbility = new AbilityTime();
		expectedAbility.setAbility(5, 15, 30, 12, 10);
		// when
		playerMapDAO.searchPlayersByAbilityTime(expectedAbility);
		// then
	}

	@Test
	public void shouldAddNewInvitationToPlayer() throws NonExistingPlayerException {
		// given
		ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeCreator(ChallengeCreator.PLAYER);
		challenge.setOwnerNickname("Someone");
		challenge.setNumberOfPlayers(4);
		challenge.setGame(new BoardGame("Dragons and wizards", 2, 6));
		// when
		playerMapDAO.addInvitationToPlayerByNickname("Sroka", challenge);
		ChallengeTO playerChallenge = playerMapDAO.getUserByNick("Sroka").getNewInvitations().get(0);
		// then
		assertTrue(playerChallenge.getChallengeCreator().equals(ChallengeCreator.PLAYER));
		assertTrue(playerChallenge.getOwnerNickname().equals("Someone"));
		assertTrue(playerChallenge.getNumberOfPlayers() == 4);
		assertTrue(playerChallenge.getGame().getName().equals("Dragons and wizards"));
	}

	@Test
	public void shouldPlayerAcceptInvitationByNickname() throws NonExistingPlayerException {
		// given
		ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeCreator(ChallengeCreator.PLAYER);
		challenge.setOwnerNickname("Someone");
		challenge.setNumberOfPlayers(4);
		challenge.setGame(new BoardGame("Dragons and wizards", 2, 6));
		playerMapDAO.addInvitationToPlayerByNickname("Sroka", challenge);
		// when
		playerMapDAO.acceptInvitationByNickname("Sroka", challenge);
		ChallengeTO playerChallenge = playerMapDAO.getUserByNick("Sroka").getAcceptedInvitations().get(0);
		// then
		assertTrue(playerMapDAO.getUserByNick("Sroka").getNewInvitations().isEmpty());
		assertTrue(playerChallenge.getChallengeCreator().equals(ChallengeCreator.PLAYER));
		assertTrue(playerChallenge.getOwnerNickname().equals("Someone"));
		assertTrue(playerChallenge.getNumberOfPlayers() == 4);
		assertTrue(playerChallenge.getGame().getName().equals("Dragons and wizards"));
	}

	@Test
	public void shouldPlayerRejectInvitationByNickname() throws NonExistingPlayerException {
		// given
		ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeCreator(ChallengeCreator.PLAYER);
		challenge.setOwnerNickname("Someone");
		challenge.setNumberOfPlayers(4);
		challenge.setGame(new BoardGame("Dragons and wizards", 2, 6));
		playerMapDAO.addInvitationToPlayerByNickname("Sroka", challenge);
		// when
		playerMapDAO.rejectInvitationByNickname("Sroka", challenge);
		ChallengeTO playerChallenge = playerMapDAO.getUserByNick("Sroka").getRejectedInvitations().get(0);
		// then
		assertTrue(playerMapDAO.getUserByNick("Sroka").getNewInvitations().isEmpty());
		assertTrue(playerChallenge.getChallengeCreator().equals(ChallengeCreator.PLAYER));
		assertTrue(playerChallenge.getOwnerNickname().equals("Someone"));
		assertTrue(playerChallenge.getNumberOfPlayers() == 4);
		assertTrue(playerChallenge.getGame().getName().equals("Dragons and wizards"));
	}

	@Test
	public void shouldPlayerAddThrownInvitationByNickname() throws NonExistingPlayerException {
		// given
		ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeCreator(ChallengeCreator.PLAYER);
		challenge.setOwnerNickname("Someone");
		challenge.setNumberOfPlayers(4);
		challenge.setGame(new BoardGame("Dragons and wizards", 2, 6));

		// when
		playerMapDAO.addThrownInvitationByNickname("Sroka", challenge);
		ChallengeTO playerChallenge = playerMapDAO.getUserByNick("Sroka").getThrownInvitations().get(0);
		// then
		assertTrue(playerMapDAO.getUserByNick("Sroka").getNewInvitations().isEmpty());
		assertTrue(playerChallenge.getChallengeCreator().equals(ChallengeCreator.PLAYER));
		assertTrue(playerChallenge.getOwnerNickname().equals("Someone"));
		assertTrue(playerChallenge.getNumberOfPlayers() == 4);
		assertTrue(playerChallenge.getGame().getName().equals("Dragons and wizards"));
	}

	@Test
	public void shouldAddScoreToPlayer() throws NonExistingPlayerException {
		// given
		// when
		playerMapDAO.addScore("Sroka", 100000);
		// then
		assertEquals(playerMapDAO.getUserByNick("Sroka").getPoints(), 200000);
	}

	@Test
	public void shouldAddGameWhichTookPlaceToPlayer() throws NonExistingPlayerException {
		// given
		List<String> list = new ArrayList<>();
		list.add("Sroka");
		GameTO game = new GameTO(new BoardGame("Sroka bez oka", 1, 1), 1, list);
		// when
		playerMapDAO.addGameWhichTookPlace("Sroka", game);
		GameTO gameCheck = playerMapDAO.getUserByNick("Sroka").getMyPlayedGamesInChallenges().get(0);
		// then
		assertEquals(gameCheck.getBoardGame().getName(), "Sroka bez oka");
	}
}
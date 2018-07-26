package com.capgemini.jstk.BoardGameCapmates;

import static junit.framework.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jstk.BoardGameCapmates.enums.ChallengeCreator;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingChallengeException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.model.TO.ChallengeTO;
import com.capgemini.jstk.BoardGameCapmates.model.entity.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.repository.ChallengeMapDAO;
import com.capgemini.jstk.BoardGameCapmates.service.ChallengeService;
import com.capgemini.jstk.BoardGameCapmates.service.PlayerService;

@SuppressWarnings({ "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeServiceTest {

	@InjectMocks
	ChallengeService challengeService;

	@Mock
	ChallengeMapDAO challengeMapDAO;

	@Mock
	PlayerService playerService;

	@Test
	public void shouldReturnPlayersAmount() {
		// given
		Mockito.when(challengeMapDAO.size()).thenReturn(500);
		// when
		int amount = challengeService.getChallengesAmount();
		// then
		Mockito.verify(challengeMapDAO).size();
		assertEquals(amount, 500);
	}

	@Test
	public void shouldCreateNewChallenge() throws NotSelectedBoardGame, TooMuchPlayersException,
			NotEnoughPlayersException, NonExistingChallengeException, NonExistingPlayerException {
		// given
		ChallengeTO challenge = new ChallengeTO();
		// when
		challenge = challengeService.createNewChallenge("Sroka", new BoardGame("Turbokarty", 2, 2), LocalTime.of(10, 0),
				LocalTime.of(15, 0), "Hello all players", "Opponent");
		// then
		assertEquals(challenge.getChallengeCreator(), ChallengeCreator.PLAYER);
		assertEquals(challenge.getGame().getName(), "Turbokarty");
		assertEquals(challenge.getListOfPlayerNicknames().get(0), "Opponent");
		assertEquals(challenge.getInvitationMessage(), "Hello all players");
	}

	@Test
	public void shouldAcceptChallenge() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException {
		// given
		List<String> list = new ArrayList<>();
		list.add("Sroka");
		ChallengeTO challenge = new ChallengeTO();
		challenge.setGame(new BoardGame("Poker", 1, 1));
		challenge.setListOfPlayerNicknames(list);
		// when
		challengeService.acceptChallenge("Sroka", challenge, "comment");
		// then
		Mockito.verify(challengeMapDAO).acceptChallenge(Mockito.anyString(), Mockito.any(), Mockito.anyString());
		;
	}

	@Test
	public void shouldRejectChallenge() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException {
		// given
		List<String> list = new ArrayList<>();
		list.add("Sroka");
		ChallengeTO challenge = new ChallengeTO();
		challenge.setGame(new BoardGame("Poker", 1, 1));
		challenge.setListOfPlayerNicknames(list);
		// when
		challengeService.rejectChallenge("Sroka", challenge, "comment");
		// then
		Mockito.verify(challengeMapDAO).rejectChallenge(Mockito.anyString(), Mockito.any(), Mockito.anyString());
		;
	}

	@Test
	public void shouldGetChallengesThrownBySystem() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException, NonExistingPlayerException {
		// given
		Mockito.when(playerService.getChallengesThrownBySystem(Mockito.anyString())).thenReturn(null);
		// when
		challengeService.getChallengesThrownBySystem("Sroka");
		// then
		Mockito.verify(playerService).getChallengesThrownBySystem(Mockito.anyString());
	}

	@Test
	public void shouldGetChallengesThrownByOtherUsers() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException, NonExistingPlayerException {
		// given
		Mockito.when(playerService.getChallengesThrownByOtherUsers(Mockito.anyString())).thenReturn(null);
		// when
		challengeService.getChallengesThrownByOtherUsers("Sroka");
		// then
		Mockito.verify(playerService).getChallengesThrownByOtherUsers(Mockito.anyString());
	}

	@Test
	public void shouldGetChallengesThrownAtOtherUsers() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException, NonExistingPlayerException {
		// given
		Mockito.when(playerService.getChallengesThrownAtOtherUsers(Mockito.anyString())).thenReturn(null);
		// when
		challengeService.getChallengesThrownAtOtherUsers("Sroka");
		// then
		Mockito.verify(playerService).getChallengesThrownAtOtherUsers(Mockito.anyString());
	}

	@Test
	public void shouldGetChallengesAcceptedByPlayer() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException, NonExistingPlayerException {
		// given
		Mockito.when(playerService.getChallengesAcceptedByPlayer(Mockito.anyString())).thenReturn(null);
		// when
		challengeService.getChallengesAcceptedByPlayer("Sroka");
		// then
		Mockito.verify(playerService).getChallengesAcceptedByPlayer(Mockito.anyString());
	}

	@Test
	public void shouldGetAllOpponentChallenges() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException, NonExistingPlayerException {
		// given
		Mockito.when(challengeMapDAO.getAllOpponentChallenges(Mockito.anyString())).thenReturn(null);
		// when
		challengeService.getAllOpponentChallenges("Sroka");
		// then
		Mockito.verify(challengeMapDAO).getAllOpponentChallenges(Mockito.anyString());
	}

	@Test
	public void shouldCheckOpponentInfo() throws NonExistingChallengeException, NotSelectedBoardGame,
			TooMuchPlayersException, NotEnoughPlayersException, NonExistingPlayerException {
		// given
		Mockito.when(playerService.checkOpponentInfo(Mockito.any())).thenReturn(null);
		ChallengeTO challenge = new ChallengeTO();
		// when
		challengeService.checkOpponentInfo(challenge);
		// then
		Mockito.verify(playerService).checkOpponentInfo(Mockito.any());
	}

	@Test
	public void shouldStartGame() {
		// given
		Mockito.when(challengeMapDAO.StartGame(Mockito.any())).thenReturn(null);
		ChallengeTO challenge = new ChallengeTO();
		// when
		challengeMapDAO.StartGame(challenge);
		// then
		Mockito.verify(challengeMapDAO).StartGame(Mockito.any());
	}

	@Test
	public void shouldCommentGameWhichTookPlace() {
		// given
		// when
		ChallengeTO challenge = new ChallengeTO();
		challengeMapDAO.commentGameWhichTookPlace("Sroka", challenge, "comment");
		// then
		Mockito.verify(challengeMapDAO).commentGameWhichTookPlace(Mockito.anyString(), Mockito.any(),
				Mockito.anyString());
	}

}

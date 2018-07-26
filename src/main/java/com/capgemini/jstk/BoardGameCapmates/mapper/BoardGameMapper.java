package com.capgemini.jstk.BoardGameCapmates.mapper;

import org.springframework.stereotype.Component;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NotEnoughPlayersException;
import com.capgemini.jstk.BoardGameCapmates.exceptions.NotSelectedBoardGame;
import com.capgemini.jstk.BoardGameCapmates.exceptions.TooMuchPlayersException;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGameTO;

@Component
public class BoardGameMapper {
	public static BoardGame makeBoardGameFromTO(BoardGameTO boardGameTO)
			throws NotSelectedBoardGame, TooMuchPlayersException, NotEnoughPlayersException {
		BoardGame boardGame = new BoardGame();
		boardGame.setMaxPlayers(boardGameTO.getMaxPlayers());
		boardGame.setMinPlayers(boardGameTO.getMinPlayers());
		boardGame.setName(boardGameTO.getName());
		return boardGame;
	}

	public static BoardGameTO makeTOFromBoardGame(BoardGame boardGame) {
		BoardGameTO boardGameTO = new BoardGameTO();
		boardGameTO.setMaxPlayers(boardGame.getMaxPlayers());
		boardGameTO.setMinPlayers(boardGame.getMinPlayers());
		boardGameTO.setName(boardGame.getName());
		return boardGameTO;
	}
}

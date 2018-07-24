package com.capgemini.jstk.BoardGameCapmates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.BoardGameCapmates.exceptions.NonExistingPlayerException;
import com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper;
import com.capgemini.jstk.BoardGameCapmates.model.BoardGame;
import com.capgemini.jstk.BoardGameCapmates.model.Player;
import com.capgemini.jstk.BoardGameCapmates.model.PlayerTO;
import com.capgemini.jstk.BoardGameCapmates.model.Rank;
import com.capgemini.jstk.BoardGameCapmates.repository.PlayerListDAO;
import static com.capgemini.jstk.BoardGameCapmates.mapper.PlayerMapper.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {

	private final PlayerListDAO playerDAO;

	@Autowired
	PlayerService(PlayerListDAO playerDAO) {
		System.out.println("Im in player service constructor");
		this.playerDAO = playerDAO;

	}

	
	

	public int getPlayersAmount() {
		return playerDAO.size();
	}

	public PlayerTO addPlayer(String nickname){
        PlayerTO newPlayerTO = new PlayerTO();
        newPlayerTO.setNickname(nickname);
        newPlayerTO.setOwnedGames(null);
        newPlayerTO.setPlayerDescription("");
        newPlayerTO.setRank(Rank.NEWBIE);

        
        Player newPlayer = PlayerMapper.makePlayerFromTO(newPlayerTO);
        playerDAO.updatePlayer(newPlayer);
        
        return newPlayerTO;
    }
	
	public PlayerTO getPlayerInformations(String nickname) throws NonExistingPlayerException{
            Player player = playerDAO.getUserByNick(nickname);
            return makeTOFromPlayer(player);

	}

	public Map<String, PlayerTO> searchPlayersByRank(Rank rank) {
		return playerDAO.searchPlayersByRank(rank);
	}




	public Map<String, PlayerTO> searchPlayersByGame(BoardGame game) {
		return playerDAO.searchPlayersByGame(game);
	}
}

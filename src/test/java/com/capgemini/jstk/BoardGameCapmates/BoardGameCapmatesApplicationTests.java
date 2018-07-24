package com.capgemini.jstk.BoardGameCapmates;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.*;
import com.capgemini.jstk.BoardGameCapmates.model.*;

@SuppressWarnings({ "deprecation" })
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardGameCapmatesApplicationTests {

	@Test
	public void shouldRankReturnRightValue() {
		Rank newbie = Rank.NEWBIE;
		Rank weakling = Rank.WEAKLING;
		Rank beginner = Rank.BEGINNER;
		Rank experiencedBeginner = Rank.EXPERIENCED_BEGINNER;
		Rank middlebrow = Rank.MIDDLEBROW;
		Rank experiencedMiddlebrow = Rank.EXPERIENCED_MIDDLEBORW;
		Rank advanced = Rank.ADVANCED;
		Rank professional = Rank.PROFESSIONAL;
		Rank master = Rank.MASTER;
		Rank ultraMaster = Rank.ULTRA_MASTER;
		Rank ultraTurboMaster = Rank.ULTRA_TURBO_MASTER;
		Rank godOfBoardGames = Rank.GOD_OF_BOARD_GAMES;
		assertEquals(newbie.getPointsRequired(), 0);
		assertEquals(weakling.getPointsRequired(), 10);
		assertEquals(beginner.getPointsRequired(), 20);
		assertEquals(experiencedBeginner.getPointsRequired(), 40);
		assertEquals(middlebrow.getPointsRequired(), 80);
		assertEquals(experiencedMiddlebrow.getPointsRequired(), 160);
		assertEquals(advanced.getPointsRequired(), 320);
		assertEquals(professional.getPointsRequired(), 640);
		assertEquals(master.getPointsRequired(), 1280);
		assertEquals(ultraMaster.getPointsRequired(), 2560);
		assertEquals(ultraTurboMaster.getPointsRequired(), 5120);
		assertEquals(godOfBoardGames.getPointsRequired(), 99999);
	}

}

package com.app;

import com.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Shwetha on 28-12-2018.
 */
public class RoundEvaluatorTest {

    @Test
    public void given_2_Cards_Of_Same_Value_Then_Should_Return_Game_Status_As_War() {
        Card diamond_Jack_Card = new Card(SuiteValue.JACK, SuiteType.Diamond, Color.BLACK);
        Card heart_Jack_Card = new Card(SuiteValue.JACK, SuiteType.Heart, Color.BLACK);

        Player player1 = new Player("Shwetha");
        Player player2 = new Player("Nelson");
        Move player1MoveDetails = new Move(player1, diamond_Jack_Card);
        Move player2MoveDetails = new Move(player2, heart_Jack_Card);

        List<Move> moveList = new ArrayList<>();

        moveList.add(player1MoveDetails);
        moveList.add(player2MoveDetails);
        GameStatus status = RoundEvaluator.getGameStatus(moveList);
        assertThat(status).isEqualTo(GameStatus.War);

    }

    @Test
    public void given_2_Cards_Of_Different_Value_Then_Should_Return_Game_Status_As_Normal() {
        Card diamond_King_Card = new Card(SuiteValue.KING, SuiteType.Diamond, Color.BLACK);
        Card heart_Jack_Card = new Card(SuiteValue.JACK, SuiteType.Heart, Color.BLACK);

        Player player1 = new Player("Shwetha");
        Player player2 = new Player("Nelson");
        Move player1MoveDetails = new Move(player1, diamond_King_Card);
        Move player2MoveDetails = new Move(player2, heart_Jack_Card);

        List<Move> moveList = new ArrayList<>();

        moveList.add(player1MoveDetails);
        moveList.add(player2MoveDetails);
        GameStatus status = RoundEvaluator.getGameStatus(moveList);
        assertThat(status).isEqualTo(GameStatus.Playable);

    }



}
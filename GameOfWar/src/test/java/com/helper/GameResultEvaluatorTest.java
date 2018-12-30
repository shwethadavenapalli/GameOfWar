package com.helper;

import com.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Shwetha on 28-12-2018.
 */
public class GameResultEvaluatorTest {

    @Test
    public void given_MoveDetails_Get_The_Winner(){
        Card diamond_2_Card = new Card(SuiteValue.TWO, SuiteType.Diamond, Color.BLACK);
        Card heart_3_Card = new Card(SuiteValue.THREE, SuiteType.Heart, Color.BLACK);

        Player player1 = new Player("Shwetha");
        player1.getCardStock().add(diamond_2_Card);
        Player player2 = new Player("Nelson");
        player2.getCardStock().add(heart_3_Card);
        Move player1MoveDetails = new Move(player1, diamond_2_Card);
        Move player2MoveDetails = new Move(player2, heart_3_Card);

        List<Move> moveList = new ArrayList<>();
        moveList.add(player1MoveDetails);
        moveList.add(player2MoveDetails);
        Move winnerForThisMove = GameResultEvaluator.getWinnerForThisMove(moveList);
        assertThat(winnerForThisMove).isEqualTo(player2MoveDetails);
    }

    @Test
    public void given_MoveDetails_Cards_Should_Be_Added_To_Winner_Bucket(){
        Card diamond_2_Card = new Card(SuiteValue.TWO, SuiteType.Diamond, Color.BLACK);
        Card diamond_3_Card = new Card(SuiteValue.THREE, SuiteType.Diamond, Color.BLACK);

        Player player1 = new Player("Shwetha");
        Player player2 = new Player("Nelson");

        Move player1MoveDetails = new Move(player1, diamond_2_Card);
        Move player2MoveDetails = new Move(player2, diamond_3_Card);

        List<Move> moveList = new ArrayList<>();

        moveList.add(player1MoveDetails);
        moveList.add(player2MoveDetails);

        GameResultEvaluator.updatePlayerCardStock(moveList);

        assertThat(player2.getCardStock()).hasSize(2);
        assertThat(player2.getCardStock()).contains(diamond_2_Card);
        assertThat(player2.getCardStock()).contains(diamond_3_Card);
        assertThat(player1.getCardStock()).isEmpty();
    }

}
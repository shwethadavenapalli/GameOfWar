package com.app;

import com.config.CardDistributor;
import com.config.PlayerConfig;
import com.exception.InvalidInputException;
import com.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Shwetha on 27-12-2018.
 */
public class GameRoundExecutorTest {

    private Player player1, player2;
    private PlayerConfig playerConfig;
    private CardDistributor cardDistributor;
    private Queue<Card> cardStockForPlayer1, cardStockForPlayer2;

    @Before
    public void setup() throws InvalidInputException {
        player1 = new Player("Shwetha");
        player2 = new Player("Aayush");
        playerConfig = new PlayerConfig(Arrays.asList(player1, player2));
        cardDistributor = new CardDistributor(playerConfig);
        cardStockForPlayer1 = new LinkedBlockingQueue<>();
        cardStockForPlayer2 = new LinkedBlockingQueue<>();
        player1.setCardStock(cardStockForPlayer1);
        player2.setCardStock(cardStockForPlayer2);
    }

    @Test
    public void given_Round_Is_WAR_Then_ShouldAcceptMoveTwice() throws InvalidInputException {
        Card diamond_2_Card = new Card(SuiteValue.TWO, SuiteType.Diamond, Color.BLACK);
        Card diamond_3_Card = new Card(SuiteValue.THREE, SuiteType.Diamond, Color.BLACK);

        Card spade_2_Card = new Card(SuiteValue.TWO, SuiteType.Spade, Color.BLACK);
        Card spade_4_Card = new Card(SuiteValue.FOUR, SuiteType.Spade, Color.BLACK);

        Card heart_2_Card = new Card(SuiteValue.TWO, SuiteType.Heart, Color.BLACK);
        Card heart_3_Card = new Card(SuiteValue.THREE, SuiteType.Heart, Color.BLACK);
        cardStockForPlayer1.add(diamond_2_Card);
        cardStockForPlayer1.add(diamond_3_Card);
        cardStockForPlayer1.add(heart_2_Card);

        cardStockForPlayer2.add(spade_2_Card);
        cardStockForPlayer2.add(spade_4_Card);
        cardStockForPlayer2.add(heart_3_Card);

        GameRoundExecutor gameRoundExecutor = new GameRoundExecutor(playerConfig, cardDistributor);
        GameStatus gameStatus = gameRoundExecutor.executeRound();

        assertThat(gameStatus).isEqualTo(GameStatus.Playable);
        assertThat(player2.getCardStock().size()).isEqualTo(6);
        assertThat(player1.getCardStock().size()).isEqualTo(0);
    }


    @Test
    public void given_Round_Is_WOn_Then_ShouldReturnWinner_Status() {
        Card diamond_2_Card = new Card(SuiteValue.TWO, SuiteType.Diamond, Color.BLACK);
        cardStockForPlayer1.add(diamond_2_Card);
        GameRoundExecutor gameRoundExecutor = new GameRoundExecutor(playerConfig, cardDistributor);
        GameStatus gameStatus = gameRoundExecutor.executeRound();
        assertThat(gameStatus).isEqualTo(GameStatus.Won);
        assertThat(player2.getCardStock().size()).isEqualTo(0);
        assertThat(player1.getCardStock().size()).isEqualTo(1);
    }

    @Test
    public void given_Round_Is_Draw_Should_Return_Status_As_Draw() {
        GameRoundExecutor gameRoundExecutor = new GameRoundExecutor(playerConfig, cardDistributor);
        GameStatus gameStatus = gameRoundExecutor.executeRound();
        assertThat(gameStatus).isEqualTo(GameStatus.Draw);
        assertThat(player2.getCardStock().size()).isEqualTo(0);
        assertThat(player1.getCardStock().size()).isEqualTo(0);
    }


}
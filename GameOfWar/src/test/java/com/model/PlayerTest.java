package com.model;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Shwetha on 28-12-2018.
 */
public class PlayerTest {


    @Test
    public void given_No_Cards_Then_Next_Move_Should_Return_Null(){
        Player player= new Player("Shwetha");
        assertThatExceptionOfType(NoSuchElementException.class)
        .isThrownBy(()->player.getNextMove());
    }


    @Test
    public void given_2_Cards_Then_Next_Move_Should_Return_First_Given_Card(){
        Player player= new Player("Shwetha");
        Card diamond_2_Card = new Card(SuiteValue.TWO, SuiteType.Diamond, Color.BLACK);
        Card diamond_3_Card = new Card(SuiteValue.THREE, SuiteType.Diamond, Color.BLACK);
        player.getCardStock().add(diamond_2_Card);
        player.getCardStock().add(diamond_3_Card);
        Card nextMove = player.getNextMove();
        assertThat(nextMove).isEqualTo(diamond_2_Card);
        nextMove = player.getNextMove();
        assertThat(nextMove).isEqualTo(diamond_3_Card);
    }

}
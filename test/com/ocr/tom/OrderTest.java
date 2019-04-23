package com.ocr.tom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Order order = new Order();

    @Test
    public void Given_Chicken_When_DisplayMenuSelected_Then_DisplayChickenSentence() {
        order.displaySelectedMenu(1);
        assertEquals("Vous avez choisi comme menu : poulet\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Beef_When_DisplayMenuSelected_Then_DisplayBeefSentence() {
        order.displaySelectedMenu(2);
        assertEquals("Vous avez choisi comme menu : boeuf\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_Vegetarian_When_DisplayMenuSelected_Then_DisplayVegetarianSentence() {
        order.displaySelectedMenu(3);
        assertEquals("Vous avez choisi comme menu : végétarien\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_TooBigValue_When_DisplayMenuSelected_Then_DisplayErrorSentence() {
        order.displaySelectedMenu(15);
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_NegativeValue_When_DisplayMenuSelected_Then_DisplayErrorSentence() {
        order.displaySelectedMenu(-6);
        assertEquals("Vous n'avez pas choisi de menu parmi les choix proposés\n", outContent.toString().replace("\r\n", "\n"));
    }
    @Test
    public void Given_ChikenInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.runMenu();
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals(output.endsWith("Vous avez choisi comme menu : poulet\n"), true);
        assertEquals(output.length() > "Vous avez choisi comme menu : poulet\n".length(), true);
    }
    @Test
    public void Given_VegetablesAndAllSides_When_DisplaySideSelected_Then_DisplayVegetablesSentence() {
        order.displaySelectedSide(1, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : légumes frais\n", output);
    }
    @Test
    public void Given_FriesAndAllSides_When_DisplaySideSelected_Then_DisplayFriesSentence() {
        order.displaySelectedSide(2, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : frites\n", output);
    }
    @Test
    public void Given_RiceAndAllSides_When_DisplaySideSelected_Then_DisplayRiceSentence() {
        order.displaySelectedSide(3, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : riz\n", output);
    }
    @Test
    public void Given_BadValueAndAllSides_When_DisplaySideSelected_Then_DisplayErrorSentence() {
        order.displaySelectedSide(5, true);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés\n", output);
    }
    @Test
    public void Given_RiceAndNotAllSides_When_DisplaySideSelected_Then_DisplayRiceSentence() {
        order.displaySelectedSide(1, false);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : riz\n", output);
    }
    @Test
    public void Given_NoRiceAndNotAllSides_When_DisplaySideSelected_Then_DisplayNoRiceSentence() {
        order.displaySelectedSide(2, false);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous avez choisi comme accompagnement : pas de riz\n", output);
    }
    @Test
    public void Given_BadValueAndNotAllSides_When_DisplaySideSelected_Then_DisplayErrorSentence() {
        order.displaySelectedSide(5, false);
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals("Vous n'avez pas choisi d'accompagnement parmi les choix proposés\n", output);
    }
}

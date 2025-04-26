package com.lld;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TickTackToeGame tttGame = new TickTackToeGame();
        tttGame.initializeGame();
        System.out.println("game winner is: "+ tttGame.startGame());
    }
}
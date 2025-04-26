package com.lld.TicTacToe;
import com.lld.TicTacToe.Modal.*;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TickTackToeGame {

    Deque<Player> players;
    Board game;

    public void initializeGame(){

        //creating players
        players = new LinkedList<>();
        PlayingPieceX cross = new PlayingPieceX();
        Player player1 = new Player("Player 1", cross);

        PlayingPieceO zero = new PlayingPieceO();
        Player player2 = new Player("Player 2", zero);

        players.add(player1);
        players.add(player2);

        //initialize board
        game = new Board(3);
    }

    public String startGame(){
        boolean noWinner = true; //ongoing game
        while(noWinner){ //until someone wins or board gets full.
            Player playerTurn = players.removeFirst();

            game.printBoard();

            List<Pair<Integer, Integer>> freeSpaces = game.getFreeCells();
            if(freeSpaces.isEmpty()){
                //If no free spaces are left = board full = tie → end game.
                noWinner = false;
                continue;
            }

            //read the user input
            System.out.print("Player:" + playerTurn.name + " Enter row,col: ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");

            //Read a row and column from the player (like "1,2").
            int inputRow = Integer.parseInt(values[0]);
            int inputColumn = Integer.parseInt(values[1]);

            //place the piece
            boolean pieceAdded = game.addPiece(inputRow, inputColumn, playerTurn.playingPiece);
            if(!pieceAdded){
                //player cannot insert the piece into this cell, player has to choose another cell
                System.out.println("Incorrect position chosen, try again");
                players.addFirst(playerTurn); //re-adding the same player at the front.)
                continue;
            }
            //For the next player turn since pieceAdded is true.
            //Move to the back of the deque so that the other player gets their turn next.
            players.addLast(playerTurn);

            boolean winner = winnerCheck(inputRow, inputColumn, playerTurn.playingPiece.pieceType);
            if(winner) {
                return playerTurn.getName();
            }
        }
        return "game is tie";
    }

    private boolean winnerCheck(int row, int col, PieceType pieceType){

        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //check in row
        for(int i=0;i<game.size;i++) {
            if(game.board[row][i] == null || game.board[row][i].pieceType != pieceType) {
                rowMatch = false;
            }
        }

        //check in col
        for(int i=0;i<game.size;i++) {
            if (game.board[i][col] == null || game.board[i][col].pieceType != pieceType) {
                colMatch = false;
            }
        }

        //check diagonal
        for(int i=0, j=0 ;i<game.size;i++,j++) {
            if(game.board[i][j] == null || game.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
            }
        }

        //check anti-diagonal
        for(int i=0, j=game.size-1 ; i<game.size; i++, j--) {
            if (game.board[i][j] == null || game.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }
}

package com.lld.TicTacToe.Modal;

import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    //Adds a player’s piece (X or O) to the board at a specific cell.
    //If the cell is already occupied, returns false.
    //Otherwise, places the piece and returns true.
    public boolean addPiece(int row, int col, PlayingPiece playingPiece){
        if(board[row][col] != null){
            return false;
        }
        board[row][col] = playingPiece;
        return true;
    }

    //Finds all empty cells on the board.
    public List<Pair<Integer,Integer>> getFreeCells(){
        List<Pair<Integer,Integer>> freeCells = new ArrayList<>();
        for(int i =0;i<size;i++){
            for(int j =0;j<size;j++){
                if(board[i][j] == null){
                    Pair<Integer, Integer> element = new Pair<>(i,j);
                    freeCells.add(element);
                }
            }
        }
        return freeCells;
    }

    //print x|o board
    public void printBoard(){
        for(int i =0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.print(board[i][j].pieceType.name() + " ");
                }else{
                    System.out.print(" ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }


}


// NOTE
//size: Holds the board size (example: 3 for 3x3)
// PlayingPiece[][] board:	2D grid where each cell holds either null (empty) or a PlayingPiece (like X or O)




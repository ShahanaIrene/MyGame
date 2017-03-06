/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

/**
 *
 * @author Shahana Alam
 */
public class Game {
    private Board myBoard;
    private Player blackPlayer;
    private Player whitePlayer;
    //private Location currentLocation;//Do I need current Location?
    private boolean isDraw = false;
    private boolean isFinished = false;
    private Lines[] myLines;
    private int noOfMoves;
    
    
    public Game(int boardSize){
        noOfMoves = 0;
        myBoard = new Board (boardSize);
        myLines = new Lines[]{new HorizontalLines(), new VerticalLines(), new StraightDiagonalLines(),
                    new DiagonalSingleSkew(), new DiagonalDoubleSkew()};
    }
    
    public void makeMove(String location, Player current){
        //currentLocation = new Location (location);
        myBoard.addBead(new Location (location), current.getColor());
        noOfMoves++;
        if (noOfMoves % 2 == 0)
            isBlackWin();
        else
            isWhiteWin();
    }
    
    public void addPlayers(Player white, Player black){
        whitePlayer = white;
        blackPlayer = black;
    }
    
    public boolean isWhiteWin(){
        for (Lines thisLine:myLines){
            if (thisLine.isWhiteLine(myBoard)){
                isFinished = true;
                return true;
            }
        }
        return false;
    }
    
    public boolean isBlackWin(){
        for (Lines thisLine:myLines){
            if (thisLine.isBlackLine(myBoard)){
                isFinished = true;
                return true;
            }
        }
        return false;
    }
    
    public boolean isFinished(){
        return isFinished;
    }
    
    public boolean isDraw(){
        if (Lines.getDeadLines() == 76 || noOfMoves == 64)
            isDraw = true;
        return isDraw;
    }
    
    public Player getOppositePlayer(Player current){
        if (current.equals(blackPlayer))
                return whitePlayer;
        return blackPlayer;
    }
    
    public void displayBoard(){
        myBoard.displayBoard();
    }
}

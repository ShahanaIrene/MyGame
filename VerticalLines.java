/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score4.game;

/**
 *
 * @author Shahana Alam
 * Testing done
 */
public class VerticalLines extends Lines {

    
    @Override
    public boolean isWhiteLine(Board board){
        String line = "";
        int boardSize = board.getMyLength();
        for (int i=0; i< boardSize; i++)
            for(int j= 0; j<boardSize; j++){
                for(int k= 0 ; k< boardSize; k++){
                    line += board.getPeg(i, j).getBeadColorLetter(k);
                }
                if (line.equals("WWWW"))
                    return true; 
                
                if (isDeadLine(line))
                    addDeadLines();
                line = "";
                } 
        return false;
    }
    
    @Override
    public boolean isBlackLine(Board board){
        String line = "";
        int boardSize = board.getMyLength();
        for (int i=0; i< boardSize; i++)
            for(int j= 0; j<boardSize; j++){
                for(int k= 0 ; k< boardSize; k++){
                    line += board.getPeg(i, j).getBeadColorLetter(k);
                }
                if (line.equals("BBBB"))
                    return true; 
                
                if (isDeadLine(line))
                    addDeadLines();
                
                line = "";
                } 
        return false;
    }
}

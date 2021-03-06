/**
 * Created by andrewnyaisonga on 2/8/17.
 */

import java.io.IOException;
import java.util.ArrayList;



public class Mastermind {


    public ArrayList<String> allCombination = new ArrayList<String>(); //This is the ArrayList that will store all our combinations
    String guess;                  //String to keep truck of the guesses we are given

    Mastermind(String [] tokenColors, int number){

        guess = null;
        allCombination.addAll(getAllCombination(tokenColors,number));       //Initialize our combination string
    }


    public ArrayList<String> getAllCombination(String[] tokeanColors, int number){      //This is the method that gives all combinations
        ArrayList<String> tempAllCombination = new ArrayList<String>();
        if(number == 1){
            for(int i=0; i<tokeanColors.length;i++){
                tempAllCombination.add(tokeanColors[i]);
            }
            return tempAllCombination;
        }
        else{
            for(int i=0; i<tokeanColors.length;i++){
                ArrayList<String> tempRecursive = getAllCombination(tokeanColors,number-1);
                for(int j=0; j<tempRecursive.size();j++){
                    tempAllCombination.add(tokeanColors[i]+tempRecursive.get(j));
                }
            }
        }
        return tempAllCombination;
    }


public int[] compareMethod(String oldguess, String newguess){     //This is the method that compare the guesses we have been given
    int black =0, white = 0;

    String[] oldguessed = oldguess.split("-");
    String[] newguessed = newguess.split("-");

    for(int i=0; i<oldguessed.length;i++){      //This one chest how many element are the same to the last guess
        if(oldguessed[i].equals(newguessed[i])){
            black++;
            oldguessed[i] = " ";
            newguessed[i] = " ";
        }
    }

    for(int j=0;j<oldguessed.length;j++){
        for(int l=0;l<newguessed.length;l++){
            if(oldguessed[j].equals(newguessed[l]) && !oldguessed[l].equals(" ") && !oldguessed[j].equals(" ")){
                newguessed[l] = " ";
                white++;
            }
        }
    }

        int [] compare = {black,white};
        return compare;
    }

    public void response(int colorsRightPositionWrong, int positionsAndColorRight){
        for(int i=0; i<allCombination.size();i++){
            int [] feedback = compareMethod(guess,allCombination.get(i));
            if(feedback[0] != positionsAndColorRight || feedback[1] != colorsRightPositionWrong){
                allCombination.remove(i);
                i--;
            }
        }
    }

    public String nextMove(){
        guess = allCombination.get(0);
        return allCombination.remove(0);
    }

    public void newGame(){
        allCombination.clear();
    }



}

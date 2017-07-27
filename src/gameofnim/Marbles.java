/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofnim;

import javax.swing.JOptionPane;

/**
 *
 * @author pant9060
 */
public class Marbles {
    private int marblesAmount;
    private int starterNumber;
    private int subNum;
    private String level;
    private String name;
    
    //constructor method that gets the difficulty and name from the other class. It initializes a number of marbles to begin with and decides who 
    //is starting.
    public Marbles(String difficulty, String playerName){
        name = playerName;
        level = difficulty;
        marblesAmount = (int)((Math.random()*200)+2);
        starterNumber = (int)(Math.random()*2);
        System.out.println("Starting Number: "+marblesAmount);
        if(starterNumber==0){
            System.out.println("Computer is starting");
        }else{
            System.out.println(name+" is starting");
        }
    }
    
    //method that returns the highest possible number in the sequence 2^n-1 that is less than the amount of marbles.
    public int findMagicNumber(){
        int previousNumber;
        int currentNumber = 1;
        int n=2;
        //goes through the sequence 2^n-1 and finds the largest possible number that is less than the amount of marbles
        do{
            previousNumber = currentNumber; 
            currentNumber =(int)(Math.pow(2,n)-1);
            n++;
        }
        while(currentNumber<marblesAmount);
        return previousNumber;
    }
    
    //this method checks if the level is hard or easy. If the level is hard, it subtracts the amount of marbles to get the magic number
    //and then continues subtracting random numbers until the computer wins. If the human went first and was able to the get the magic number
    //before the computer, the computer just subtracts random numbers.the easy level just subtracts random numbers until someone wins.
    public void subtractNumbers(){
        //this while loop subtracts the user input and computer numbers while the marblesAmount is greater than 1.
        while(marblesAmount>1){
            //checks if the starter number is 0 or 1 to see whose turn it is. if it is 
            //the computer's turn, it checks if the level is hard or easy and subtracts numbers based on that.
            if(starterNumber == 0){
                subNum = (int)(Math.random()*(marblesAmount/2)+1); //computer
                checkLevel();
                System.out.print("Computer: "+marblesAmount+" - " +subNum);
                marblesAmount -= subNum;
                starterNumber = 1;
                System.out.println(" = " +marblesAmount);
                
            }else{
                int humanSubNum = Integer.parseInt(JOptionPane.showInputDialog("How "+ "much would you like to subtract? It must be a number from 1 to " + (marblesAmount/2))); //human
                while(!(humanSubNum <= (marblesAmount/2))){
                    humanSubNum = Integer.parseInt(JOptionPane.showInputDialog("Please eneter in a number from 1 to " + (marblesAmount/2)));
                }
                System.out.print("Human: "+marblesAmount+" - " +humanSubNum);
                marblesAmount -=humanSubNum;
                starterNumber = 0;
                System.out.println(" = " +marblesAmount);
            }
        }  
        //if the human is going to lose, the starterNumber will be 1. the human will be forced to subtract 1 and lose the game.
        if(starterNumber == 1){
            int humanSubNum = Integer.parseInt(JOptionPane.showInputDialog("The only number you can subtract is 1")); //human
            while(humanSubNum != 1){
                humanSubNum = Integer.parseInt(JOptionPane.showInputDialog("Please eneter in 1"));
            }
            System.out.print("Human: "+marblesAmount+" - " +humanSubNum);
            marblesAmount -=humanSubNum;
            System.out.println(" = " +marblesAmount);
            System.out.println("Computer Wins!");
        }else{ //the computer is going to lose and has to subtract 1.
            subNum = 1; //computer
            System.out.print("Computer: "+marblesAmount+" - " +subNum);
            marblesAmount -= subNum;
            System.out.println(" = " +marblesAmount);
            System.out.println(name +" Win!");
        }
    }
    //checks if the subtraction number is greater than the marblesAmount divided by 2. returns true if it is otherwise it returns false.
    //this is needed to see if the human already got the pattern or not.
    public boolean checkNumber(int subtractionNumber){
        return subtractionNumber >= (marblesAmount/2);
    }
    
    //checks if the level is hard. if the level is hard, the computer will subtract the magic number from the number of marbles.
    public void checkLevel(){
        if(level.equals("hard")){
            subNum = marblesAmount - findMagicNumber(); //computer
            //checks if the number is not valid and if it's not, it changes the subNum to a random number.
            if(checkNumber(subNum)){
                subNum = (int)(Math.random()*(marblesAmount/2)+1);
            }
            level= "easy"; //changes the level to easy so the rest of code executes like it is easy.
        }
    }
}

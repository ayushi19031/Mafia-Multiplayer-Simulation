package com.company;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Input {
    Scanner scan = new Scanner(System.in);
    public Input(){}
    public int Game_number_of_players(){

        try{
            System.out.println("Welcome to mafia ");
            System.out.println("Please enter the number of players. ");
            int a = scan.nextInt();
            if (a < 6){
                System.out.println("Please enter an integer, greater then equal to 6. ");
                return Game_number_of_players();
            }
            return a;
        }
        catch (InputMismatchException e){
            System.out.println("Please enter an integer, greater than equal to 6. ");
            scan.next();
             return Game_number_of_players();
        }
    }
    public User createUser(){
        System.out.println("Choose a character. ");
        System.out.println("1) Mafia ");
        System.out.println("2) Healer ");
        System.out.println("3) Commoner ");
        System.out.println("4) Detective ");
        System.out.println("5) Assign Randomly ");
        try{

        int a = scan.nextInt();

        User user;
        if (a == 1){
            Mafia ada = new Mafia();
            user = new User(ada);
        }
        else if (a == 2){
            Healer ada = new Healer();
            user = new User(ada);
        }
        else if (a == 3){
            Commoner ada = new Commoner();
            user = new User(ada);
        }
        else if (a == 4){
            Detective det = new Detective();
            user = new User(det);
        }
        else {
            Random rand = new Random(3);
            int s = rand.nextInt(4);
            s += 1;
            if (s == 1){
                Mafia adam = new Mafia();
                user = new User(adam);
            }
            else if (s == 2){
                Healer adam = new Healer();
                user = new User(adam);
            }
            else if (s == 3){
                Commoner adam = new Commoner();
                user = new User(adam);
            }
            else if ( s == 4){
                Detective ada = new Detective();
                user = new User(ada);
            }
            else {
                user = new User(new Mafia());
            }
        }
      //  user.player.ref = 1;
        return user;}
        catch (InputMismatchException e){
            System.out.println("Please enter integer :-|");
            scan.next();
            return createUser();
        }

    }
}

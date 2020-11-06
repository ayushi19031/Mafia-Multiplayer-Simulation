package com.company;

import java.lang.management.PlatformLoggingMXBean;
import java.util.*;

public class Mafia extends Player{
    protected boolean inGame;
    public Mafia(){
        this.hp_points = 2500;
        this.inGame = true;
    }
    public void takeInput(int i, HashMap<Integer, Player> hash) {
        if (i == 0) {
            try
            {      // System.out.println("You are player no 1. ");
            System.out.println("Your fellow players are: ");
            Iterator hmIterator = hash.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                Player pj = (Player) mapElement.getValue();
                if (pj instanceof Mafia) {
                    System.out.println("Player No. " + mapElement.getKey());
                }
            }
            Scanner scan = new Scanner(System.in);
            boolean f_in = true;
            int a = 0;
            while (f_in) {
                System.out.println("Who do you wanna kill. ");
                a = scan.nextInt();
                if (hash.containsKey(a) && !(hash.get(a) instanceof Mafia)) {
                    f_in = false;


                }
                else if (!hash.containsKey(a)){
                    throw new PlayerDoesNotExist();
                }
                else {
                    throw  new IsAMafiaPlayer();
                   // System.out.println("Please choose a member who is not mafia. ");
                }
            }
            Player Player_to_kill = hash.get(a);
            System.out.println("Player killed: " + Player_to_kill.ref);
            Operate_the_player(Player_to_kill, hash);

        } catch (IsAMafiaPlayer t) {
                System.out.println("Please choose a member who is not mafia. ");
                takeInput(0, hash);
            }
            catch (InputMismatchException e){
                System.out.println("Please enter an integer as input. ");
                takeInput(0, hash);
            }
            catch (PlayerDoesNotExist p){
                System.out.println("Player does not exist. ");
                takeInput(0, hash);
            }
        }
        else {
            Player Player_to_kill = choosePlayer(hash);
            System.out.println("The Mafias have chosen their kill.: " + Player_to_kill.ref);
            Operate_the_player(Player_to_kill, hash);
        }
    };
    public ArrayList<Integer> choosablePlayers(HashMap<Integer, Player> hash){
        ArrayList<Integer> choosable_People = new ArrayList<>();
        Iterator hmIterator = hash.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            Player pj = (Player) mapElement.getValue();
            int ind = (int) mapElement.getKey();
            if (!(pj instanceof Mafia)){
                choosable_People.add(ind);
            }
        }
        return choosable_People;
    }
    public  Player choosePlayer(HashMap<Integer, Player> hash){
        ArrayList<Integer> choosablePeople = choosablePlayers(hash);
        Random t = new Random();
        try{
        int j = t.nextInt(choosablePeople.size());
        int ind = choosablePeople.get(j);
        hash.get(ind);
        Player play = hash.get(ind);

        return play;}
        catch (Exception e){
            System.out.println("ONly Mafias are left. ");
            return null;
        }
    };
    public void Operate_the_player(Player player, HashMap<Integer, Player> hash){
        Iterator hmIterator = hash.entrySet().iterator();
        ArrayList<Mafia> list_of_mafia = new ArrayList<>();
        int total_hp = 0;
        int Y = 0;
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            Player pj = (Player) mapElement.getValue();
       //     System.out.println(pj.hp_points);

            if (pj instanceof Mafia) {
           //     System.out.println("Hullo ");
                ///    System.out.println("Player No. " + mapElement.getKey();
                list_of_mafia.add((Mafia) pj);
                //   total_hp += pj.hp_points;
                if (pj.hp_points > 0) {
                    total_hp += pj.hp_points;
                    Y += 1;
                    //    System.out.println(pj.hp_points);
                }
        //        System.out.println("Heyy: " + pj.hp_points);
            }
        }
        Collections.sort(list_of_mafia, new SortMafiaWithHP());
       // System.out.println("TOTAL HP POINTS = " + total_hp);
            int X = player.hp_points;
            if (total_hp >= player.hp_points){
                player.hp_points = 0;
            }
            else {
                player.hp_points -= total_hp;
            }
         //   System.out.println("PLAYER MAFIA KILLED POINTS: " + player.ref + " "  + player.hp_points);
            if (Y == 0){return ;}
            int damage  = X/Y;
            int r = 0;
            for (Mafia muffin : list_of_mafia){
                r += 1;
                if (muffin.hp_points >= damage){
                    muffin.hp_points -= damage;
                    X -= damage;
                }
                else {
                    X -= muffin.hp_points;
                    muffin.hp_points = 0;
                    if (list_of_mafia.size() - r > 0){damage = X/(list_of_mafia.size()  - r) ;}
                }
            }
            int kp = player.ref;

        }

    public void LetsVote(){

    }
    public boolean equals(Object ad){
        if (ad instanceof Mafia){
            return ad == this;
        }
        if (ad == this){return true;}
        return false;
    }
}

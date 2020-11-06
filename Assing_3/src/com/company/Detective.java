package com.company;

import java.util.*;

public class Detective extends Player {
    public Detective() {
        hp_points = 800;
    }

    public void takeInput(int i, HashMap<Integer, Player> hash) {
        if (i == 0) {

            try {   System.out.println("Your fellow players are: ");
            Iterator hmIterator = hash.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                Player pj = (Player) mapElement.getValue();
                if (pj instanceof Detective) {
                    System.out.println("Player No. " + mapElement.getKey());
                }
            }
            Scanner scan = new Scanner(System.in);
            boolean f_in = true;
            int a = 0;

            while (f_in) {
                System.out.println("Who do you want to test. ");
                a = scan.nextInt();
                if (hash.containsKey(a) && !(hash.get(a) instanceof Detective)) {
                    f_in = false;


                } else if (!hash.containsKey(a)) {
                    throw new PlayerDoesNotExist();
                //    System.out.println("You cannot test a dead person/non-existent person buddy :-P");
                } else {
                    System.out.println("You cannot test a detective ");

                }
            }
            Player Player_to_test = hash.get(a);
            OperateAPlayer(Player_to_test, hash);
        }
            catch (PlayerDoesNotExist p){
                System.out.println("You cannot test a dead person/non-existent person buddy :-P");
                takeInput(0, hash);
            }
            catch (InputMismatchException e){
                System.out.println("Please enter an integer as input. ");
                takeInput(0, hash);
            }
        }

         else {
            Player Player_to_test = choosePlayer(hash);
            OperateAPlayer(Player_to_test, hash);
            System.out.println("The Detectives have done the work.");
        }
    }

    public ArrayList<Integer> choosablePlayers(HashMap<Integer, Player> hash){
        ArrayList<Integer> choosable_People = new ArrayList<>();
        Iterator hmIterator = hash.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            Player pj = (Player) mapElement.getValue();
            int ind = (int) mapElement.getKey();
            if (!(pj instanceof Detective)){
                choosable_People.add(ind);
            }
        }
        return choosable_People;
    }
    public  Player choosePlayer(HashMap<Integer, Player> hash){
        ArrayList<Integer> choosablePeople = choosablePlayers(hash);
        Random t = new Random();
        int j = t.nextInt(choosablePeople.size());
        int ind = choosablePeople.get(j);
        hash.get(ind);
        Player play = hash.get(ind);
        return play;
    };


    public void OperateAPlayer(Player player, HashMap<Integer, Player> hash){
        int agar = -1;
        if (player instanceof  Mafia){
                Iterator hmIterator = hash.entrySet().iterator();
                while (hmIterator.hasNext()) {
                    Map.Entry mapElement = (Map.Entry)hmIterator.next();
                    int s = (Integer) mapElement.getKey();
                    Player pp = (Player) mapElement.getValue();
                    if (pp.equals(player)){
                        agar = s;
                        //hash.remove(s);
                    }
                }
                System.out.println("Player " + player.ref + " is mafia. ");
            }
        else {
            System.out.println("Player " + player.ref + " is not mafia. ");
        }
        if (agar != -1){
            hash.remove(agar);
        }
    }
    public boolean equals(Object ad){
        if (ad instanceof Detective){
            return ad == this;
        }
        return false;
    }
}

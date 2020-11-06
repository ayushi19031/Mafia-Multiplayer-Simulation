package com.company;

import java.util.*;

public class Healer  extends Player{
    public Healer(){
        this.hp_points = 800;
    }
    public void takeInput(int i, HashMap<Integer, Player> hash){
        if (i == 0) {
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println("Heal a person. ");

                boolean f_in = true;
                int a;
                a = scan.nextInt();
                if (hash.containsKey(a)){
                Player Player_to_heal = hash.get(a);
                Operate_on_player(Player_to_heal);}
                else {
                    throw new PlayerDoesNotExist();
                }
            }
            catch (InputMismatchException e){
                System.out.println("Please enter an integer. ");
                //scan.next();
                 takeInput(0, hash);
            }
            catch (PlayerDoesNotExist e){
                System.out.println("Player Does Not Exist");
                //scan.next();
                takeInput(0 ,hash);
            }
        }
        else {
            Player Player_to_heal = choosePlayer(hash);
            System.out.println("The Healer have done the work.");
            Operate_on_player(Player_to_heal);
        }
    };
    public ArrayList<Integer> choosablePlayers(HashMap<Integer, Player> hash){
        ArrayList<Integer> choosable_People = new ArrayList<>();
        Iterator hmIterator = hash.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            Player pj = (Player) mapElement.getValue();
            int ind = (int) mapElement.getKey();
            // if (!(pj instanceof Detective)){
            choosable_People.add(ind);
            // }
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
    public void Operate_on_player(Player p){
    //    System.out.println("Healer has healed Player no: " + p.ref);
        p.hp_points += 500;

        return ;
    }
    public boolean equals(Object ad){
        if (ad instanceof Healer){
            return ad == this;
        }
        return false;
    }
}

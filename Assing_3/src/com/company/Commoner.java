package com.company;

import java.util.*;

public class Commoner extends Player{

    public Commoner(){
        this.hp_points = 1000;
    }
    public void takeInput(int i, HashMap<Integer, Player> hash){
        if (i == 0) {
            System.out.println("Vote out a person. ");
            Scanner scan = new Scanner(System.in);
            boolean f_in = true;
            int a ;
            a = scan.nextInt();

            Player Player_to_voteout = hash.get(a);


        }
        else {
            Player Player_to_voteout = choosePlayer(hash);
            System.out.println("The Detectives have done the work.");
        }
    }

    public void OperateAPlayer() {

    }

    ;
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
    public boolean equals(Object ad){
        if (ad instanceof Commoner){
            return ad == this;
        }
        return false;
    }
  //  public void Operate_a_Player(){}
}

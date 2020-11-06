package com.company;

import java.util.*;

public abstract class Player {
    public abstract void takeInput(int i, HashMap<Integer, Player> hash);
    protected int hp_points;
    protected boolean inGame;
    protected int ref = -1;
   // public abstract void choosePlayer();
    public Player vote(HashMap<Integer, Player> hash){
        ArrayList<Player> list = new ArrayList<>();
        Iterator hIt = hash.entrySet().iterator();
        Random random = new Random();
        int t = random.nextInt(hash.size());
        while (hIt.hasNext()){
            Map.Entry jp = (Map.Entry) hIt.next();
            Player pj = (Player) jp.getValue();
            list.add(pj);
        }
        return list.get(t);
    }
    public  String toString(){
        if (this instanceof  Mafia){
            System.out.println(ref + ": Mafia: " + hp_points);
        }
        if (this instanceof  Detective){
            System.out.println(ref  + ": Detective: " + hp_points);
        }
        if (this instanceof  Healer){
            System.out.println(ref + ":  Healer"  + hp_points);
        }
        if (this instanceof  Commoner){
            System.out.println(ref + ":  Commoner"  + hp_points);
        }
        return "";
    }
   // public abstract void OperateAPlayer();
}

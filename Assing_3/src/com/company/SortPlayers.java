package com.company;

import java.util.Comparator;

public class SortPlayers implements Comparator<Player> {
    public int compare(Player p1, Player p2){
        int[] a = new int[2];
        int mafia = 5;
        int detect = 4;
        int heal = 3;
        int commoner = 2;
        if (p1 instanceof Mafia){
            a[0] = mafia;
        }
        if (p2 instanceof Mafia){
            a[1] = mafia;
        }
        if (p1 instanceof Detective){
            a[0] = detect;
        }
        if (p2 instanceof Detective){
            a[1] = detect;
        }
        if (p1 instanceof Healer){
            a[0] = heal;
        }
        if (p2 instanceof Healer){
            a[1] = heal;
        }
        if (p1 instanceof Commoner){
            a[0] = commoner;
        }
        if (p2 instanceof Commoner){
            a[1] = commoner;
        }
        return a[0] - a[1];
    }
}

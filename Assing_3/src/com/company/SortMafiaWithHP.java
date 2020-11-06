package com.company;

import java.util.Comparator;

public class SortMafiaWithHP implements Comparator<Mafia> {
    public int compare(Mafia m1, Mafia m2){
        return m1.hp_points - m2.hp_points;
    }
}

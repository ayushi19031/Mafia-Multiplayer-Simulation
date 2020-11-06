package com.company;

import java.util.*;

public class Game {
    private int number_of_players;
    private Input input = new Input();
    private User user;
    protected HashMap<Integer, Player> player_details = new HashMap<>();
    private InfoClass<Mafia> i_m = new InfoClass<>();
    private InfoClass<Detective> i_d = new InfoClass<>();
    private InfoClass<Healer> i_h = new InfoClass<>();
    private InfoClass<Commoner> i_c = new InfoClass<>();
    protected ArrayList<Mafia> mafia_list = new ArrayList<>();
    protected ArrayList<Detective> detective_list = new ArrayList<>();
    protected ArrayList<Commoner> commoners_list = new ArrayList<>();
    protected ArrayList<Healer> healers_list = new ArrayList<>();
    private  Rounds rounds;
    protected ArrayList<Integer> list_to_be_rand = new ArrayList<>();
    protected  ArrayList<Player> list_to_give_info = new ArrayList<>();
    public Game(){
        this.number_of_players = input.Game_number_of_players();
        this.user = input.createUser();
        Random rand  = new Random();
       // int user_no = 1;
        player_details.put(1, user.player);
     //   System.out.println(player_details.get(1).toString());

        createGameEnv();
    }
    public void createGameEnv(){
        int no_of_mafia = number_of_players/5;
        int no_of_detectives = number_of_players/5;
        int healer_quantity = Math.max(1, number_of_players/10);
        for (int j = 0; j < number_of_players; j++){
            list_to_be_rand.add(j + 1);
        }
        Collections.shuffle(list_to_be_rand);
        //list_to_be_rand.add(-1);
        for (int i = 1; i <= number_of_players; i++){
            Random rand = new Random();
         //   int healer_quantity = Math.max(1, number_of_players/10);
            if (i == 1){

                player_details.put(list_to_be_rand.get(i - 1), user.player);
                if (user.player instanceof Mafia){
                    mafia_list.add((Mafia) player_details.get(list_to_be_rand.get(i - 1)));
                    i_m.list.add((Mafia) player_details.get(list_to_be_rand.get(i - 1)));
                    no_of_mafia -= 1;
                }
                if (user.player instanceof Detective){
                    no_of_detectives -= 1;
                    Iterator hit = player_details.entrySet().iterator();
                    while (hit.hasNext()){
                        Map.Entry d = (Map.Entry) hit.next();
                        int j = (int) d.getKey();

                        //System.out.println(j  + " " + i);
                    }
//                    System.out.println(user.player.hp_points);
                    player_details.put(list_to_be_rand.get(0), user.player);
                    Detective jk = (Detective)  player_details.get(list_to_be_rand.get(i - 1));
                    detective_list.add(jk);
                    i_d.list.add(jk);
                }
                if (user.player instanceof Healer){
                    Iterator hit = player_details.entrySet().iterator();
                    while (hit.hasNext()){
                        Map.Entry d = (Map.Entry) hit.next();
                        int j = (int) d.getKey();
                        System.out.println(j);
                    }
                    healers_list.add((Healer) player_details.get(list_to_be_rand.get(i - 1)));
                    i_h.list.add((Healer) player_details.get(list_to_be_rand.get(i - 1)));
                }
                if (user.player instanceof Commoner){

                    commoners_list.add((Commoner) player_details.get(list_to_be_rand.get(i - 1)));
                    i_c.list.add((Commoner) player_details.get(list_to_be_rand.get(i - 1)));
                }
                user.player.ref = list_to_be_rand.get(0);
            }
            else if (i <= 1 + no_of_mafia){
                player_details.put(list_to_be_rand.get(i - 1) , new Mafia());
                mafia_list.add( (Mafia) player_details.get(list_to_be_rand.get(i - 1)));
                i_m.list.add( (Mafia) player_details.get(list_to_be_rand.get(i - 1)));
                player_details.get(list_to_be_rand.get(i - 1)).ref = list_to_be_rand.get(i - 1);
            }
            else if (i > no_of_mafia && i <= no_of_mafia + no_of_detectives + 1){
                player_details.put(list_to_be_rand.get(i - 1), new Detective());
                detective_list.add((Detective) player_details.get(list_to_be_rand.get(i - 1)));
                i_d.list.add((Detective) player_details.get(list_to_be_rand.get(i - 1)));
                player_details.get(list_to_be_rand.get(i - 1)).ref = list_to_be_rand.get(i - 1);
            }
            else if (i > (no_of_detectives + no_of_mafia + 1) && i <= (no_of_detectives +no_of_mafia +  healer_quantity + 1)){
                player_details.put(list_to_be_rand.get(i - 1), new Healer());
                healers_list.add((Healer) player_details.get(list_to_be_rand.get(i - 1)));
                i_h.list.add((Healer) player_details.get(list_to_be_rand.get(i - 1)));
                player_details.get(list_to_be_rand.get(i - 1)).ref = list_to_be_rand.get(i - 1);
            }
            else {
                player_details.put(list_to_be_rand.get(i - 1), new Commoner());
                commoners_list.add((Commoner) player_details.get(list_to_be_rand.get(i - 1)));
                player_details.get(list_to_be_rand.get(i - 1)).ref = list_to_be_rand.get(i - 1);
                i_c.list.add((Commoner) player_details.get(list_to_be_rand.get(i - 1)));
            }
           // rounds = new Rounds(this);
        }
        rounds = new Rounds(this);
        list_to_give_info.addAll(mafia_list);
        list_to_give_info.addAll(healers_list);
        list_to_give_info.addAll(detective_list);
        list_to_give_info.addAll(commoners_list);
        Collections.sort(list_to_give_info, new SortPlayers());
        System.out.println("Mafia were: ");
        for (Mafia p: mafia_list){
            System.out.print(" Playerno: " + p.ref);
        }
        System.out.println("");
        System.out.println("Detective were: ");
        for (Detective p: detective_list){
            System.out.print(" Playerno: " + p.ref);
        }
        System.out.println("");
        System.out.println("Healer were: ");
        for (Healer p: healers_list){
            System.out.print(" Playerno: " + p.ref);
        }
        System.out.println("");
        System.out.println("Commoners were: ");
        for (Commoner p: commoners_list){
            System.out.print(" Playerno: " + p.ref);
        }
        System.out.println("");
    }
    public HashMap<Integer, Player> getPlayerDetails(){
    //    System.out.println(player_details.get(list_to_be_rand.get(0)));
        return player_details;
    }
    public User getUser(){
        return this.user;
    }
}

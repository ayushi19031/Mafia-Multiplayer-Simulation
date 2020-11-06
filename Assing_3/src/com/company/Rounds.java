package com.company;

import java.util.*;
import java.util.Map;
import java.util.Map.Entry;
public class Rounds {
    private Mafia mafia;
    private Detective detective;
    private Healer healer;
    private Commoner commoner;
    private Game game;
    private HashSet<Player> set_of_people_elimnated_this_round;

   // Iterator hIter = game.getPlayerDetails().entrySet().iterator();
    public Rounds(Game game){
        Iterator hIter = game.getPlayerDetails().entrySet().iterator();
        this.game = game;
        set_of_people_elimnated_this_round = new HashSet<>();
        while (hIter.hasNext()) {
            Entry mapElement = (Entry) hIter.next();
            Player p = (Player) mapElement.getValue();
            if (p instanceof Mafia && mafia == null){
                mafia = (Mafia) p;
            }
            if (p instanceof Detective && detective == null){
                detective = (Detective) p;
            }
            if (p instanceof Healer && healer == null){
                healer = (Healer) p;
            }
            if (p instanceof Commoner && commoner == null){
                commoner = (Commoner) p;
            }
        }
        letsPlay();
    }
    public void letsPlay(){
        Iterator huppy = game.getPlayerDetails().entrySet().iterator();
        System.out.println("You are player no " + game.getUser().player.ref) ;
        System.out.println("You are: "+ game.getUser().player.getClass());
        while (huppy.hasNext()){
            Entry entry = (Entry) huppy.next();
            Player gfg  = (Player)  entry.getValue();
            System.out.println("Player: " + (int) entry.getKey());
        }
        if (game.getUser().player instanceof Mafia && game.player_details.containsKey(game.getUser().player.ref)) {
            game.getUser().player.takeInput(0, game.getPlayerDetails());
        }
        else{
          if (mafia != null)  mafia.takeInput(1, game.getPlayerDetails());
          System.out.println("The mafia have chosen their kill. ");
        }
        if (game.getUser().player instanceof Detective && game.getPlayerDetails().containsKey(game.getUser().player.ref)){
            game.getUser().player.takeInput(0 ,game.getPlayerDetails());
        }
        else {
           if (detective != null) detective.takeInput(1, game.getPlayerDetails());
           System.out.println("The detectives have done the work. ");
        }
        if (game.getUser().player instanceof  Healer && game.getPlayerDetails().containsKey(game.getUser().player.ref)){
            game.getUser().player.takeInput(0, game.getPlayerDetails());
        }
        else{
            if (healer != null)healer.takeInput(1, game.getPlayerDetails());
            System.out.println("The healer has healed. ");
        }
        CleanUpPlayers();
        Voting();

        if (endGame()){
            System.out.println("GAME OVER");
            Iterator<Map.Entry<Integer, Player>> aj = game.getPlayerDetails().entrySet().iterator();
            while (aj.hasNext()){
                Map.Entry<Integer, Player> kl = (Map.Entry<Integer, Player>) aj.next();
                Player juppa = (Player) kl.getValue();
                System.out.println("Player no. " + (int)kl.getKey() + " is " + juppa.getClass());
            }
            return;
        }
        else{
            System.out.println("NEXT ROUND STARTING NOW...");
            letsPlay();
        }
    }
    public void Voting(){
        if (game.getPlayerDetails().containsKey((int)game.getUser().player.ref)){
            int g = takeInputFromUser();
        }
        Iterator hIt = game.getPlayerDetails().entrySet().iterator();
        HashMap<Player, Integer> treeMap = new HashMap<>();
        Player dip = new Commoner(); int max_votes = 0;
        while (hIt.hasNext()){
            Entry mapElement = (Entry)hIt.next();
            Player pubg = (Player) mapElement.getValue();
            int jubb = (Integer) mapElement.getKey();
            if (game.getPlayerDetails().get(jubb).equals(game.getUser().player)){

            }
            else {
                Player ads = pubg.vote(game.getPlayerDetails());
                treeMap.put(ads, treeMap.getOrDefault(ads, 0) + 1);
            }
        }
        hIt= treeMap.entrySet().iterator(); int l = -1; //int yoohoo = -1;
        while (hIt.hasNext()){
            Entry mapElement = (Entry)hIt.next();
            int s = (Integer) mapElement.getValue();
            if (s > l){
                l = s; dip = (Player) mapElement.getKey();
            }
        }
        hIt = treeMap.entrySet().iterator();
        int tubby = 0;
        while (hIt.hasNext()){
            Entry mapElement = (Entry)hIt.next();
            int s = (Integer) mapElement.getValue();
            if (s == l){
                tubby += 1;

            }
        }
        if (tubby == 1){
            int yoohoo = -1;
            Iterator hubit = game.getPlayerDetails().entrySet().iterator();
            while (hubit.hasNext()){
                Entry mapEle = (Entry)  hubit.next();
                Player jk = (Player) mapEle.getValue();
                if (jk.equals(dip)){
                    yoohoo = (int) mapEle.getKey();
                    break;
                }

            }
            if (yoohoo != -1){
                System.out.println("Player removed: " + yoohoo);
                game.getPlayerDetails().remove(yoohoo);}
        }
        else{
            System.out.println("Votiing will be done again. Tie:-( ");
            Voting();
        }
    }
    public void CleanUpPlayers(){
        Iterator hIt = game.getPlayerDetails().entrySet().iterator();
        HashMap<Player, Integer> treeMap = new HashMap<>();
        ArrayList<Integer> list_of_people_to_remove = new ArrayList<>();
        while(hIt.hasNext()){
            Entry entry = (Entry) hIt.next();
            int s = (int) entry.getKey();
            Player st = (Player) entry.getValue();
            if (st.hp_points == 0 && !(st instanceof Mafia) ){list_of_people_to_remove.add(st.ref);}
        }
        for (int k: list_of_people_to_remove){
            game.getPlayerDetails().remove(k);
        }
        mafia = null;
        detective = null;
        healer = null;
        commoner = null;
    }
    public boolean endGame(){
        Iterator hit = game.player_details.entrySet().iterator();
        int t1 = 0; int t2 = 0;
        while (hit.hasNext()){
            Entry ele = (Entry) hit.next();
            Player hp = (Player) ele.getValue();
            if (hp instanceof Mafia){
                t1 += 1;
            }
            else {
                t2 += 1;
            }
        }
        if (t1 == 0){System.out.println("Mafias loose. Happy life. "); return true;}
        if (t1 == t2){System.out.println("Mafias win. Sed life. "); return true;}
        return false;
    }
    public int takeInputFromUser(){
        Scanner scanner = new Scanner(System.in);
        try{

            System.out.println("Enter who you wanna vote against: ");

            int a = scanner.nextInt();
            if (game.getPlayerDetails().containsKey((int) a)){
                return a;
            }
            else{
                throw new PlayerDoesNotExist();
            }
        }
        catch (InputMismatchException t){
            System.out.println("Please enter integer. ");
            scanner.next();
            return takeInputFromUser();
        }
        catch (PlayerDoesNotExist e){
            System.out.println("Player does not exist sir! ");
            scanner.next();
            return takeInputFromUser();
        }
    }
}



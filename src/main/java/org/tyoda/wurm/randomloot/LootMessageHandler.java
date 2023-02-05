package org.tyoda.wurm.randomloot;

import com.wurmonline.server.Players;
import com.wurmonline.server.Server;
import com.wurmonline.server.creatures.Communicator;
import com.wurmonline.server.players.Player;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Required to bypass weird java stuff.
 */
public class LootMessageHandler {
    public static boolean handleMessage(Communicator communicator, String message){
        if(message.startsWith(RandomLoot.command)){
            String loot = "the loot";
            if(message.length() > RandomLoot.command.length()+1){
                loot = message.substring(RandomLoot.command.length()+1);
            }

            Player sender = communicator.getPlayer();
            float senderX = sender.getPosX();
            float senderY = sender.getPosY();

            Player[] players = Players.getInstance().getPlayers();
            ArrayList<String> names = new ArrayList<>(players.length);
            for(Player player : players){
                float playerX = player.getPosX();
                float playerY = player.getPosY();
                double distance = Math.sqrt(Math.pow(Math.abs(senderX-playerX),2) + Math.pow(Math.abs(senderY-playerY), 2));
                if(     distance <= RandomLoot.distMeters
                     && (player.getPower() < 4 || (player.isVisibleToPlayers() && RandomLoot.includeGmsInRolls))){
                    names.add(player.getName());
                }
            }
            switch(names.size()) {
                case 0:
                    communicator.sendNormalServerMessage("You fail to find anyone eligible "+RandomLoot.distMeters+" meters.");
                    Server.getInstance().broadCastAction(sender.getName()+" looks confused.", sender, RandomLoot.distMeters/4);
                    break;
                case 1:
                    communicator.sendNormalServerMessage("You only find one eligible person for the "+loot+" in "+RandomLoot.distMeters+" meters: "+names.get(0));
                    Server.getInstance().broadCastAction(sender.getName()+" rolled for "+loot+". There was only one eligible person: "+names.get(0), sender, RandomLoot.distMeters/4);
                    break;
                default:
                    Collections.shuffle(names);
                    StringBuilder builder = new StringBuilder()
                            .append(" rolled for ")
                            .append(loot)
                            .append(". Rolling for ")
                            .append(names.size())
                            .append(" people in ")
                            .append(RandomLoot.distMeters)
                            .append(" meters.");
                    communicator.sendNormalServerMessage("You" + builder);
                    Server.getInstance().broadCastAction(sender.getName() + builder, sender, RandomLoot.distMeters / 4);
                    for (int i = 0; i < names.size(); ++i) {
                        String msg = (i + 1) + ". " + names.get(i);
                        communicator.sendNormalServerMessage(msg);
                        Server.getInstance().broadCastAction(msg, sender, RandomLoot.distMeters/4 + 1);
                    }
            }
            return true;
        }
        return false;
    }
}

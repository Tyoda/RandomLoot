package org.tyoda.wurm.randomloot;

import com.mysql.jdbc.log.Log;
import com.wurmonline.server.creatures.Communicator;
import org.gotti.wurmunlimited.modloader.interfaces.*;

import java.util.Properties;
import java.util.logging.Logger;

public class RandomLoot implements WurmServerMod, Versioned, PlayerMessageListener, Configurable {
    public static final String version = "1.0";

    public static final Logger logger = Logger.getLogger(RandomLoot.class.getName());

    public static int distMeters = 15 * 4;
    public static boolean includeGmsInRolls = false;
    public static String command = "#choose";

    public String getVersion(){
        return version;
    }

    @Override
    public void configure(Properties p){
        distMeters = Integer.parseInt(p.getProperty("distMeters", String.valueOf(distMeters)));
        includeGmsInRolls = Boolean.parseBoolean(p.getProperty("includeGmsInRolls", String.valueOf(includeGmsInRolls)));
        command = p.getProperty("command", command);
        if(!command.startsWith("#")){
            command = '#'+command;
        }
    }

    @Override
    public MessagePolicy onPlayerMessage(Communicator communicator, String message, String title) {
        return LootMessageHandler.handleMessage(communicator, message) ? MessagePolicy.DISCARD : MessagePolicy.PASS;
    }

    @Override
    @Deprecated
    public boolean onPlayerMessage(Communicator communicator, String message) {
        return false;
    }
}
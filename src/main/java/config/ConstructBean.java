package config;

import config.domain.message.listeners.MessageReceivedListener;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.Javacord;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by Tim on 17-6-2017.
 */

@Singleton
@Startup
public class ConstructBean {

    private static String token = "MzI1NDMzMDUxODI1ODMxOTM3.DCYKrg.4jCT81a0JPTwTXBxSEC3KYBX5W8";
    private static String PREFIX = "$";

    @PostConstruct
    public void init() {
        DiscordAPI api = Javacord.getApi(token, true);
        api.connectBlocking();
        api.registerListener(new MessageReceivedListener(PREFIX));
    }



}

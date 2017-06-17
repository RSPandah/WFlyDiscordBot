package config.domain.message.listeners;

import config.domain.command.interfaces.ICommand;
import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;
import de.btobastian.javacord.listener.message.MessageCreateListener;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.*;

/**
 * Created by Tim on 17-6-2017.
 */
public class MessageReceivedListener implements MessageCreateListener {

    public MessageReceivedListener(){}

    public MessageReceivedListener(String prefix) {
        this.prefix = prefix;
    }

    private String prefix;
    // A static map of commands mapping from command string to the functional impl
    private static Map<String, ICommand> commandMap = new HashMap<>();
    static
    {
        commandMap.put("hello", (message, args) ->
        {
            message.reply(String.format("Hi %s!", message.getAuthor().getName()));
        });

    }

    @Override
    public void onMessageCreate(DiscordAPI discordAPI, Message message) {

        String[] args = message.getContent().split(" ");

        // First ensure at least the command and prefix is present, the arg length can be handled by your command func
        if(args.length == 0)
            return;

        // Check if the first arg (the command) starts with the prefix defined in the utils class
        if(!args[0].startsWith(prefix))
            return;

        // Extract the "command" part of the first arg out by just ditching the first character
        String commandStr = args[0].substring(1);

        // Load the rest of the args in the array into a List for safer access
        List<String> argsList = new ArrayList<>(Arrays.asList(args));
        argsList.remove(0); // Remove the command


        if(commandMap.containsKey(commandStr))
            commandMap.get(commandStr).runCommand(message, argsList);
    }
}

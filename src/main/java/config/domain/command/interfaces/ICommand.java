package config.domain.command.interfaces;

import de.btobastian.javacord.DiscordAPI;
import de.btobastian.javacord.entities.message.Message;

import java.util.List;

/**
 * Created by Tim on 17-6-2017.
 */
public interface ICommand {

    void runCommand(Message message, List<String> args);
}

package dev.zepnex.listener;

import dev.zepnex.commands.ClearCommand;
import dev.zepnex.commands.PingCommand;
import dev.zepnex.commands.type.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import java.util.concurrent.ConcurrentHashMap;


public class CommandManager {
    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager() {

        this.commands = new ConcurrentHashMap<>();
        this.commands.put("clear", new ClearCommand());
        this.commands.put("ping", new PingCommand());
    }

    public boolean perform(String command, Member member, TextChannel textChannel, Message msg) {
        ServerCommand cmd;

        if ((cmd = this.commands.get(command.toLowerCase())) != null) {
            cmd.performCommand(member, textChannel, msg);

            return true;
        }

        return false;
    }
}

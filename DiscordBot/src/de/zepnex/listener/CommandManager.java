package de.zepnex.listener;

import java.util.concurrent.ConcurrentHashMap;

import commands.ClearCommand;
import commands.Ping;
import commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CommandManager {
	public ConcurrentHashMap<String, ServerCommand> commands;

	public CommandManager() {

		this.commands = new ConcurrentHashMap<>();
		this.commands.put("clear", new ClearCommand());
		this.commands.put("ping", new Ping());
	}

	public boolean peform(String command, Member member, TextChannel textChannel, Message msg) {
		ServerCommand cmd;

		if ((cmd = this.commands.get(command.toLowerCase())) != null) {
			cmd.performCommand(member, textChannel, msg);

			return true;
		}

		return false;
	}
}

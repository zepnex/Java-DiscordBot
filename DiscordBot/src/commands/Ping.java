package commands;

import commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

public class Ping implements ServerCommand {

	@Override
	public void performCommand(Member member, TextChannel textChannel, Message msg) {
		Message message = msg;
		MessageChannel channel = textChannel;
		long time = System.currentTimeMillis();
		channel.sendMessage("Pong!") /* => RestAction<Message> */
				.queue(response /* => Message */ -> {
					response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
				});

	}

}

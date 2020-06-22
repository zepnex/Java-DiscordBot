package commands;

import java.util.List;
import java.util.concurrent.TimeUnit;

import commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

public class ClearCommand implements ServerCommand {

	@Override
	public void performCommand(Member member, TextChannel textChannel, Message msg) {

		if (member.hasPermission(textChannel, Permission.MESSAGE_MANAGE)) {
			msg.delete().queue();

			String[] args = msg.getContentDisplay().split(" ");
			if (args.length == 2) {

				try {
					int amount = Integer.parseInt(args[1]);
					purgeMessages(textChannel, amount);
					textChannel.sendMessage(amount + " messages has been deleted.").complete().delete().queueAfter(3,
							TimeUnit.SECONDS);
					return;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void purgeMessages(TextChannel channel, int numberofMessages) {
		MessageHistory history = new MessageHistory(channel);
		List<Message> msgs;
		msgs = history.retrievePast(numberofMessages).complete();
		if (msgs.size() == 0) {
			channel.sendMessage("This channel is already clear").complete().delete().queueAfter(3, TimeUnit.SECONDS);
			return;
		}
		channel.deleteMessages(msgs).queue();

	}

}

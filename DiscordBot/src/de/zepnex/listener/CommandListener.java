package de.zepnex.listener;

import de.zepnex.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		String msg = event.getMessage().getContentDisplay();
		if (event.isFromType(ChannelType.TEXT)) {
			TextChannel textChannel = event.getTextChannel();
			if (msg.startsWith("-")) {
				String[] args = msg.substring(1).split(" ");
				if (args.length > 0) {
					if (!Main.INSTANCE.getCmdMan().peform(args[0], event.getMember(), textChannel,
							event.getMessage())) {
						textChannel.sendMessage("`Unknowen Command`");
					}
				}
			}
		}

	}

}

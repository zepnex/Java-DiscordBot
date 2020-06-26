package dev.zepnex.commands;

import dev.zepnex.commands.type.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class PingCommand implements ServerCommand {
    @Override
    public void performCommand(Member member, TextChannel textChannel, Message msg) {
        long time = System.currentTimeMillis();
        textChannel.sendMessage("Pong!") /* => RestAction<Message> */
                .queue(response /* => Message */ -> {
                    response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
                });

    }
}

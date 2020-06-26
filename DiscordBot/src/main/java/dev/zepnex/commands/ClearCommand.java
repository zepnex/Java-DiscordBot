package dev.zepnex.commands;

import dev.zepnex.commands.type.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand implements ServerCommand {

    @Override
    public void performCommand(Member member, TextChannel textChannel, Message msg) {

        if (member.hasPermission(textChannel, Permission.MESSAGE_MANAGE)) {
            msg.delete().queue();

            String[] args = msg.getContentDisplay().split(" ");
            if (args.length == 2) {

                try {
                    int amount = Integer.parseInt(args[1]);
                    if (amount <= 100) {
                        purgeMessages(textChannel, amount);
                        textChannel.sendMessage(amount + " messages has been deleted.")
                                .complete().delete().queueAfter(3,
                                TimeUnit.SECONDS);
                    } else {
                        textChannel.sendMessage("``Error can't delete more then 100 Messages``")
                                .complete().delete().queueAfter(3, TimeUnit.SECONDS);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void purgeMessages(TextChannel channel, int numberOfMessages) {
        List<Message> history = new MessageHistory(channel).retrievePast(numberOfMessages).complete();
        List msg =channel.purgeMessages(history);
        System.out.println(msg);

    }
}

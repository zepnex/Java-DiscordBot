package dev.zepnex.commands.type;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface ServerCommand {
    public void performCommand(Member member, TextChannel textChannel, Message msg);
}

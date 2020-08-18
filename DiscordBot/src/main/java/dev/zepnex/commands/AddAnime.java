package dev.zepnex.commands;

import dev.zepnex.commands.type.ServerCommand;
import dev.zepnex.utils.Database;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class AddAnime implements ServerCommand {
    @Override
    public void performCommand(Member member, TextChannel textChannel, Message msg) {
        Database.insert(msg, true);
    }
}

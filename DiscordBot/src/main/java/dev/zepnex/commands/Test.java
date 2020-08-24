package dev.zepnex.commands;

import dev.zepnex.commands.type.ServerCommand;
import dev.zepnex.utils.Database;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.Array;

public class Test implements ServerCommand {
    @Override
    public void performCommand(Member member, TextChannel textChannel, Message msg) {
        long startTime = System.nanoTime();
        String[] data = Database.selectAll("moviesuggestions");
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Select all took: "
                + elapsedTime / 1000000 + " milliseconds");

        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }


    }
}

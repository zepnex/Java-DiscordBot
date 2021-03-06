package dev.zepnex.commands;

import dev.zepnex.utils.Database;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AnimeSuggestion extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String filmSuggestions = "718925322916986931";
        String id = "718919698493734952";

        MessageChannel planToWatch = event.getGuild().getTextChannelById(id);
        MessageChannel channel = event.getChannel();
        String messageID = event.getMessageId();

        if (channel.getId().equalsIgnoreCase(filmSuggestions)) {
            String movie = event.getMessage().getContentStripped();
            movie = movie.replace("\n", "`");
            String[] content = movie.split("`");
            List<Message> history = new MessageHistory(planToWatch).retrievePast(20).complete();

            if (movie.startsWith("https://anilist.co/")) {
                if (convertList(history).contains(movie)) {
                    channel.deleteMessageById(messageID).complete();
                    channel.sendMessage("This movie is already planned to be watched")
                            .complete().delete().queueAfter(3, TimeUnit.SECONDS);
                } else {
                    if (movie.endsWith("needs anime")) {
                        long startTime = System.nanoTime();
                        String values = content[0] + "," + true;
                        String keys = "movielink, needsanime";
                        Database.insert("moviesuggestions", values, keys);
                        long elapsedTime = System.nanoTime() - startTime;
                        System.out.println("Insert took: " + elapsedTime / 1000000 + " milliseconds");
                    } else {
                        long startTime = System.nanoTime();
                        String values = content[0] + "," + false;
                        String keys = "movielink, needsanime";
                        Database.insert("moviesuggestions", values, keys);
                        long elapsedTime = System.nanoTime() - startTime;
                        System.out.println("Insert took: " + elapsedTime / 1000000 + " milliseconds");
                    }
                }
            }
        }
    }

    private List<String> convertList(List<Message> history) {
        List<String> convertedList = new ArrayList<String>();
        for (int i = 0; i < history.size(); i++) {
            convertedList.add(history.get(i).getContentDisplay());
        }
        return convertedList;
    }


}

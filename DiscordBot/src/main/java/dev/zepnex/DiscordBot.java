package dev.zepnex;

import com.google.gson.Gson;
import dev.zepnex.commands.AnimeSuggestion;
import dev.zepnex.commands.events.MemberJoin;
import dev.zepnex.listener.CommandListener;
import dev.zepnex.listener.CommandManager;
import dev.zepnex.utils.BotInformation;
import dev.zepnex.utils.Database;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.*;


public class DiscordBot {
    public static DiscordBot INSTANCE;
    private static BotInformation inf;
    public JDABuilder builder;
    private CommandManager cmdMan;
    static String path = "src\\main\\java\\dev\\zepnex\\utils\\";

    public static void main(String[] args) throws LoginException {
        Gson gson = new Gson();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path + "BotInf.json"));
            inf = gson.fromJson(reader, BotInformation.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        new Database().createTables();
        new DiscordBot();


    }

    public DiscordBot() throws LoginException {

        INSTANCE = this;
        builder = new JDABuilder(inf.getToken());
        this.cmdMan = new CommandManager();
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new MemberJoin());
        builder.addEventListeners(new AnimeSuggestion());
        builder.setActivity(Activity.playing("reviewing animes"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.build();
        new Database().createTables();
        shutdown();


    }

    public void shutdown() {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            while ((line = reader.readLine()) != null) {
                if (line.equalsIgnoreCase("exit")) {
                    if (builder != null) {
                        System.out.println("Bot offline");
                        System.exit(1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CommandManager getCmdMan() {
        return cmdMan;
    }
}

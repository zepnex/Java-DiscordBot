package dev.zepnex;

import dev.zepnex.listener.CommandListener;
import dev.zepnex.listener.CommandManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class DiscordBot {
    public static DiscordBot INSTANCE;
    public JDABuilder builder;
    private CommandManager cmdMan;

    public static void main(String[] args) throws LoginException {
        new DiscordBot();
    }

    public DiscordBot() throws LoginException {
        INSTANCE = this;
        builder = new JDABuilder("NzI0MzU4OTM5NDU3ODgwMDc2.XvE1iw.wQIGkd_gvZ3WoZcf4iB2Gp02WWU");
        this.cmdMan = new CommandManager();
        builder.addEventListeners(new CommandListener());
        builder.setActivity(Activity.playing("Mooooin Maister"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.build();
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

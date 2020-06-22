package de.zepnex;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.security.auth.login.LoginException;

import de.zepnex.listener.CommandListener;
import de.zepnex.listener.CommandManager;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

public class Main {
	public static Main INSTANCE;
	public ShardManager shardMan;
	private CommandManager cmdMan;

	public static void main(String[] args) throws LoginException {

		new Main();

	}

	public Main() throws LoginException, IllegalArgumentException {
		INSTANCE = this;
		@SuppressWarnings("deprecation")
		DefaultShardManagerBuilder builder = new DefaultShardManagerBuilder();
		builder.setToken("NzI0MzU4OTM5NDU3ODgwMDc2.Xu_CIg.M_r_gyFpBWBZay9ORepoTZv6d-M");
		this.cmdMan = new CommandManager();
		builder.addEventListeners(new CommandListener());
		builder.setActivity(Activity.playing("Maple is beautiful"));
		shardMan = builder.build();
		shutdown();

	}

	public void shutdown() {

		new Thread(() -> {
			String line = "";
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {

				while ((line = reader.readLine()) != null) {
					if (line.equalsIgnoreCase("exit")) {
						if (shardMan != null) {
							shardMan.setStatus(OnlineStatus.OFFLINE);
							shardMan.shutdown();

							System.out.println("Bot offline");
							System.exit(1);
						}
					} else {

						System.out.println("Use 'exit' to shut down");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

	}

	public CommandManager getCmdMan() {
		return cmdMan;
	}
}

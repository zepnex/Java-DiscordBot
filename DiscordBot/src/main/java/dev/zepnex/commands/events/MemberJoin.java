package dev.zepnex.commands.events;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Random;


public class MemberJoin extends ListenerAdapter {
    String[] messages = {
            "[member] joined. You must construct additional pylons.",
            "Never gonna give [member] up. Never let [member] down!",
            "Hey! Listen! [member] has joined!",
            "Ha! [member] has joined! You activated my trap card!",
            "We've been expecting you, [member].",
            "It's dangerous to go alone, take [member]!",
            "Swoooosh. [member] just landed.",
            "Brace yourselves. [member] just joined the server.",
            "A wild [member] appeared."
    };

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Random r = new Random();
        int random = r.nextInt(messages.length);
        Member member = event.getMember();
        Guild guild = event.getGuild();
        TextChannel channel = guild.getDefaultChannel();
        List<Role> role = guild.getRolesByName("Test",false);
        System.out.println(role.size());
        EmbedBuilder join = new EmbedBuilder();
        join.setColor(0x66d8ff);
        join.setDescription(messages[random].replace("[member]", member.getEffectiveName()));
        channel.sendMessage(join.build()).queue();
        if(role.size()==0){
            System.err.println("Role does not exist");
        }else{
             guild.addRoleToMember(member, role.get(0)).queue();
        }


    }


}

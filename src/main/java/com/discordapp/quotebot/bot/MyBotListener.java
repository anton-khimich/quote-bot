package com.discordapp.quotebot.bot;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MyBotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        User author = message.getAuthor();
        String content = message.getContentDisplay();
        if (event.isFromType(ChannelType.TEXT)) {
            if (content.startsWith("!q")) {
                MessageChannel channel = event.getChannel();
                channel.sendMessage("<@" + author.getAsTag() + "> Command recognized.").queue();
            }
        }
    }
}

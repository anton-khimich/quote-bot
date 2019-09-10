package com.discordapp.quotebot.bot;

import net.dv8tion.jda.core.MessageBuilder;
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
        Message reply;
        if (event.isFromType(ChannelType.TEXT)) {
            MessageChannel channel = event.getChannel();
            if (content.startsWith("!qsave")) {
                reply = new MessageBuilder().append(author).append("Save quote.").build();
                channel.sendMessage(reply).queue();
            } else if (content.startsWith("!qdelete")) {
                reply = new MessageBuilder().append(author).append("Delete quote.").build();
                channel.sendMessage(reply).queue();
            } else if (content.startsWith("!qlist")) {
                reply = new MessageBuilder().append(author).append("Quote list.").build();
                channel.sendMessage(reply).queue();
            } else if (content.startsWith("!q")) {
                if (content.equals("!q")) {
                    reply = new MessageBuilder().append(author).append("Daily quote.").build();
                    channel.sendMessage(reply).queue();
                } else if (content.matches("!q .+")) {
                    reply = new MessageBuilder().append(author).append("Specific quote.").build();
                    channel.sendMessage(reply).queue();
                }
            }
        }
    }
}

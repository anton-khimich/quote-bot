package com.discordapp.quotebot.bot;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.json.JSONObject;

public class MyBotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();
        User author = message.getAuthor();
        String content = message.getContentDisplay();
        String guild = event.getGuild().getId();
        Message reply;

        if (event.isFromType(ChannelType.TEXT)) {
            MessageChannel channel = event.getChannel();
            // Save the given quote
            if (content.startsWith("!qsave")) {
                content = content.substring(7);
                content += ",contributor:" + author.toString();
                JSONObject obj = new JSONObject("{" + content + "}");
                Bot.saveQuote(obj, guild);
                reply = new MessageBuilder().append(author).append(" Save quote.").build();
                channel.sendMessage(reply).queue();
            // Delete the quote by name
            } else if (content.startsWith("!qdelete")) {
                content = content.substring(3);
                Bot.deleteQuote(content, guild);
                reply = new MessageBuilder().append(author).append(" Delete quote.").build();
                channel.sendMessage(reply).queue();
            // Display the list of quotes saved in the discord channel
            } else if (content.startsWith("!qlist")) {
                reply = new MessageBuilder().append(author).append(" Displaying a list of quotes is not yet implemented.").build();
                channel.sendMessage(reply).queue();
            // Display the quote of the day
            } else if (content.equals("!q")) {
                reply = new MessageBuilder().append(author).append(" Daily quote is not yet implemented.").build();
                channel.sendMessage(reply).queue();
            // Display a quote by name
            } else if (content.matches("!q .+")) {
                content = content.substring(3);
                reply = new MessageBuilder().append(author).append(" " + Bot.getSpecificQuote(content, guild)).build();
                channel.sendMessage(reply).queue();
            }
        }
    }
}

package com.discordapp.quotebot.bot;

import com.discordapp.quotebot.entity.Quote;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.util.MissingFormatArgumentException;

@Service
public class Bot {
    private static Quote dailyQuote;
    private static LocalDate dailyQuoteDate;

    public Bot() {
        try {
            String token = "NTU4ODI5MjYzNzMwNzY5OTIw.XKJAxw.R6af-HsIKYZm0TySlFUNNbGJ_CQ";
            JDA jda = new JDABuilder(token)
                    .addEventListener(new MyBotListener())
                    .build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static Quote getDailyQuote() {
        LocalDate dateNow = LocalDate.now();
        if (dailyQuote == null) {
            //pick a random quote and note the date
        } else if (dateNow.isAfter(dailyQuoteDate)) {
            //pick a new quote and note the date
        }
        return dailyQuote;
    }

    static Quote getSpecificQuote(String name) {
        return new Quote();
    }

    static void saveQuote(JSONObject obj) {
        String quoteName = obj.getString("name");
        String quoteContent = obj.getString("content");
        String quoteAuthor = obj.getString("author");
        String quoteContributor = obj.getString("contributor");
        if (quoteName != null && quoteContent != null && quoteAuthor != null && quoteContributor != null) {
            // add the quote to the repository
        } else {
            throw new IllegalArgumentException("Incorrect quote format.");
        }
    }

    static void deleteQuote(String name) {

    }

}

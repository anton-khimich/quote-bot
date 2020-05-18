package com.discordapp.quotebot.bot;

import com.discordapp.quotebot.entity.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.MissingFormatArgumentException;

@Service
public class Bot {
    private static Quote dailyQuote;
    private static LocalDate dailyQuoteDate;
    private static CloseableHttpClient httpClient;

    public Bot() {
        try {
            String token = "NTU4ODI5MjYzNzMwNzY5OTIw.Xk3D4g.KJ-S1aMsdSGSHJ4viPvb_I2KxfY";
            JDA jda = new JDABuilder(token)
                    .addEventListener(new MyBotListener())
                    .build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        httpClient = HttpClientBuilder.create().build();
    }

    private static HttpPost generateRequest(String data) {
        HttpPost request = new HttpPost("http://35.231.222.180:8080/graphql");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept-Encoding", "gzip, deflate, br");
        request.addHeader("Accept", "application/json");
        request.addHeader("DNT", "1");
        request.addHeader("Origin", "file://");
        try {
            request.setEntity(new StringEntity(data));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return request;
    }

    private static JSONObject sendRequest(HttpPost request) {
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            String json_string = EntityUtils.toString(response.getEntity());
            return new JSONObject(json_string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    static JSONObject getSpecificQuote(String name, String guild) {
        String data = "{\"query\":\"{quoteByGuildAndName(guild: \\\"" + guild + "\\\", name: \\\"" +
                name + "\\\") {id, name content author contributor guild}}\"}";
        HttpPost request = Bot.generateRequest(data);
        return Bot.sendRequest(request);
    }

    static String saveQuote(JSONObject obj, String guild) {
        String quoteName = obj.getString("name");
        String quoteContent = obj.getString("content");
        String quoteAuthor = obj.getString("author");
        String quoteContributor = obj.getString("contributor");
        if (quoteName != null && quoteContent != null && quoteAuthor != null && quoteContributor != null) {
            // add the quote to the repository
            String data = "{\"query\":\"{saveQuote(guild: \\\"" + guild + "\\\", name: \\\"" +
                    quoteName + "\\\") {id, name content author contributor guild}}\"}";
            HttpPost request = Bot.generateRequest(data);
            if (Bot.sendRequest(request) != null) {
                return "Successfully saved the quote.";
            }
        } else {
            throw new IllegalArgumentException("Incorrect quote format.");
        }
        return "Failed to save the quote.";
    }

    static void deleteQuote(String name, String guild) {

    }

}

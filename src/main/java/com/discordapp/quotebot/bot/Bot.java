package com.discordapp.quotebot.bot;

import net.dv8tion.jda.core.JDABuilder;
import org.springframework.stereotype.Service;
import net.dv8tion.jda.core.JDA;
import javax.security.auth.login.LoginException;

@Service
public class Bot {

    public Bot() {
        try {
            String token = "";
            JDA jda = new JDABuilder(token)
                    .addEventListener(new MyBotListener())
                    .build();
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}

package com.discordapp.quotebot.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;


@Data @Builder @Entity(name = "quotes")
public class Quote {
    @Id
    String id;
    String name;
    String content;
    String author;
    String contributor;
}

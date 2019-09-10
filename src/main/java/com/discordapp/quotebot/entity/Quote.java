package com.discordapp.quotebot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "quotes")
public class Quote {
    @Id Long id;
    String name;
    String content;
    String author;
    String contributor;
    String guild;
}

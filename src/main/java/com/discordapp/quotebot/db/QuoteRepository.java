package com.discordapp.quotebot.db;

import com.discordapp.quotebot.entity.Quote;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.List;

public interface QuoteRepository extends DatastoreRepository<Quote, String> {
    List<Quote> findByGuildAndName(String guild, String name);
    List<Quote> deleteByGuildAndName(String guild, String name);
}

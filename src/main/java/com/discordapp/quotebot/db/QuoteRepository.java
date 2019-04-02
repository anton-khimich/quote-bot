package com.discordapp.quotebot.db;

import com.discordapp.quotebot.entity.Quote;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import java.util.Optional;

public interface QuoteRepository extends DatastoreRepository<Quote, String> {
}

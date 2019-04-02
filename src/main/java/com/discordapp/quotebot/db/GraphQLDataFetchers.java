package com.discordapp.quotebot.db;

import com.discordapp.quotebot.entity.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {

    private QuoteRepository quoteRepository;

    @Autowired
    public GraphQLDataFetchers(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    DataFetcher getQuoteByGuildAndNameDataFetcher() {
        return dataFetchingEnvironment -> {
            String quoteName = dataFetchingEnvironment.getArgument("name");
            String quoteGuild = dataFetchingEnvironment.getArgument("guild");
            return quoteRepository.findByGuildAndName(quoteGuild, quoteName).get(0);
        };
    }

    DataFetcher saveQuoteDataFetcher() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            Quote quote = mapper.convertValue(dataFetchingEnvironment.getArgument("quote"), Quote.class);
            quoteRepository.save(quote);
            return quote;
        };
    }

    DataFetcher deleteQuoteDataFetcher() {
        return dataFetchingEnvironment -> {
            String quoteName = dataFetchingEnvironment.getArgument("name");
            String quoteGuild = dataFetchingEnvironment.getArgument("guild");
            quoteRepository.deleteByGuildAndName(quoteGuild, quoteName);
            return null;
        };
    }

}

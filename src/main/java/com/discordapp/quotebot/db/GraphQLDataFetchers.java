package com.discordapp.quotebot.db;

import com.discordapp.quotebot.entity.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class GraphQLDataFetchers {

    private QuoteRepository quoteRepository;

    @Autowired
    public GraphQLDataFetchers(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    DataFetcher getQuoteByNameDataFetcher() {
        return dataFetchingEnvironment -> {
            String quoteName = dataFetchingEnvironment.getArgument("name");
            return quoteRepository.findById(quoteName);
        };
    }

    DataFetcher saveQuoteDataFetcher() {
        return dataFetchingEnvironment -> {
            ObjectMapper mapper = new ObjectMapper();
            Quote quote = mapper.convertValue(dataFetchingEnvironment.getArgument("input"), Quote.class);
            quoteRepository.save(quote);
            return quote;
        };
    }

    DataFetcher deleteQuoteDataFetcher() {
        return dataFetchingEnvironment -> {
            String quoteName = dataFetchingEnvironment.getArgument("name");
            quoteRepository.deleteById(quoteName);
            return null;
        };
    }

}

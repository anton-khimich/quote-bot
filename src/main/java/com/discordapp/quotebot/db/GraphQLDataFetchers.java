package com.discordapp.quotebot.db;

import com.discordapp.quotebot.entity.Quote;
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

    DataFetcher getQuoteByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String quoteId = dataFetchingEnvironment.getArgument("id");
            return quoteRepository.findById(quoteId);
        };
    }

    DataFetcher saveQuoteDataFetcher() {
        return dataFetchingEnvironment -> {
            LinkedHashMap<String, String> quoteMap = dataFetchingEnvironment.getArgument("input");
            String id = quoteMap.get("id");
            String name = quoteMap.get("name");
            String content = quoteMap.get("content");
            String author = quoteMap.getOrDefault("author", "Unknown");
            String contributor = quoteMap.get("contributor");
            Quote quote = Quote.builder().id(id).name(name).content(content).author(author).contributor(contributor).build();
            quoteRepository.save(quote);
            return quote;
        };
    }

}

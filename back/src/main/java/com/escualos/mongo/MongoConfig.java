package com.escualos.mongo;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.OffsetDateTime;
import java.util.Arrays;

@Configuration
@EnableMongoRepositories("com.escualos.core")
public class MongoConfig {
    static final String DATE_TIME = "dateTime";
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new OffsetDateTimeToDocumentConverter(),
                new DocumentToOffsetDateTimeConverter()
        ));
    }

    @ReadingConverter
    public static class DocumentToOffsetDateTimeConverter implements Converter<Document, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(Document document) {
            String dateTime = document.getString(DATE_TIME);
            return OffsetDateTime.parse(dateTime);
        }
    }

    @WritingConverter
    public static class OffsetDateTimeToDocumentConverter implements Converter<OffsetDateTime, Document> {
        @Override
        public Document convert(OffsetDateTime offsetDateTime) {
            Document document = new Document();
            document.put(DATE_TIME, offsetDateTime.toString());
            return document;
        }
    }
}

package com.sbrown52.examples.mongodbspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${spring.data.mongodb.collection}")
    private String collection;

    @Value("${underlyingDatabase.sqlQuery}")
    private String sqlQuery;

    @Value("${underlyingDatabase.driver}")
    private String driver;

    @Value("${underlyingDatabase.url}")
    private String url;

    public DataSource initialDatasource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        return dataSourceBuilder.build();
    }

    @Bean
    public JdbcCursorItemReader<Dog> reader() {
        return new JdbcCursorItemReaderBuilder<Dog>()
                .dataSource(initialDatasource())
                .name("dogReader")
                .sql(sqlQuery)
                .rowMapper(new DogMapper())
                .build();
    }

    @Bean
    public DogItemProcessor processor() {
        return new DogItemProcessor();
    }

    @Bean
    public MongoItemWriter<Dog> writer() {
        return new MongoItemWriterBuilder<Dog>()
                .collection(collection)
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Job importUserJob(JobNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importDogJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(MongoItemWriter<Dog> writer) {
        return stepBuilderFactory.get("step1")
                .<Dog, Dog> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}

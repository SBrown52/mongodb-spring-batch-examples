package com.sbrown52.examples.mongodbspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${processing.linesToSkip}")
    private int linesToSkip;

    @Value("${spring.data.mongodb.collection}")
    private String collection;

    @Value("${processing.rawFile}")
    private String rawFile;

    @Bean
    public FlatFileItemReader<Dog> reader() {
        return new FlatFileItemReaderBuilder<Dog>()
                .name("dogItemReader")
                .resource(new FileSystemResource(rawFile))
                .delimited()
                .names(new String[]{"name", "age", "breed", "colour"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Dog.class);
                }})
                .linesToSkip(linesToSkip)
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

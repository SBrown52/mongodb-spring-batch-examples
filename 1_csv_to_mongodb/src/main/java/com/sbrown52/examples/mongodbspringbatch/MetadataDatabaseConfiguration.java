package com.sbrown52.examples.mongodbspringbatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

//@Configuration
public class MetadataDatabaseConfiguration {

    @Value("${metadata.database.driver}")
    private String driver;

    @Value("${metadata.database.url}")
    private String url;

    @Value("${metadata.database.user}")
    private String user;

    @Value("${metadata.database.pass}")
    private String pass;

    @Bean
    public DataSource springBatchDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }

}

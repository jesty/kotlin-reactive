package com.kotlincon.mvcdemo

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PostresConfig {

    // https://github.com/spring-projects/spring-data-r2dbc/issues/150

    @Bean
    fun connectionFactory(): ConnectionFactory = PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                    .host("localhost")
                    .port(5432)
                    .username("postgres")
                    .password("password")
                    .database("kotlinconf")
                    .build())

}
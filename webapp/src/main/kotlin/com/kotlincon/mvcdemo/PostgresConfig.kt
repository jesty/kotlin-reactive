package com.kotlincon.mvcdemo

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import io.r2dbc.spi.Option
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class PostgresConfig {

    @Bean
    fun connectionFactoryBuilder(properties: R2dbcProperties): ConnectionFactory? = ConnectionFactories
            .get(builder()
                    .from(parse(properties.url))
                    .option(USER, properties.username)
                    .option(PASSWORD, properties.password)
                    .option(Option.valueOf("maxSize"), properties.pool.maxSize)
                    .option(Option.valueOf("initialSize"), properties.pool.initialSize)
                    .option(DATABASE, "postgres")
                    .build())

}
package com.example.springJooqSample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJooqSampleApplication

fun main(args: Array<String>) {
    runApplication<SpringJooqSampleApplication>(*args)
}

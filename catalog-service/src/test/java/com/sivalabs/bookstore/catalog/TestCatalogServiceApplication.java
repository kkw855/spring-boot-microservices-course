package com.sivalabs.bookstore.catalog;

import org.springframework.boot.SpringApplication;

public class TestCatalogServiceApplication {

    static void main(String[] args) {
        SpringApplication.from(CatalogServiceApplication::main)
                .with(ContainerConfig.class)
                .run(args);
    }
}

package com.sivalabs.bookstore.catalog.domain;

import java.math.BigDecimal;

public record Product(
  String code,
  String name,
  String description,
  String imageUrl,
  BigDecimal price
) {
  static Product from(ProductEntity entity) {
    return new Product(
      entity.getCode(),
      entity.getName(),
      entity.getDescription(),
      entity.getImageUrl(),
      entity.getPrice()
    );
  }
}

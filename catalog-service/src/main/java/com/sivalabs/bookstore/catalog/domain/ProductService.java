package com.sivalabs.bookstore.catalog.domain;

import com.sivalabs.bookstore.catalog.ApplicationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductService {
  private final ProductRepository productRepository;
  private final ApplicationProperties applicationProperties;

  ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
    this.productRepository = productRepository;
    this.applicationProperties = applicationProperties;
  }

  public PagedResult<Product> getProducts(int pageNo) {
    Sort sort = Sort.by("name").ascending();
    int pageNumber = pageNo <= 1 ? 0 : pageNo - 1;
    Pageable pageable = PageRequest.of(pageNumber, applicationProperties.pageSize(), sort);
    Page<Product> productsPage = productRepository.findAll(pageable).map(Product::from);

    return new PagedResult<>(
      productsPage.getContent(),
      productsPage.getTotalElements(),
      productsPage.getNumber() + 1,
      productsPage.getTotalPages(),
      productsPage.isFirst(),
      productsPage.isLast(),
      productsPage.hasNext(),
      productsPage.hasPrevious()
    );
  }

  public Optional<Product> getProductByCode(String code) {
    return productRepository.findByCode(code).map(Product::from);
  }
}

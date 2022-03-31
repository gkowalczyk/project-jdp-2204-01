package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getName(),
                product.getPrice()
        );
    }

    public static List<ProductDto> mapToProductDtoList(final Map<Long, Product> products) {
        List<Product> collect = new ArrayList<>(products.values());

        return collect.stream()
                .map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }
}

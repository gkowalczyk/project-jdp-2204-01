package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroup()
        );
    }

    public static List<ProductDto> mapToProductDtoList(final Map<Long, Product> products) {
        List<Product> collect = new ArrayList<>(products.values());

        return collect.stream()
                .map(ProductMapper::mapToProductDto)
                .collect(Collectors.toList());
    }
}

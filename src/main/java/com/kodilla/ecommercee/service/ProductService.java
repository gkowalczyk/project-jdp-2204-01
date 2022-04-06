package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();

    public List<ProductDto> getAllProducts() {
        return ProductMapper.mapToProductDtoList(products);
    }

    public void createProduct(ProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getGroup());
        products.put(productDto.getId(), product);
    }

    public void editProduct(Long id, ProductDto productDto) {
        Product product = products.get(id);
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        products.put(id, product);
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }

    public ProductDto getProduct(Long id) {
        Product product = products.get(id);
        if(product != null) {
            return ProductMapper.mapToProductDto(product);
        }
        return null;
    }
}

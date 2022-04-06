package com.kodilla.ecommercee;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto productDto = productService.getProduct(id);
        if(productDto != null) {
            return ResponseEntity.ok().body(productDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto product) {
        productService.createProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editProduct(@PathVariable Long id,
                                            @RequestBody ProductDto product) {
        productService.editProduct(id, product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}

package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public ProductDTO findById(Long id) throws Exception {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return new ProductDTO(product);
        }
        else {
            throw new Exception("Product not found for id: " + id);
        }
    }

    @Transactional
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> product = repository.findAll(pageable);
        return product.map(ProductDTO::new);
    }
}

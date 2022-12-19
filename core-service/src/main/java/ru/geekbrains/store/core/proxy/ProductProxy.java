package ru.geekbrains.store.core.proxy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.repositories.ProductRepository;
import ru.geekbrains.store.core.services.ProductService;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductProxy extends ProductService {

    private Map<Integer, Page<Product>> cache;

    private final ProductRepository productRepository;

    public ProductProxy(ProductRepository productRepository, ProductRepository productRepository1) {
        super(productRepository);
        this.productRepository = productRepository1;
        cache = new HashMap<>();
    }

    @Override
    public Page<Product> findAll(Specification<Product> spec, int page) {
        if(!cache.containsKey(page)){
            cache.put(page, productRepository.findAll(spec, PageRequest.of(page, 5)));
        }
        return cache.get(page);
    }
}

package ru.geekbrains.store.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.store.api.CreateNewProductDto;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.repositories.ProductRepository;
import ru.geekbrains.store.core.repositories.specifications.ProductsSpecifications;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findAll(Specification<Product> spec, int page){
        return productRepository.findAll(spec, PageRequest.of(page, 5));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void createNewProduct(CreateNewProductDto createNewProductDto) {
        Product product = new Product();
        product.setTitle(createNewProductDto.getTitle());
        product.setPrice(createNewProductDto.getPrice());
        productRepository.save(product);
    }

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title){
        Specification<Product> spec = Specification.where(null);
        if(minPrice != null){
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        } if(maxPrice != null){
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        } if(title != null){
            spec = spec.and(ProductsSpecifications.titleLike(title));
        }
        return spec;
    }
}

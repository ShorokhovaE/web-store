package ru.geekbrains.spring.webstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.webstore.dtos.CreateNewProductDto;
import ru.geekbrains.spring.webstore.entities.Product;
import ru.geekbrains.spring.webstore.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
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
}

package ru.geekbrains.spring.webstore.services.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.webstore.entities.Product;
import ru.geekbrains.spring.webstore.repositories.ProductRepository;
import ru.geekbrains.spring.webstore.soap.products.ProductXml;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductXmlService {

    private final ProductRepository productRepository;

    public static final Function<Product, ProductXml> functionEntityToSoap = product -> {
        ProductXml productXml = new ProductXml();
        productXml.setId(product.getId());
        productXml.setTitle(product.getTitle());
        productXml.setPrice(product.getPrice());
        return productXml;
    };

    public List<ProductXml> getAllProducts(){
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductXml getById(Long id){
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }


}

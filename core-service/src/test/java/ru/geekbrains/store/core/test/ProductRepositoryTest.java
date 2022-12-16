package ru.geekbrains.store.core.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllProductsTest(){
        Product product = new Product();
        product.setTitle("Cookies");
        product.setId(1234567L);
        product.setPrice(BigDecimal.valueOf(25));

        entityManager.persist(product);
        entityManager.flush();;

        List<Product> products = productRepository.findAll();

        Assertions.assertEquals(4, products.size());
        Assertions.assertEquals("Cookies", products.get(3).getTitle());


    }

}

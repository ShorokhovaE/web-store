package ru.geekbrains.store.core.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.store.api.*;
import ru.geekbrains.store.core.entities.Order;
import ru.geekbrains.store.core.entities.OrderItem;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.integrations.CartServiceIntegration;
import ru.geekbrains.store.core.services.OrderService;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllOrdersTest() throws Exception {
        mvc
                .perform(get("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[4].username", is("Billy")));
    }

    @Test
    public void createOrderTest() throws Exception {
        mvc
                .perform(
                        post("/api/v1/orders/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString("Bill"))
                                .header("username", "Bill")
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }
}

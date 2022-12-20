package ru.geekbrains.store.carts.observer;

import org.springframework.stereotype.Component;
import ru.geekbrains.store.carts.model.CartItem;
import java.util.List;

@Component
public class Subscriber implements Observer {

    @Override
    public void handleEvent(String msq) {
        System.out.println(msq);
    }
}

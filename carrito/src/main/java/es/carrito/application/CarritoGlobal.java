package es.carrito.application;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import java.util.ArrayList;

@Service
@ApplicationScope
public class CarritoGlobal {

    private ArrayList<String> items;

    public CarritoGlobal() {
        this.items = new ArrayList<>();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void add(String item) {
        items.add(item);
    }
}
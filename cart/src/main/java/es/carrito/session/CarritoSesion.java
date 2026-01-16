package es.carrito.session;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;

@Service
@SessionScope
public class CarritoSesion {

    private ArrayList<String> items;

    public CarritoSesion() {
        this.items = new ArrayList<>();
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void add(String item) {
        items.add(item);
    }
}
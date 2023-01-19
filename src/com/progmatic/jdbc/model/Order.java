package com.progmatic.jdbc.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record Order(Long oid, Client client, Courier courier, List<OrderItem> items, LocalDateTime orderedAt) {
    @Override
    public String toString() {
        StringBuilder pizzas = new StringBuilder();
        for (OrderItem i: items) {
            pizzas.append(i.toString());
        }
        return String.format("""
                ---------------------------- %d
                   Vevo: %s
                   Futar: %s
                   Pizzak: %s
                   Idopont: %s
                """,
                oid,
                client.name(),
                courier.name(),
                pizzas.toString(),
                orderedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }
}

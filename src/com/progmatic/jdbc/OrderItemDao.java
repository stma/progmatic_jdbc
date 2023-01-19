package com.progmatic.jdbc;

import com.progmatic.jdbc.model.OrderItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderItemDao implements Dao<OrderItem> {

    private final DBEngine engine;
    private final PizzaDao pizzaDao;

    public OrderItemDao(DBEngine engine, PizzaDao pizzaDao) {
        this.engine = engine;
        this.pizzaDao = pizzaDao;
    }

    @Override
    public OrderItem get(long id) {
        return null;
    }

    @Override
    public List<OrderItem> getAll() {
        return null;
    }

    public List<OrderItem> getAll(Long orderId) {
        List<OrderItem> all = new LinkedList<>();

        try (
            PreparedStatement s = engine.getConnection().prepareStatement("SELECT * FROM tetel WHERE razon = ?;");
        ) {
            s.setLong(1, orderId);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                all.add(resultToOrderItem(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    private OrderItem resultToOrderItem(ResultSet rs) throws SQLException {
        return new OrderItem(
            this.pizzaDao.get(rs.getLong("pazon")),
            rs.getShort("db")
        );
    }

    @Override
    public void save(OrderItem orderItem) {

    }

    @Override
    public void update(OrderItem orderItem, String[] params) {

    }

    @Override
    public void delete(OrderItem orderItem) {

    }
}

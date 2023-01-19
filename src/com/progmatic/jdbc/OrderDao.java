package com.progmatic.jdbc;

import com.progmatic.jdbc.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class OrderDao implements Dao<Order> {

    DBEngine engine;
    CourierDao courierDao;
    ClientDao clientDao;

    OrderItemDao orderItemDao;

    public OrderDao(DBEngine engine, CourierDao courierDao, ClientDao clientDao, OrderItemDao orderItemDao) {
        this.engine = engine;
        this.clientDao = clientDao;
        this.courierDao = courierDao;
        this.orderItemDao = orderItemDao;
    }


    @Override
    public Order get(long id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> all = new LinkedList<>();

        try (
                Statement s = engine.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM rendeles;");
        ) {
            while (rs.next()) {
                all.add(resultToOrder(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    private Order resultToOrder(ResultSet rs) throws SQLException {
        return new Order(
            rs.getLong("razon"),
            clientDao.get(rs.getLong("vazon")),
            courierDao.get(rs.getLong("fazon")),
            orderItemDao.getAll(rs.getLong("razon")),
            rs.getTimestamp("idopont").toLocalDateTime()
        );
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Order order, String[] params) {

    }

    @Override
    public void delete(Order order) {

    }
}

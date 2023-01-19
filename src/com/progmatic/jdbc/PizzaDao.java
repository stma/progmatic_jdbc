package com.progmatic.jdbc;

import com.progmatic.jdbc.model.Pizza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PizzaDao implements Dao<Pizza> {

    private DBEngine engine;

    public PizzaDao(DBEngine e) {
        this.engine = e;
    }
    @Override
    public Pizza get(long id) {
        try (
            PreparedStatement s = engine.getConnection().prepareStatement("SELECT * FROM pizza WHERE pazon = ?;");
        ) {
            s.setLong(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return resultToPizza(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Pizza> getAll() {
        List<Pizza> all = new LinkedList<>();

        try (
            Statement s = engine.getConnection().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM pizza;");
        ) {
            while (rs.next()) {
                all.add(resultToPizza(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    public List<Pizza> getAll(List<Long> ids) {
        List<Pizza> all = new LinkedList<>();

        try (
            PreparedStatement s = engine.getConnection().prepareStatement("SELECT * FROM pizza WHERE pazon in ?;");
        ) {
            s.setString(1, "("+ String.join(",", ids.stream().map(String::valueOf).toList()) +")");
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                all.add(resultToPizza(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    @Override
    public void save(Pizza pizza) {

    }

    @Override
    public void update(Pizza pizza, String[] params) {

    }

    @Override
    public void delete(Pizza pizza) {
        try (
            PreparedStatement s = engine.getConnection().prepareStatement("DELETE FROM pizza WHERE pazon = ?;");
        ) {
            s.setLong(1, pizza.pid());
            s.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Pizza resultToPizza(ResultSet rs) throws SQLException {
        return new Pizza(
            rs.getLong("pazon"),
            rs.getString("pnev"),
            rs.getInt("par")
        );
    }
}

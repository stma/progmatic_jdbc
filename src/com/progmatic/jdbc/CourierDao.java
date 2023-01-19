package com.progmatic.jdbc;

import com.progmatic.jdbc.model.Courier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CourierDao implements Dao<Courier> {
    DBEngine engine;

    public CourierDao(DBEngine engine) {
        this.engine = engine;
    }


    @Override
    public Courier get(long id) {
        try (
            PreparedStatement s = engine.getConnection().prepareStatement("SELECT * FROM futar WHERE fazon = ?;");
        ) {
            s.setLong(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return resultToCourier(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Courier> getAll() {
        List<Courier> all = new LinkedList<>();

        try (
                Statement s = engine.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM futar;");
        ) {
            while (rs.next()) {
                all.add(CourierDao.resultToCourier(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    @Override
    public void save(Courier courier) {

    }

    @Override
    public void update(Courier courier, String[] params) {

    }

    @Override
    public void delete(Courier courier) {

    }

    private static Courier resultToCourier(ResultSet rs) throws SQLException {
        return new Courier(
                rs.getLong("fazon"),
                rs.getString("fnev"),
                rs.getString("ftel")
        );
    }
}

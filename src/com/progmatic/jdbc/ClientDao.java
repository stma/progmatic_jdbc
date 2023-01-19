package com.progmatic.jdbc;

import com.progmatic.jdbc.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ClientDao implements Dao<Client> {

    DBEngine engine;

    public ClientDao(DBEngine engine) {
        this.engine = engine;
    }

    @Override
    public Client get(long id) {
        try (
            PreparedStatement s = engine.getConnection().prepareStatement("SELECT * FROM vevo WHERE vazon = ?;");
        ) {
            s.setLong(1, id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                return resultToClient(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Client> getAll() {
        List<Client> all = new LinkedList<>();

        try (
                Statement s = engine.getConnection().createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM vevo;");
        ) {
            while (rs.next()) {
                all.add(ClientDao.resultToClient(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    private static Client resultToClient(ResultSet rs) throws SQLException {
        return new Client(
            rs.getLong("vazon"),
            rs.getString("vnev"),
            rs.getString("vcim")
        );
    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void update(Client client, String[] params) {

    }

    @Override
    public void delete(Client client) {

    }
}

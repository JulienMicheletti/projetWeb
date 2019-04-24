package dao;

import entite.Utilisateur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class DAO {

    protected Connection cnx;
    protected String table;

    public ResultSet setQuery(String query) throws SQLException {
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        return rs;
    }

    public void insertQuery(String query) throws SQLException {
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate(query);
    }

    public void del(int id) throws SQLException {
        Statement stmt = cnx.createStatement();
        stmt.executeUpdate("DELETE FROM `"+table+"` WHERE `"+ table +"`.`id` = "+id);
    }

    public int lastID() throws SQLException {
        ResultSet rs = setQuery("SELECT last_insert_id()");
        rs.next();
        return rs.getInt(1);
    }
}

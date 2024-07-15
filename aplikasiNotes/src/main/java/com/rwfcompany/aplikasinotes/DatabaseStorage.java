/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rwfcompany.aplikasinotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rizky
 */
public class DatabaseStorage implements DataStorage {

    private Connection connection;

    public DatabaseStorage(String databasePath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
            createDataTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData(String data) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO data (value) VALUES( ?)")) {
            statement.setString(1, data);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readData() {
        StringBuilder sb = new StringBuilder();
        try (
                Statement statement = connection.createStatement(); 
                 ResultSet resultSet = statement.executeQuery("SELECT value FROM data ORDER BY created_at DESC")) {

            while (resultSet.next()) {
                sb.append(resultSet.getString("value")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    @Override
    public void deleteData(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM data WHERE ROWID = (SELECT ROWID FROM data ORDER BY ROWID DESC LIMIT 1)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDataTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS data ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "value TEXT, "
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        }
    }

}

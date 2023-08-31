package net.zeroone.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import net.zeroone.Model.Painting;

public class PaintingDAO {

    Connection conn;
    public PaintingDAO() {
        
        try {
            conn = DriverManager.getConnection("url", "username", "password");

        } catch (SQLException e) {            
            System.out.println("failed to set up db connection!");
            System.exit(0);
        }
    }

    public int savePaintingAndReturn() {
        return 0;
    }

    public List<Painting> getAllPaintings() {

        return null;
    }

}

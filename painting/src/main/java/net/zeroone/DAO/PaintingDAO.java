package net.zeroone.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import net.zeroone.Main;
import net.zeroone.Model.Painting;

public class PaintingDAO {

    Connection conn;

    public PaintingDAO() {        
        try {
            conn = DriverManager.getConnection("jdbc:h2:./h2/db", "sa", "sa");
            PreparedStatement ps1 = conn.prepareStatement("drop table if exists painting;");
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement("create table painting (id int, title varchar(255), author varchar(255), year_made int, primary key (id));");
            ps2.executeUpdate();

        } catch (SQLException e) {            
            System.out.println("failed to set up db connection!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * TODO: refactor to using auto-generated keys
     * 
     * @param painting
     * @return
     */
    public int savePaintingReturnId(Painting painting) {
        Painting paintingExists = getPaintingById(painting.getId());
        if (paintingExists == null) {
            try {
                PreparedStatement ps2 = conn.prepareStatement("insert into painting (id, title, author, year_made) values (?, ?, ?, ?);");
                ps2.setInt(1, painting.getId());
                ps2.setString(2, painting.getAuthor());
                ps2.setString(3, painting.getTitle());
                ps2.setInt(4, painting.getYear());
                ps2.executeUpdate();
                Main.getLogger().info("save a new painting: " + painting);        
            } catch (SQLException e) {            
                e.printStackTrace();
            }   
        } else {
            Main.getLogger().info("duplicate primay-key");
        }
        return painting.getId();
    }

    public List<Painting> getAllPaintings() {
        List<Painting> paintings = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("select * from painting;");            
            ResultSet rs = ps.executeQuery();
            /**
             * if we don't have a value in the rs, return null.
             */
            while (rs.next()) {
                paintings.add(new Painting(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year_made")));
            } 
        } catch (SQLException e) {              
            e.printStackTrace();
        }        
        return paintings;
    }

    public Painting getPaintingById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from painting where id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            /**
             * if we don't have a value in the rs, return null.
             */
            if (rs.next()) {
                return (new Painting(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getInt("year_made")));
            } else {
                return null;
            }
        } catch (SQLException e) {  
            // Main.getLogger().error("user managed to get a sql exception... check this out", e);                  
            // Main.getLogger().error("The specified painting does not exist in the database!!!", e);                  
            e.printStackTrace();
        }        
        return null;
    }

}

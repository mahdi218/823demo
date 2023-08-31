package net.zeroone.Service;

import java.util.ArrayList;
import java.util.List;

import net.zeroone.DAO.PaintingDAO;
import net.zeroone.Model.Painting;

public class PaintingService {
    
    PaintingDAO paintingDAO;

    public PaintingService() {
        paintingDAO = new PaintingDAO();
    }

    /**
     * first, try persisting the painting.
     * then, using the new painting's id,
     * return the full painting with its database id.
     * 
     * @return
     */

    public Painting savePaintingAndReturn() {
        return null;
    }

    public List<Painting> getAllPaintings() {
        List<Painting> paintings = new ArrayList<>();
        
        return paintings;
    }
}

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
     * @param painting
     * 
     * @return
     */

    public Painting savePaintingAndReturn(Painting painting) {
        int id = paintingDAO.savePaintingReturnId(painting);
        Painting persistedPainting = paintingDAO.getPaintingById(id);
        return persistedPainting;
    }

    public List<Painting> getAllPaintings() {
        return paintingDAO.getAllPaintings();
    }

    public Painting getPaintingById(int id) {
        return paintingDAO.getPaintingById(id);
    }
}

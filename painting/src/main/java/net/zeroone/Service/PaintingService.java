package net.zeroone.Service;

// import static net.zeroone.Main.myLogger;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import net.zeroone.Main;
import net.zeroone.DAO.PaintingDAO;
import net.zeroone.Model.Painting;

public class PaintingService {
    
    PaintingDAO paintingDAO;
    Logger log;

    public PaintingService() {
        paintingDAO = new PaintingDAO();
        log = Main.getLogger();
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
        // log.info("save a new painting: " + painting);
        return persistedPainting;
    }

    public List<Painting> getAllPaintings() {
        log.info("getting all paintings: ");
        return paintingDAO.getAllPaintings();
    }

    public Painting getPaintingById(int id) {
        Painting painting = paintingDAO.getPaintingById(id);
        if (painting == null) {
            log.error("The specified painting does not exist in the database!!!");
        } 
        return painting;       
    }
}

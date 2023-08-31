package net.zeroone.Controller;


import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;
import net.zeroone.Model.Painting;
import net.zeroone.Service.PaintingService;

/**
 * As a user, I should be able to persist a painting by using a POST request to localhost:7070/painting, with the painting JSON in the body
 * As a user, I should be able to get all paintings by using a GET request to localhost:7070/painting, which will return a JSON of all paintings
 * As a user, I should able to get a single painting by its id using a GET request to localhost:7070/painting/{id}, 
 * which will return a JSON of a single painting
 * 
 * painting: int id, string title, string author, int year
 * create table painting(id int, title varchar(255), author varchar(255), year int);
 * 
 *  
 */

public class PaintingController {

    static PaintingService paintingService = new PaintingService();

    public PaintingController() {
        paintingService = new PaintingService();
    }

    /**
     * REST
     * Representational State Transfer
     * - we are transferirng data that is a representation of some 'entity' in our application (cars, movies, paintings,jusers, etc)
     * - we follow a certain set of guidelines to properly leverage HTTP requests/responses
     *      - self-descriptive URI's
     *      - URI is a pattern that identifies a particular resource
     *      - a restFul URI contains only nouns, not verbs
     *      - we define what action we'd like to perform on the resouce using the HTTP verb
     *      - we properly inform the sender of the request of the status of the request using the right response
     * 
     * - statelessness
     *      - because we don't store any data in-memory past the lifetime of the request/response, a RESTful api is infinitely scalable
     *          - we can still use a database! it's external to our api. our api's job is just to manage requests/responses.
     *          - let's say that every request added items to our arraylist. (eg we're keeping track of security tokens for every active user.)
     *          - this is fine for a small amount of users... scale up to several million, your api is now pretty slow, probably, so we don't do this.
     * 
     * REST is neither the only common practice for api design (SOAP... good luck), nor is it mandatory - oftentimes, you just need to break
     * convention, do the wrong thing, and, if you'd like to sound like you know what you're doing, call it an "anti-pattern"
     * @return     
     */
    public Javalin getAPI() {
        Javalin app = Javalin.create();
        app.post("/painting", PaintingController::postHandler);
        app.get("/painting", PaintingController::getAllHandler);
        app.get("/painting/{id}", PaintingController::getSingleHandler);
        return app;
    }

    /**
     * retrieve the request body json as a painting object,
     * ask the service to persist and return the painting object,
     * and respond with that as json
     * @param ctx
     */
    public static void postHandler(Context ctx) {
        String requestJson = ctx.body();
        ObjectMapper om = new ObjectMapper();
        Painting painting;
        try {
            painting = om.readValue(requestJson, Painting.class);
            Painting persistedPainting = paintingService.savePaintingAndReturn(painting);
            ctx.json(persistedPainting);

        } catch (JsonProcessingException e) {
            ctx.status(400);
            e.printStackTrace();
        }
    }

    public static void getAllHandler(Context ctx) {
        List<Painting> allPaintings = paintingService.getAllPaintings();
        ctx.json(allPaintings);
    }

    public static void getSingleHandler(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Painting painting = paintingService.getPaintingById(id);
        ctx.json(painting);
    }
}

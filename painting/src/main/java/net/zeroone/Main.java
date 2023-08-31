package net.zeroone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;
import net.zeroone.Controller.PaintingController;

public class Main {

    public static final Logger myLogger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        PaintingController paintingController = new PaintingController();
        Javalin app = paintingController.getAPI();
        app.start(7070);       
    }
    public static Logger getLogger() {
        return myLogger;
    }
}
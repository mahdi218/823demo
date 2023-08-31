package net.zeroone;

import io.javalin.Javalin;
import net.zeroone.Controller.PaintingController;

public class Main {
    public static void main(String[] args) {

        PaintingController paintingController = new PaintingController();
        Javalin app = paintingController.getAPI();
        app.start(7070);

       
    }
}
package Web;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Utils.BDUtils;
import Web.Controllers.HomeController;
import Web.Controllers.LoginController;
//import spark.Spark;
//import spark.debug.DebugScreen;
//import spark.template.handlebars.HandlebarsTemplateEngine;


public class Router {
    public static void configure() {
//        HandlebarsTemplateEngine engineTemplate = new HandlebarsTemplateEngine();
//        HomeController homeController = new HomeController();
//        LoginController loginController = new LoginController();
//
//        EntityManager em = BDUtils.getEntityManager();
//        EntityTransaction et = em.getTransaction();
//
//        DebugScreen.enableDebugScreen();
//
//        Spark.staticFiles.location("public/css"); // --> carpeta en la que se encuentran los .css dentro de la carpeta resouces
//
//        Spark.get("/home", homeController::home, engineTemplate);
//        Spark.redirect.get("/", "/home");
    }
}

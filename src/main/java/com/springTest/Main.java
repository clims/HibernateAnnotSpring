package com.springTest;

import com.springTest.config.SpringConfig;
import com.springTest.dao.CosmonautDAO;
import com.springTest.dao.RocketDAO;
import com.springTest.dao.SatelliteDAO;
import com.springTest.model.Cosmonaut;
import com.springTest.model.Rocket;
import com.springTest.model.Role;
import com.springTest.model.Satellite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {
        logger.info("\n\n\n\n\n\n\t\t\t\t\t##############New Launch#################\n\n\n\n\n\n");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        RocketDAO rocketDAO = ctx.getBean(RocketDAO.class);
        SatelliteDAO satelliteDAO = ctx.getBean(SatelliteDAO.class);
        CosmonautDAO cosmonautDAO = ctx.getBean(CosmonautDAO.class);
        Rocket rocket = new Rocket("Apollo", 5498d, 23_454_678d, 24_351d);
        Satellite satellite1 = new Satellite("Hubble", "space observation", 3_435d);
        Satellite satellite2 = new Satellite("Mamba", "navigation", 2548d);
        rocket.addSatellite(satellite1);
        rocket.addSatellite(satellite2);
        Cosmonaut cosmonaut1 = new Cosmonaut("Gagarin", 34);
        cosmonaut1.setRole(Role.PILOT);
        Cosmonaut cosmonaut2 = new Cosmonaut("Armstrong", 30);
        rocket.addCosmonaut(cosmonaut1);
        rocket.addCosmonaut(cosmonaut2);
        rocketDAO.addRocket(rocket);
        System.out.println("Data in db");
        printRockets(rocketDAO.getAllRockets());
        satellite1 = satelliteDAO.getSatellitesByName("Hubble").get(0);
        satelliteDAO.removeSatellite(satellite1);
        System.out.println("Data after delete satellite");
        printRockets(rocketDAO.getAllRockets());
        rocket = rocketDAO.getRocketByName("Apollo").get(0);
        rocket.addSatellite(satellite1);
        satelliteDAO.addSatellite(satellite1);
        System.out.println("Data after add satellite");
        printRockets(rocketDAO.getAllRockets());
        cosmonaut1 = cosmonautDAO.getCosmonautsByName("Gagarin").get(0);
        System.out.println("Founded cosmonaut: " + cosmonaut1);
        cosmonautDAO.removeCosmonaut(cosmonaut1);
        System.out.println("After deleting  cosmonaut");
        printRockets(rocketDAO.getAllRockets());
        rocket = rocketDAO.getRocketById(rocket.getId());
        rocket.addCosmonaut(cosmonaut1);
        cosmonautDAO.addCosmonaut(cosmonaut1);
        System.out.println("After add cosmonaut");
        printRockets(rocketDAO.getAllRockets());
        System.out.println("Number of satellites in db: " + satelliteDAO.getNumberOfSatellites());
        System.out.println("Oldest cosmonaut: " + cosmonautDAO.getOldestCosmonaut());
        rocketDAO.deleteRocket(rocket);
        System.out.println("Data in db after deleting rocket:");
        printRockets(rocketDAO.getAllRockets());
        printCosmonauts(cosmonautDAO.getAllCosmonauts());
        printSatellites(satelliteDAO.getAllSatellites());

        ((AnnotationConfigApplicationContext) ctx).close();
    }
    public static void printRockets(List<Rocket> rockets) {
        for (Rocket r : rockets) {
            System.out.println(r);
        }
    }
    public static void printCosmonauts(List<Cosmonaut> cosmonauts) {
        for (Cosmonaut c : cosmonauts) {
            System.out.println(c);
        }
    }
    public static void printSatellites(List<Satellite> satellites) {
        for (Satellite s : satellites) {
            System.out.println(s);
        }
    }
}
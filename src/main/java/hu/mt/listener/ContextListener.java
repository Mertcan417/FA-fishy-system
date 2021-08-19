package hu.mt.listener;
import hu.mt.model.*;
import hu.mt.persistence.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //Voor gebruik als beheerder:
            //voornaam: rik
            //achternaam: hik
            //wachtwoord: wachtwoord

            //Voor gebruik als eigenaar:
            //voornaam: Zoonlief
            //achternaam:Reenen
            //wachtwoord: wachtwoord
            //ZIE README FILE VOOR MEER INFO

            PersistenceManager.LoadAquariumManager();
            System.out.println("loading data...");
        } catch (Exception e) {
            System.out.println("Error with loading data...");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("closing application...");

        try {
            PersistenceManager.saveClients();
        } catch (Exception e) {
            System.out.println("Error with saving data... " + e.getMessage());
        }

    }
}


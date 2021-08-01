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
            Eigenaar eig1 = new Eigenaar("Zoonlief", "Reenen");
            Eigenaar eig2 = new Eigenaar("Dochterlief", "Reenen");
            Eigenaar eig3 = new Eigenaar("Neo", "Deo");
            Eigenaar eig4 = new Eigenaar("rik", "hik");

            Aquarium aq1 = new Aquarium("Aquarium0", 30, 60, 30, null, null);
            Aquarium aq2 = new Aquarium("Aquarium1", 30, 60, 30, null, null);

            Ornament om = new Ornament("octo", "blauw, van glas en is zeer sterk!", "blauw", true);

            Bewoner bn = new Bewoner("visje", "oranje-wit-zwart", 1, true, "nemo");
            Bewoner bwn = new Bewoner("Piranhia", "grijs-geel", 1, true, "groot");

            Toebehoren tbh = new Toebehoren("Spongebob", 12028, "Heel groot en zacht, mooie gele kleur en absorbeert water.");

            aq1.voegBewonerToe(bn);
            aq1.voegOrnamentToe(om);


            eig1.voegAquariumToe(aq1);
            eig1.voegToebehorenToe(tbh);

            eig2.voegAquariumToe(aq1);
            eig2.voegAquariumToe(aq2);

            AquariumManager.voegEigenaarToe(eig1);
            AquariumManager.voegEigenaarToe(eig2);
            AquariumManager.voegEigenaarToe(eig3);
            AquariumManager.voegToebehorenToe(tbh);

            AquariumManager.voegBewonerToe(bwn);

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


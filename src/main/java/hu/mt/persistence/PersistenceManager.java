package hu.mt.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import hu.mt.model.AquariumManager;
import hu.mt.model.Bewoner;
import hu.mt.model.Eigenaar;
import hu.mt.model.Toebehoren;

import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    //azure configuratie van gegevens
    private static String ENDPOINT = "https://backendpracticum.blob.core.windows.net/";
    private static String SAS_TOKEN = "?sv=2020-08-04&ss=bfqt&srt=sco&sp=rwdlacuptfx&se=2021-09-22T08:47:59Z&st=2021-07-26T00:47:59Z&spr=https,http&sig=86qtepStUz4p2N%2FlQLuovIF4vLMqmd61iDy1CmesOAA%3D";
    private static String CONTAINER = "usercontainer";

    //nieuwe container object
    private static BlobContainerClient blobContainerClient = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SAS_TOKEN)
            .containerName(CONTAINER)
            .buildClient();

    //laden van klantenlijst
    public static void LoadAquariumManager() throws IOException, ClassNotFoundException {
        System.out.println("Loading AquariumManager...");

        if (blobContainerClient.exists()) {
            BlobClient blobClient = blobContainerClient.getBlobClient("info");

            if (blobClient.exists()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blobClient.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                ArrayList<Eigenaar> eigenaren = (ArrayList<Eigenaar>) ois.readObject();
                ArrayList<Bewoner> bewoners = (ArrayList<Bewoner>) ois.readObject();
                ArrayList<Toebehoren> toebehoren = (ArrayList<Toebehoren>) ois.readObject();

                AquariumManager.alleEigenaren = eigenaren;
                AquariumManager.alleBewoners = bewoners;
                AquariumManager.alleToebehoren = toebehoren;

                baos.close();
                bais.close();
                ois.close();
                System.out.println("Data loaded...");

            }
        } else throw new IllegalStateException("container not found...");

    }

    //opslaan van klantenlijst
    public static void saveClients() throws IOException {
        System.out.println("Saving AquariumManager...");

        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }

        BlobClient blobClient = blobContainerClient.getBlobClient("info");
        ArrayList<Eigenaar> OwnersToSave = AquariumManager.getEigenaren();
        ArrayList<Bewoner> CitizensToSave = AquariumManager.getBewoners();
        ArrayList<Toebehoren> ToebehorenToSave = AquariumManager.getToebehoren();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(OwnersToSave);
        oos.writeObject(CitizensToSave);
        oos.writeObject(ToebehorenToSave);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blobClient.upload(bais, bytez.length, true);

        baos.close();
        oos.close();
        bais.close();
        System.out.println("AquariumManager saved");

    }
}

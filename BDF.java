import java.io.*;

import java.util.ArrayList;


public class BDF {

    private ArrayList<String> contenu;
    private String nomFichier;
    private int taille;

    public BDF(String nomBDF){
        nomFichier = nomBDF;
        contenu = new ArrayList<String>();
        System.out.println(contenu);
        BufferedReader br;
        String line;
        line = "";
        try {
            br = new BufferedReader(new FileReader(nomBDF));
            while (line != null) {
                line = br.readLine();
                if (line != null && line != "")
                    contenu.add(line);
            }
            br.close();
        }
        catch(IOException e) {
            System.out.println("erreur de lecture base de faits");
        }
        System.out.println(contenu);
        taille = contenu.size();
        System.out.println(taille);
    }

    public void ajoutBDF(String fait){
        contenu.add(fait);
        taille++;
    }

    public void MAJfichier(){
        FileWriter fw ;
        try{
            fw = new FileWriter(nomFichier);
            for (int i = 0; i < taille; i++)
                fw.write(contenu.get(i)+"\n");
            fw.close();
        }
        catch(IOException  e) {
            System.out.println("erreur d'écriture");
        }
    }

    @Override
    public String toString() {
        return "BDF{" +
                "contenu=" + contenu +
                ", nomFichier='" + nomFichier + '\'' +
                ", taille=" + taille +
                '}';
    }

    public ArrayList<String> getContenu() {
        return contenu;
    }

    public int getTaille() {
        return taille;
    }
}


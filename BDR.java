import java.util.ArrayList;

public class BDR {
    private ArrayList<Regle> contenu ;
    private int taille;
    private String nomFichier;

    public BDR (String nomBDR){
        contenu= new ArrayList<Regle>();
        nomFichier=nomBDR;
        StreamDeRegles h = new StreamDeRegles(nomBDR);
        taille=0;
        int cptwhile=0;
        while (h.getRegleSuivante() != null){
            Regle rtemp= new Regle(h.getRegle().getSchema());
            rtemp.setValeurs(h.getRegle().getValeurs());
            contenu.add(rtemp);
            taille++;
            cptwhile++;
        }
    }

    @Override
    public String toString() {
        return "BDR{" +
                "contenu=" + contenu +
                ", taille=" + taille +
                ", nomFichier='" + nomFichier + '\'' +
                '}';
    }

    public ArrayList<Regle> getContenu() {
        return contenu;
    }

    public int getTaille() {
        return taille;
    }
}


import java.io.BufferedReader;
import java.io.FileReader;

public class StreamDeRegles {
    private FileReader descripteur;
    private BufferedReader buffer;
    private Regle regle; //règle courante

    public StreamDeRegles(String s){
        try {
            descripteur = new FileReader (s);
            buffer = new BufferedReader(descripteur);
            System.out.println("L'ouverture s'est bien passée!");
        }
        catch (Exception e){
            System.out.println("Problème d'ouverture!");
        }
        String ss = null;
        try {
            ss = buffer.readLine();
        }
        catch (Exception e){
            System.out.println("Problème de lecture!");
        }
        System.out.println(ss);
        String [] tab1;
        System.out.println();
        tab1 = ss.split(";");
        for (int i = 0; i < tab1.length; i++)
            System.out.println(tab1[i]);
        regle = new Regle(tab1);
    }

    public Regle getRegleSuivante(){
        String s = null;
        try{
            s = buffer.readLine();
        }
        catch (Exception e){
            System.out.println("Problème de lecture!");
        }
        if (s == null){
            try {
                buffer.close();
            }
            catch (Exception e){
                System.out.println("Problème fermeture BDR!");
            }
            return null;
        }
        else {
            regle.setValeurs(s.split(";"));
            System.out.println("regle "+regle);
            return regle;
        }
    }

    public Regle getRegle() {
        return regle;
    }

    public String toString(){
        return regle.toString();
    }

}

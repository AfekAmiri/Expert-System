public class Regle {
    private String[] schema;
    private String[] valeurs;

    public Regle(String[] schema){
        this.schema = schema;
        int m = schema.length;
        this.valeurs = new String[m];
    }

    public void setValeurs(String[] valeurs) {

        this.valeurs = valeurs;

    }
    /* public void setValeurs(String[] valeurs) {
         if (valeurs.length == schema.length)
             this.valeurs = valeurs;
         else{
             System.out.println("Valeurs non compatibles!");
              this.valeurs = new String[schema.length];
         }
     }
     */
    public String[] getSchema() {
        return schema;
    }

    public String[] getValeurs() {
        return valeurs;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < schema.length; i++) {
            s = s + schema[i] + " : " + valeurs[i] + "\n";
        }
        return s;
    }
}



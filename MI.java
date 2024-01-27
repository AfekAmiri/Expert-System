public class MI {
    private BDF BFait;
    private BDR Bregle;

    public MI(String BF, String BR) {
        this.BFait = new BDF(BF);
        this.Bregle = new BDR(BR);
    }

    public boolean condDansBDF(String condition) {
        int i;
        for (i = 0; i < BFait.getTaille(); i++) {
            String cnd = BFait.getContenu().get(i);
            if (condition!=null && condition.equals(cnd))
                return true;
        }
        return false;
    }
    public boolean regleDeclenchee(Regle R){
        int i;
        for (i=0;i<R.getValeurs().length-1;i++){
            if (!R.getValeurs()[i].equals("")){
                if (!condDansBDF(R.getValeurs()[i])){
                    return false;
                }
            }
        }
        return true;

    }
    public void chainageAvant(BDF BF, BDR BR){
        boolean dejaDeclenchee[]=new boolean[BR.getTaille()];
        int i;
        for (i=0;i<BR.getTaille();i++){
            dejaDeclenchee[i]=false;
        }

        boolean saturation=false;
        Regle regleCourante;
        String conclusion;
        while(!saturation){
            int cpt=0;
            for (i=0;i<Bregle.getTaille();i++){
                if (!dejaDeclenchee[i]){
                    regleCourante=BR.getContenu().get(i);
                    if (regleDeclenchee(regleCourante)){
                        dejaDeclenchee[i]=true;
                        cpt++;
                        conclusion=regleCourante.getValeurs()[regleCourante.getValeurs().length-1];
                        if (!condDansBDF(conclusion)){
                            BFait.ajoutBDF(conclusion);
                        }
                    }
                }
            }
            if (cpt==0)
                saturation=true;
        }
        BFait.MAJfichier();
    }
    public boolean verifRegleArr(Regle R){
        int i;
        for (i=0;i<R.getValeurs().length-1;i++){
            if(!R.getValeurs()[i].equals("")){
                if(!condDansBDF(R.getValeurs()[i])){
                    if(!chainageArriere(R.getValeurs()[i]))
                        return  false;
                }
            }
        }
        return true;
    }

    public boolean chainageArriere(String but){
        if (condDansBDF(but))
            return true;
        int i;
        String conclusionCourante;
        Regle courante;
        for (i=0;i<Bregle.getTaille();i++){
            courante=Bregle.getContenu().get(i);
            conclusionCourante=courante.getValeurs()[Bregle.getContenu().get(i).getValeurs().length-1];
            if (conclusionCourante.equals(but)){
                if(verifRegleArr(courante))
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MI{" +
                "BFait=" + BFait +
                ", Bregle=" + Bregle +
                '}';
    }
}

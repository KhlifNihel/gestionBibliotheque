public class Localisation {
    public String etage;
    public String rayon;
    public String etagaire;

    public Localisation(){}

    public Localisation(String e,String r,String Eta){
        this.etage=e;
        this.rayon=r;
        this.etagaire=Eta;
    }

    public String toString(){
        return "Etage : "+this.etage + "   ,Etagaire : "+this.etagaire+"    ,Rayon :"+this.rayon;
    }
}

import java.util.ArrayList;
public class Adherent extends Personne{
    public String password;
    public ArrayList<Livre> LivresEmprunte = new ArrayList<Livre>();

    public Adherent(){}
    public Adherent(String nom,String prenom,String cin,String p){
        super(nom,prenom,cin);
        password = p;
    }

    public void chargerEmprunts(){
        ArrayList<Emprunt> emprunts = DB.getEmprunt(getCin());
        LivresEmprunte = new ArrayList<Livre>();
        for(int i=0;i<emprunts.size();i++){
            Livre livre = Bib.trouverLivre(emprunts.get(i).identifiant);
            LivresEmprunte.add(livre);
        }
    }

    public void Saisie(){
       Adherent A=new Adherent();
        System.out.println("votre Nom : ");
        super.nom=scanner.next();
        scanner.nextLine();
        System.out.println("votre Prenom : ");
        super.prenom=scanner.next();
        scanner.nextLine();
    }

    public void emprunter(String titre,Bib bib){

        if(bib.Livres.size()==0)
            System.out.println("-_-  ce livre n'est pas disponible pour le moment   -_-");
        else {
            for (int i =0; i < bib.Livres.size(); i++) {

                if (bib.Livres.get(i).titre.equals(titre)) {
                    if (bib.Livres.get(i).GetNbExemplaire() == 0) {
                        System.out.println("-_-  ce livre n'est pas disponible pour le moment   -_-");
                    } else {
                        LivresEmprunte.add(bib.Livres.get(i));
                        bib.Livres.get(i).decrementerNbExemplaire(1);
                        DB.UpdateLivreBD(bib.Livres.get(i));
                        DB.ajouterEmpruntBD(bib.Livres.get(i),this);
                        System.out.println("Merci de prendre soin du livre :)  ");
                        break;
                    }
                }else
                    System.out.println("-_-  ce livre n'est pas disponible pour le moment   -_-");
            }
        }
    }

    public void retournerLivre(Bib bib, int index){
        LivresEmprunte.get(index).incrementerNbExemplaire(1);
        DB.retournerLivreDB(getCin(),LivresEmprunte.get(index).GetIdentifier(),LivresEmprunte.get(index).GetNbExemplaire());
        DB.DeleteEmpruntsBD(LivresEmprunte.get(index),this);
    }

    public String whoAmI(){
        return super.toString();
    }

    public String toString() {
        if( LivresEmprunte.size()==0)
            System.out.println("Vous n'avez aucun livre ");
        String s ="";
        for (int i = 0; i < LivresEmprunte.size(); i++) {
            s += LivresEmprunte.get(i).toString() + "\n";
        }
        return s;
    }
}
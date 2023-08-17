import java.util.Scanner;
import java.util.ArrayList;
public class Livre {

    Scanner scanner = new Scanner(System.in);
    private int identifier;
    private int NbExemplaire;
    public String titre;
    public String Auteur;
    public String DateAchat;
    public Localisation localisation;
    public Livre(){}
    public Livre(int identifier,int expl,String titre,String Auteur,String DateAchat, Localisation localisation){
        this.SetIdentifier(identifier);
        this.SetNbExemplaire(expl);
        this.titre=titre;
        this.Auteur = Auteur;
        this.DateAchat=DateAchat;
        this.localisation=localisation;
    }

    public void SetIdentifier(int id)
    {
        this.identifier =id ;
    }

    public int GetIdentifier(){
        return  this.identifier;
    }

    public void SetNbExemplaire(int ex)
    {
         this.NbExemplaire =ex;
    }

    public int GetNbExemplaire(){
        return  this.NbExemplaire;
    }

    public void modifierLivre(){
        boolean loop=true;
        main_menu:while(loop){
        System.out.println("0 : modifier le titre \n"+
                           "1 : modifier l'auteur \n"+
                           "2 : modifier localisation \n" +
                           "3 : modifier date d'achat  \n"+
                           "4 : Incrementer nombre d'exemplaire \n"+
                           "5 : Decrementer nombre d'exemplaire \n"+
                            "6 : Quitter");
        int choix = scanner.nextInt();
        switch (choix){
            case 0 :
                System.out.println("Entrer le nouveau titre :");
                String newTitle = scanner.next();
                        this.titre=newTitle;
                        continue main_menu;

            case 1 :
                System.out.println("Entrer le nom de l'auteur :");
                String newname = scanner.next();
                        this.Auteur=newname;
                        continue main_menu;
            case 2 :
                System.out.println("==== nouvelle localisation ====:");
                System.out.println("==== Etage ====:");
                String etage=scanner.next();
                System.out.println("==== Etagaire ====:");
                String etagaire=scanner.next();
                System.out.println("==== Rang ====:");
                String rang=scanner.next();
                Localisation newLocation=new Localisation(etage,rang,etagaire);
                this.localisation=newLocation;
                continue main_menu;
            case 3 :
                System.out.println("Entrer la nouvelle localisation :");
                String newDate = scanner.next();
                this.DateAchat=newDate;
                continue main_menu;
            case 4 :
                System.out.println("Entrer l'identifiant de livre  :");
                int id = scanner.nextInt();
                System.out.println("Entrer la quantité à ajouter  :");
                int nb= scanner.nextInt();
                for(int i=0 ;i<Bib.Livres.size() ;i++){
                    if(Bib.Livres.get(i).identifier==id){
                        this.incrementerNbExemplaire(nb);
                    }
                }
                continue main_menu;
            case 5 :
                System.out.println("Entrer l'identifiant de livre  :");
                int idd = scanner.nextInt();
                System.out.println("Entrer la quantité à décrimenter  :");
                int nbb= scanner.nextInt();
                for(int i=0 ;i<Bib.Livres.size() ;i++){
                    if(Bib.Livres.get(i).identifier==idd){
                        this.decrementerNbExemplaire(nbb);
                    }
                }
                continue main_menu;
            case 6 :
                loop=false;

            }
        }

    }

    public  void incrementerNbExemplaire(int ex){
        this.NbExemplaire+=ex;
    }

    public  void decrementerNbExemplaire(int ex){
        if(this.NbExemplaire>0)
                this.NbExemplaire-=ex;
    }

    public String toString() {
        return "[Livre : ID= " + this.identifier +"  ,NbExemplaire = " + this.NbExemplaire + " ,Titre= " + this.titre + "  ,Auteur=" + this.Auteur + "  ,Date d'achat =  " + this.DateAchat + "  ,Localisation=" + this.localisation.toString() + "]";
    }

}






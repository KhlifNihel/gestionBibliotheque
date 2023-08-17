import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bib {
    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Livre> Livres = new ArrayList<>();
    public static ArrayList<Adherent> Adherents = new ArrayList<>();

    public Bib(){
        Livres = DB.getLivres();
        Adherents = DB.getAdherantsDB();
    }
    public void loginAdherant() {
        System.out.println("Saisir votre CIN :");
        String cin = scanner.next();
        scanner.nextLine();


        if(Adherents.size()==0){

            Adherent ad = new Adherent();
            ad.setCin(cin);
            ad.Saisie();
            System.out.println("Saisir votre mot de passe :");
            ad.password = scanner.next();
            scanner.nextLine();
            DB.ajouterAdherantBD(ad);
            Adherents.add(ad);
            boolean Quitter = true;
            while(Quitter){
                System.out.println(
                                "0 : Emprunter un livre \n" +
                                "1 : Retourner un livre \n"+
                                "2 : consulter la liste des livres que vous avez emprunté \n"+
                                "3 : Quitter");
                switch (scanner.nextInt()) {
                    case 0:
                        System.out.println("Enterez le titre du livre volu : ");
                        String tit=scanner.next();
                        scanner.nextLine();
                        ad.emprunter(tit,this);
                        ad.chargerEmprunts();
                        break;
                    case 1:
                        ad.chargerEmprunts();
                        System.out.println("Entrer le numero du livre à rendre");
                        int j;
                        for(j=0; j<ad.LivresEmprunte.size();j++){
                            System.out.println("%s - %s".formatted(j,ad.LivresEmprunte.get(j).titre));
                        }
                        System.out.println("\n");
                        int choix_livre = scanner.nextInt();
                        scanner.nextLine();
                        while(choix_livre >= j&&choix_livre<0){
                            choix_livre = scanner.nextInt();
                            scanner.nextLine();
                        }
                        ad.retournerLivre(this,choix_livre);
                        DB.retournerLivreDB(ad.getCin(),ad.LivresEmprunte.get(choix_livre).GetIdentifier(),Bib.trouverLivre(ad.LivresEmprunte.get(choix_livre).GetIdentifier()).GetIdentifier());
                        ad.chargerEmprunts();
                        break;
                    case 2:
                        System.out.println(ad.toString());
                        ad.chargerEmprunts();
                        break;
                    case 3:
                        Quitter=false;
                }
            }
        }
        else{
            boolean found = false;
            for (int i = 0; i < Adherents.size(); i++) {
                if (cin.equals(Adherents.get(i).getCin())) {
                    found = true;
                    System.out.println("Saisir votre mot de passe :");
                    String input = scanner.next();
                    scanner.nextLine();
                    if(Adherents.get(i).password.equals(input)){
                        Adherent ad = Adherents.get(i);
                        boolean Quitter = true;
                        while(Quitter){
                            System.out.println(
                                            "0 : Emprunter un livre \n" +
                                            "1 : Retourner un livre \n"+
                                            "2 : consulter la liste des livres que vous avez emprunté \n"+
                                            "3 : Quitter");
                            switch (scanner.nextInt()) {
                                case 0:
                                    System.out.println("Enterez le titre du livre volu : ");
                                    String tit=scanner.next();
                                    scanner.nextLine();
                                    ad.emprunter(tit,this);
                                    ad.chargerEmprunts();
                                    break;
                                case 1:
                                    ad.chargerEmprunts();
                                    System.out.println("Entrer le numero du livre à rendre");
                                    int j;
                                    for(j=0; j<ad.LivresEmprunte.size();j++){
                                        System.out.println("%s - %s".formatted(j,ad.LivresEmprunte.get(j).titre));
                                    }
                                    System.out.println("\n");
                                    int choix_livre = scanner.nextInt();
                                    scanner.nextLine();
                                    while(choix_livre >= j&&choix_livre<0){
                                        choix_livre = scanner.nextInt();
                                        scanner.nextLine();
                                    }
                                    ad.retournerLivre(this,choix_livre);
                                    DB.retournerLivreDB(ad.getCin(),ad.LivresEmprunte.get(choix_livre).GetIdentifier(),Bib.trouverLivre(ad.LivresEmprunte.get(choix_livre).GetIdentifier()).GetIdentifier());
                                    ad.chargerEmprunts();
                                    break;
                                case 2:
                                    ad.chargerEmprunts();
                                    System.out.println(ad.toString());
                                    break;
                                case 3:
                                    Quitter=false;
                            }
                        }

                    }
                }
            }

            if(!found){

                Adherent ad = new Adherent();
                ad.setCin(cin);
                ad.Saisie();
                System.out.println("Saisir votre mot de passe :");
                ad.password = scanner.next();
                scanner.nextLine();
                DB.ajouterAdherantBD(ad);
                Adherents.add(ad);
                boolean Quitter = true;
                while(Quitter){
                    System.out.println(
                                    "0 : Emprunter un livre \n" +
                                    "1 : Retourner un livre \n"+
                                    "2 : consulter la liste des livres que vous avez emprunté \n"+
                                    "3 : Quitter");
                    switch (scanner.nextInt()) {
                        case 0:
                            System.out.println("Enterez le titre du livre volu : ");
                            String tit=scanner.next();
                            scanner.nextLine();
                            ad.emprunter(tit,this);
                            ad.chargerEmprunts();
                            break;
                        case 1:
                            ad.chargerEmprunts();
                            System.out.println("Entrer le numero du livre à rendre");
                            int j;
                            for(j=0; j<ad.LivresEmprunte.size();j++){
                                System.out.println("%s - %s".formatted(j,ad.LivresEmprunte.get(j).titre));
                            }
                            System.out.println("\n");
                            int choix_livre = scanner.nextInt();
                            scanner.nextLine();

                            while((choix_livre >= j) && (choix_livre<0)){
                                choix_livre = scanner.nextInt();
                                scanner.nextLine();
                            }
                            ad.retournerLivre(this,choix_livre);
                            DB.retournerLivreDB(ad.getCin(),ad.LivresEmprunte.get(choix_livre).GetIdentifier(),Bib.trouverLivre(ad.LivresEmprunte.get(choix_livre).GetIdentifier()).GetIdentifier());
                            ad.chargerEmprunts();
                            break;
                        case 2:
                            System.out.println(ad.toString());
                            ad.chargerEmprunts();
                            break;
                        case 3:
                            Quitter=false;
                    }

                }
            }

        }

    }

    public void ajouterAdherant(String nom,String prenom,String cin,String p){
        Adherent ad = new Adherent();
        Adherents.add(ad);
        DB.ajouterAdherantBD(ad);
    }

    public static void ajouterLivre(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("donner L'identifiant du livre :");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("donner Le nombre d'exemplaire du livre :");
        int ex = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Titre du livre");
        String Title = scanner.nextLine();

        System.out.println("L'auteur du livre");
        String Auteur= scanner.nextLine();


        System.out.println("Date d'achat du livre");
        String Date= scanner.next();
        scanner.nextLine();

        System.out.println("Localisation du livre");
        Localisation Loc =new Localisation();
        System.out.println("Etagaire");
        Loc.etagaire= scanner.nextLine();
        System.out.println("Etage");
        Loc.etage= scanner.nextLine();
        System.out.println("rayon");
        Loc.rayon= scanner.nextLine();

        Livre l=new Livre(id,ex,Title,Auteur,Date,Loc);
        Livres.add(l);
        DB.ajouterLivreBD(l);

    }

    public String consulterAdherents(){
        for(Adherent a : Adherents){
            a.chargerEmprunts();

        }
        String s="";
        for(int i=0 ; i < Adherents.size() ; i++){
            s+=Adherents.get(i).whoAmI()+"\n";
            s+=Adherents.get(i).toString()+"\n";
        }
        return s;
    }

    public static void ajouterLivre(Livre l){
        Livres.add(l);
    }

    public void modifierLivre(){
        System.out.println("Entrer l'identifiant de livre à modifié :");
        int id= scanner.nextInt();
        for(int i =0; i<Livres.size() ; i++){
            if (Livres.get(i).GetIdentifier()==id){
                Livres.get(i).modifierLivre();
                DB.UpdateLivreBD(Livres.get(i));
            }
        }
    }

    public static Livre trouverLivre(int id){
        for(int i =0; i<Livres.size() ; i++){
            if (Livres.get(i).GetIdentifier()==id){
                return Livres.get(i);
            }
        }
        return null;
    }

    public void supprimerLivre(){
        System.out.println("Entrer l'identifiant de livre à supprimer :");
        int id = scanner.nextInt();
        boolean existe =true;
        for(int i =0; i<Livres.size() ; i++){
            if (Livres.get(i).GetIdentifier()==id){
                DB.DeleteLivreBD(Livres.get(i));
                Livres.remove(i);
                System.out.println("==== Livre supprimer ! ====");
                existe=false;
            }
        }
        if (existe==true){
            System.out.println("==== ce livre n'existe pas ! ====");
        }
    }

    public String toString(){
        String str = "";
        System.out.println("SIZE="+Livres.size());

        for (int i=0;i < Livres.size();i++){


            str+= Livres.get(i).toString() + "\n";

        }

        return str;
    }

}

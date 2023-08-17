import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void splash(){
        for(int i=0;i<50;i++)
        System.out.println();
    }

    public static void showCenteredText(String txt){
        System.out.println("                                                          "+txt);
    }

    public static void menu_employe(Bib Bibliotheque){
        Scanner scanner = new Scanner(System.in);
        boolean test=employe.sauthentifier();
        if(test) {
            boolean exite = true;
            main_menu:
            while (exite) {
                System.out.println(
                        "0 : Ajouter un livre \n" +
                        "1 : Supprimer un livre \n" +
                        "2 : Modifier un livre \n" +
                        "3 : Consulter les Livres  \n" +
                        "4 : Consulter les  Adherents  \n" +
                        "5 :  Quitter");

                int choix = scanner.nextInt();
                switch (choix) {
                    case 0:
                        Bibliotheque.ajouterLivre();
                        break;
                    case 1:
                        Bibliotheque.supprimerLivre();
                        break;
                    case 2:
                        Bibliotheque.modifierLivre();
                        break;
                    case 3:
                        System.out.println(Bibliotheque.toString());
                        break;
                    case 4:
                        System.out.println("Consultation des adherents:");
                        System.out.println(Bibliotheque.consulterAdherents());
                        break;
                    case 5:
                        System.out.println("vous avez quitte!!");
                        exite = false;
                }
            }
        }
    }

    public static void menu_etudiant(Bib Bibliotheque){
        Bibliotheque.loginAdherant();
    }

    public static void main(String[] args) {
        splash();
        DB db = new DB();
        Scanner scanner = new Scanner(System.in);
        Bib Bibliotheque = new Bib();
        employe E = new employe();
        showCenteredText("Bienvenu dans le Systeme de gestion de la Bibliotheque :");
        System.out.println();
        while(true) {
        System.out.println("Vous etes:\n" + "-::: 1  :Etudiant\n" + "-::: 2  :Gestionnaire");
            switch (scanner.nextInt()) {
                case 2:
                    menu_employe(Bibliotheque);
                    break;
                case 1:
                    menu_etudiant(Bibliotheque);
                    break;
                default:
                    System.out.println("Choix incorrecte Merci de verifier !");
            }
        }
    }
}
import java.util.Scanner;
public class employe extends Personne {

    private static String Identifiant="0000";
    Scanner scanner = new Scanner(System.in);

    public employe(String nom,String prenom,String cin,String poste){
        super( nom,prenom,cin);
    }

    public employe(){
    }

    public static boolean sauthentifier(){
        Scanner scanner = new Scanner(System.in);
        boolean enter = false;
        log_in:
        while(true){
        System.out.println("Entrer votre identifiant:");

        String Id=scanner.nextLine();
        if(Id.equals(Identifiant)) {
            enter = true;
            break ;
        }
        else
            System.out.println("Identifiant incorrect merci de réesayer !");
         continue log_in;
        }
        return enter;
    }

    public String toString(){
        return super.toString();
    }
}

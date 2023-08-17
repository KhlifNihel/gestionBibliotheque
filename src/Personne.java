import java.util.Scanner;
public class Personne {
    public String nom;
    public String prenom;
    private String cin;

    public Personne(){}
    public Personne(String nom,String prenom,String cin){
        this.nom=nom;
        this.prenom=prenom;
        this.setCin(cin);
    }
    Scanner scanner = new Scanner(System.in);
    public void Saisie(){
        System.out.println("votre nom :: :");
        String nom = scanner.nextLine();

        System.out.println("votre prenom :: :");
        String prenom = scanner.nextLine();

        System.out.println("votre numero de carte d'identité");
        String cin= scanner.nextLine();

        Personne p=new Personne(nom,prenom,cin);
    }

    public void setCin(String cin){
        this.cin=cin;
    }

    public String getCin(){return this.cin; }
    public String toString(){
        return
                "[Nom : "+ this.nom+" , "+ "Prenom :  "+this.prenom+" , "+ "Cin :  "+this.getCin()+" ]";
    }

}

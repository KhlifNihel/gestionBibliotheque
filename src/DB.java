import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.*;
import java.util.ArrayList;



public  class DB {
    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String driverName  ="jdbc:mysql://localhost:3306/base1";
    private static String username  ="root";
    private static String password  ="password";
    static Connection connection;



    public static ArrayList<Livre> getLivres(){
        ArrayList<Livre> livres = new ArrayList<Livre>();
        try{
            Connection con=DriverManager.getConnection(driverName,username,password);
            Class.forName(className);
            Statement stmt=con.createStatement();

           ResultSet result = stmt.executeQuery("SELECT * FROM Livre ;");
           while (result.next()){
               livres.add(new Livre(result.getInt(1),result.getInt(2),result.getString(3),result.getString(4),result.getString(5),new Localisation(result.getString(6),result.getString(7),result.getString(8))));

           }
            con.close();
       }
        catch(Exception e)
        { System.out.println(e);
        }
        return livres;
    }

    public static void retournerLivreDB(String cin,int identifiant,int nb_exemplaire_inc){
        ExecuteNonReader("DELETE FROM Emprunts WHERE (cin = '%s' AND id = %s) ;".formatted(cin,identifiant));
        ExecuteNonReader("UPDATE Livre SET NbExemplaire =  %s WHERE Id = %s ;".formatted(nb_exemplaire_inc,identifiant));

    }

    public static ArrayList<Emprunt> getEmprunt(String  cin){
        ArrayList<Emprunt> emprunts = new ArrayList<Emprunt>();
        try{
            Connection con=DriverManager.getConnection(driverName,username,password);
            Class.forName(className);
            Statement stmt=con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM Emprunts WHERE cin=%s ;".formatted(cin));
            while (result.next()){
                emprunts.add(new Emprunt(result.getString(1),result.getInt(2)));
            }
            con.close();
        }
        catch(Exception e)
        { System.out.println(e);
        }
        return emprunts;
    }

    public static ArrayList<Adherent> getAdherantsDB(){
        ArrayList<Adherent> adherents = new ArrayList<Adherent>();
        try{
            Connection con=DriverManager.getConnection(driverName,username,password);
            Class.forName(className);
            Statement stmt=con.createStatement();

            ResultSet result = stmt.executeQuery("SELECT * FROM Adherants ;");
            while (result.next()){
                adherents.add(new Adherent(result.getString(1),result.getString(2),result.getString(3),result.getString(4)));
            }
            con.close();
        }
        catch(Exception e)
        { System.out.println(e);
        }
        return adherents;
    }

    public static void ajouterEmpruntBD(Livre livre,Adherent ad){
        ExecuteNonReader("INSERT INTO Emprunts VALUES (%s,%s) ;".formatted(ad.getCin(),livre.GetIdentifier()));
    }

    public static void DeleteEmpruntsBD(Livre livre,Adherent ad){
        ExecuteNonReader("DELETE FROM Emprunts WHERE (id= %s AND cin=%s) ;".formatted(livre.GetIdentifier(),ad.getCin()));
    }

    public static void ajouterLivreBD(Livre livre){
        ExecuteNonReader("INSERT INTO Livre VALUES (%s,%s,'%s','%s','%s','%s','%s','%s') ;".formatted(livre.GetIdentifier(),livre.GetNbExemplaire(),livre.titre,livre.Auteur,livre.DateAchat,livre.localisation.etage,livre.localisation.etagaire,livre.localisation.rayon));
    }

    public static void UpdateLivreBD(Livre livre){
        ExecuteNonReader("DELETE FROM Livre WHERE Id = %s".formatted(livre.GetIdentifier()));
        ExecuteNonReader("INSERT INTO Livre VALUES (%s,%s,'%s','%s','%s','%s','%s','%s') ;".formatted(livre.GetIdentifier(),livre.GetNbExemplaire(),livre.titre,livre.Auteur,livre.DateAchat,livre.localisation.etage,livre.localisation.etagaire,livre.localisation.rayon));

    }

    public static void DeleteLivreBD(Livre livre){
        ExecuteNonReader("DELETE FROM Livre WHERE ID= %s ;".formatted(livre.GetIdentifier()));
    }

    public static void ajouterAdherantBD(Adherent adherent){
        ExecuteNonReader("INSERT INTO Adherants VALUES ('%s','%s','%s','%s');".formatted(adherent.nom,adherent.prenom,adherent.getCin(),adherent.password));
    }

   public static void ExecuteNonReader(String sql) {
       try {
           Class.forName(className);
           Connection con = DriverManager.getConnection(driverName, username, password);
           Statement stmt = con.createStatement();
           stmt.executeUpdate(sql);
           con.close();
       } catch (Exception e) {
           System.out.println(e);
       }
   }
}


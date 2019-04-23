package entite;

import dao.UtilisateurDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {

    private int id = -1;
    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;

    public Utilisateur(int id, String nom, String prenom, String pseudo, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public Utilisateur(int idUser){
        ResultSet rs;
        try {
            rs = UtilisateurDAO.getInstance().setQuery("select * from Utilisateurs where id = "+idUser);
            rs.next();
            id = rs.getInt(1);
            nom = rs.getString(2);
            prenom = rs.getString(3);
            pseudo = rs.getString(4);
            mdp = rs.getString(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Utilisateur(String nom, String prenom, String pseudo, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void save() throws SQLException {
        if(id < 0) {
            UtilisateurDAO.getInstance().insertQuery("INSERT INTO `utilisateur` (`nom`, `prenom`, `pseudo`, `mdp`) VALUES ('" + nom + "', '" + prenom + "', '" + pseudo + "', '" + mdp + "')");
            id = UtilisateurDAO.getInstance().lastID();
        } else
            UtilisateurDAO.getInstance().insertQuery("UPDATE `utilisateur` SET `Nom` = '"+ nom +"', `prenom` = '"+ prenom +"', `pseudo` = '"+ pseudo +"', `mdp` = '"+ mdp +"' WHERE `utilisateurs`.`id` = "+ id +";");
    }

    public void del() throws SQLException {
        UtilisateurDAO.getInstance().delUtilisateur(id);
    }
}

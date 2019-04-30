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
    private Panier panier = null;
    private int role = 1;

    public Utilisateur(int id, String nom, String prenom, String pseudo, String mdp, int role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.role = role;
    }

    public Utilisateur(int idUser) throws SQLException{
        ResultSet rs;
        rs = UtilisateurDAO.getInstance().setQuery("select * from utilisateur where id = "+idUser);
        rs.next();
        id = rs.getInt(1);
        nom = rs.getString(2);
        prenom = rs.getString(3);
        pseudo = rs.getString(4);
        mdp = rs.getString(5);
        role = rs.getInt(6);
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

    public void setRole(int role){ if (role == 0 || role == 1) this.role = role;}

    public int getRole(){return this.role;}

    public void save() throws SQLException {
        System.out.println("save");
        if(id < 0) {
            UtilisateurDAO.getInstance().insertQuery("INSERT INTO `utilisateur` (`nom`, `prenom`, `pseudo`, `mdp`, `role`) VALUES ('" + nom + "', '" + prenom + "', '" + pseudo + "', '" + mdp + "', '" + role +"');");
            id = UtilisateurDAO.getInstance().lastID();
        } else {
            UtilisateurDAO.getInstance().insertQuery("UPDATE `utilisateur` SET `Nom` = '" + nom + "', `prenom` = '" + prenom + "', `pseudo` = '" + pseudo + "', `mdp` = '" + mdp + "', `role` = '" + role + "' WHERE `utilisateur`.`id` = " + id + ";");
        }
    }

    public void del() throws SQLException {
        UtilisateurDAO.getInstance().del(id);
    }

    public Panier getPanier() throws SQLException {
        if(panier == null) {
            panier = new Panier(id);
        }
        return panier;
    }
}

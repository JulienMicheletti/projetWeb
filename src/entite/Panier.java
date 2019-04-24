package entite;

import dao.CommandeDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Panier {
    private ArrayList<Commande> commandes;
    private int userId;

    public Panier(int userId) throws SQLException {
        this.userId = userId;
        commandes = CommandeDAO.getInstance().getCommandesByUser(userId);
    }

    public int getNbCom() {
        return commandes.size();
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    public void addCommande(Commande c) throws SQLException {
        c.save(userId);
        commandes.add(c);
    }

    public void delCommande(int commandeId) throws SQLException {
        for (int i = 0; i < getNbCom(); i++) {
            if(commandes.get(i).getId() == commandeId) {
                commandes.remove(i);
                i = getNbCom();
            }
        }
        CommandeDAO.getInstance().del(commandeId);
    }
}

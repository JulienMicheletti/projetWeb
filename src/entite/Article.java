package entite;

public class Article {
    private int id = -1;
    private String nom;
    private int stock;
    private float prix;

    public Article(int id, String nom, int stock, float prix) {
        this.id = id;
        this.nom = nom;
        this.stock = stock;
        this.prix = prix;
    }

    public Article(String nom, int stock, float prix) {
        this.nom = nom;
        this.stock = stock;
        this.prix = prix;
    }
}

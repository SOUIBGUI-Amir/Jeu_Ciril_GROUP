import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Représente une carte composée d'une grille de caractères.
 * Chaque case peut être accessible ou non pour le personnage.
 */
public class Carte {

    protected char[][] grille; 
    private int largeur;
    private int hauteur;

    // --------------------------------------------------- Constructeur

    /**
     * Construit une carte en lisant un fichier texte.
     * @param cheminFichier le chemin vers le fichier de la carte
     * @throws IOException si une erreur de lecture du fichier survient
     */
    public Carte(String cheminFichier) throws IOException {
        List<String> lignes = Files.readAllLines(Paths.get(cheminFichier));

        hauteur = lignes.size();
        largeur = lignes.get(0).length();

        grille = new char[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                grille[y][x] = lignes.get(y).charAt(x);
            }
        }
    }

    // --------------------------------------------------- Méthodes 

    /**
     * Vérifie si une case est accessible (pas un mur).
     * @param x la coordonnée x (colonne)
     * @param y la coordonnée y (ligne)
     * @return true si la case est accessible, false sinon
     */
    public boolean estCaseAccessible(int x, int y) {
        return (x >= 0 && x < largeur && y >= 0 && y < hauteur && grille[y][x] != '#');
    }


    /**
     * Affiche la carte dans la console.
     */
    public void afficher() {
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                System.out.print(grille[y][x]);
            }
            System.out.println();
        }
    }

    // --------------------------------------------------- Getters

    public int getHauteur() {
        return hauteur; 
    }

    public int getLargeur() {
        return largeur;
    }
}

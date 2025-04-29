

public class Personnage {

    private int x;
    private int y;

    // --------------------------------------------------- Constructeur

    /**
     * Crée un personnage à la position de départ donnée.
     * @param xDepart coordonnée x initiale
     * @param yDepart coordonnée y initiale
     */
    public Personnage(int xDepart, int yDepart) {
        this.x = xDepart;
        this.y = yDepart;
    }

    // --------------------------------------------------- Méthodes 

    /**
     * Déplace le personnage dans une direction donnée si la case est accessible.
     * @param direction 'N' pour Nord, 'S' pour Sud, 'E' pour Est, 'O' pour Ouest
     * @param carte la carte sur laquelle le personnage se déplace
     */
    public void deplacer(char direction, Carte carte) {
        int dx = 0, dy = 0;
        switch (direction) {
            case 'N': dy = -1; break;
            case 'S': dy = 1; break;
            case 'E': dx = 1; break;
            case 'O': dx = -1; break;
        }

        int newX = x + dx;
        int newY = y + dy;

        if (carte.estCaseAccessible(newX, newY)) {
            carte.grille[y][x] = '●'; // Pour marquer le chemin parcouru
            x = newX;
            y = newY;
            carte.grille[y][x] = '◆'; // Pour marquer la nouvelle position du heros
            System.out.println("\n");

            try {
                Thread.sleep(1000); // Pause de 1 seconde 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            carte.afficher();
        } else {
            System.out.println("\n");
            System.out.println("Case (" + newX + "," + newY + ") inaccessible.");
        }
    }


    /**
     * Affiche la position actuelle du heros.
     */
    public void afficherPosition() {
        System.out.println("Votre heros se trouve en position (" + x + "," + y + ").");
    }

    // --------------------------------------------------- Getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

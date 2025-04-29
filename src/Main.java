import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("\n###################### Jeu du héros ##############################");
        System.out.println("\nVotre héros s'aventure dans un monde dangereux, frayant son passage dans les bois obscurs...\n");

        try {
            // Lecture du fichier de la carte
            System.out.print("Entrez le nom du fichier de la carte (sans extension) : ");
            String nomFichierCarte = "resources/"+ scanner.nextLine() + ".txt";
            Carte maCarte = new Carte(nomFichierCarte);

            // Lecture du fichier de déplacements
            System.out.print("Entrez le nom du fichier des déplacements (sans extension) : ");
            String nomFichierDeplacements = "resources/"+ scanner.nextLine() + ".txt";
            List<String> mouvements = Files.readAllLines(Paths.get(nomFichierDeplacements), StandardCharsets.UTF_8);

            // Extraction des informations de départ et de la séquence
            String[] depart = mouvements.get(0).split(",");
            int xDepart = Integer.parseInt(depart[0]);
            int yDepart = Integer.parseInt(depart[1]);
            String sequence = mouvements.get(1).trim();

            // Affichage de la carte et des informations initiales
            System.out.println("\nVoici la carte : ");
            maCarte.afficher();

            System.out.println("\nPosition de départ du héros : (" + xDepart + ", " + yDepart + ")");
            System.out.println("Séquence de déplacements : " + sequence);

            
            if (!maCarte.estCaseAccessible(xDepart, yDepart)) {
                throw new IllegalArgumentException("La case de départ (" + xDepart + ", " + yDepart + ") est inaccessible. Veuillez recommencer.");
            }

            
            Personnage heros = new Personnage(xDepart, yDepart);

            System.out.println("\n Chemin parcouru :\n");

            // Déplacement du héros selon la sequence
            for (char move : sequence.toCharArray()) {
                heros.deplacer(move, maCarte);
            }

            
            heros.afficherPosition();

        } catch (IOException e) {

            System.out.println("Oups ! Il semblerait que l'un de vos fichiers n'existe pas ou n'est pas lisible. Veuillez recommencer.");

        } catch (IllegalArgumentException e) {

            System.out.println("Erreur : " + e.getMessage());
        }



        // Décommenter pour exécuter les tests unitaire et fonctionnels 
        /*
        System.out.println("\n###################### TESTS ##############################");
        try {
            Test test = new Test();
            test.testUnitaireCarte();
            test.testUnitairePersonnage();
            test.testFonctionnelJeu();

        } catch (IOException e) {
            System.out.println("Erreur lors de l'exécution des tests : " + e.getMessage());
        }

         */


    }
}

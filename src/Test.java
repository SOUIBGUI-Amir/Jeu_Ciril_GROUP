import java.io.IOException;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.*;

public class Test {
   
        
        public void testUnitaireCarte() throws IOException {


            System.out.println("\n############## Tests Unitaires - Classe Carte ##################");

            // Test 1 : Erreur de lecture (fichier inexistant)
            System.out.println("\n############## Test n°1 : ##############");
            try {
                Carte carteInexistante = new Carte("fichierInxistant.txt");
                throw new AssertionError("IOException attendue mais non levée ");
            } catch (IOException e) {
                System.out.println("############## SUCCESS : IOException levée ##############");
            } 


            // Test 2 : Lecture correcte du fichier d'entrée
            System.out.println("\n############## Test n°2 : ##############");
            Carte maCarte = new Carte("resources/carte.txt");
            assert maCarte.getHauteur() == 20 : "Test échoué pour getHauteur(), attendu 20";
            assert maCarte.getLargeur() == 20 : "Test échoué pour getLargeur(), attendu 20";
            System.out.println("\nCarte lue : ");
            maCarte.afficher();
            System.out.println("\n############## SUCCESS ##############");


            // Test 3 : Verification de la méthode estCaseAccessible
            System.out.println("\n############## Test n°3 : ##############");
            assert maCarte.estCaseAccessible(0, 3) == true : "Test échoué pour estCaseAccessible(0, 3)";
            assert maCarte.estCaseAccessible(3, 0) == false : "Test échoué pour estCaseAccessible(3, 0)";
            assert maCarte.estCaseAccessible(21, 11) == false : "Test échoué pour estCaseAccessible(21, 11)";
            assert maCarte.estCaseAccessible(20, 31) == false : "Test échoué pour estCaseAccessible(20, 31)";
            System.out.println("\n############## SUCCESS ##############");
        }




        public void testUnitairePersonnage() throws IOException {

            System.out.println("\n############## Tests Unitaires - Classe Personnage ##################");

            Carte maCarte = new Carte("resources/carte.txt");
            int xDepart = 2;
            int yDepart = 4;


            Personnage heros = new Personnage(xDepart, yDepart);

            // Tests Déplacement personnage :
    
            // Mouvement Est
            heros.deplacer('E', maCarte);
            assert heros.getX() == xDepart + 1 && heros.getY() == yDepart : "Erreur déplacement Est";
            xDepart = heros.getX();

            // Mouvement Nord
            heros.deplacer('N', maCarte);
            assert heros.getX() == xDepart && heros.getY() == yDepart - 1 : "Erreur déplacement Nord";
            yDepart = heros.getY(); 

             // Mouvement Ouest
            heros.deplacer('O', maCarte);
            assert heros.getX() == xDepart - 1 && heros.getY() == yDepart : "Erreur déplacement Ouest";
            xDepart = heros.getX();

            // Mouvement Sud
            heros.deplacer('S', maCarte);
            assert heros.getX() == xDepart && heros.getY() == yDepart + 1 : "Erreur déplacement Sud";
            yDepart = heros.getY();


            // Vérifier que le personnage ne peut pas se déplacer si la case est inaccessible
            int ancienneX = heros.getX();
            int ancienneY = heros.getY();
            heros.deplacer('O', maCarte);

            // Vérifier que la position du personnage n'a pas changé
            assert heros.getX() == ancienneX : "Erreur : le personnage a changé de position en X malgré l'obstacle.";
            assert heros.getY() == ancienneY : "Erreur : le personnage a changé de position en Y malgré l'obstacle.";




            System.out.println("\n############## SUCCESS ##############");
        }






        public void testFonctionnelJeu() throws IOException {

            // Premier Test
            System.out.println("\n############## Test Fonctionnel n°1 : ##############");
            Carte maCarte1 = new Carte("resources/carte.txt");
            List<String> mouvementsTest1 = Files.readAllLines(Paths.get("resources/deplacementsTest1.txt"), StandardCharsets.UTF_8);
            String[] depart1 = mouvementsTest1.get(0).split(",");
            int xDepart1 = Integer.parseInt(depart1[0]);
            int yDepart1 = Integer.parseInt(depart1[1]);
            String sequence1 = mouvementsTest1.get(1).trim();

            if (!maCarte1.estCaseAccessible(xDepart1, yDepart1)) {
             throw new IllegalArgumentException("Erreur : La case de départ (" + xDepart1 + ", " + yDepart1 + ") est inacessible. Veuillez recommencer");
            }

            Personnage heros1 = new Personnage(xDepart1, yDepart1);
            for (char move : sequence1.toCharArray()) {
                heros1.deplacer(move, maCarte1);
            }
            assert heros1.getX() == 9 : "Test échoué pour x : Attendu 9 mais obtenu " + heros1.getX();
            assert heros1.getY() == 2 : "Test échoué pour y : Attendu 2 mais obtenu " + heros1.getY();
            System.out.println("\n############## SUCCESS ##############");

            



            // Deuxième Test
            System.out.println("\n############## Test Fonctionnel n°2 : ##############");
            Carte maCarte2 = new Carte("resources/carte.txt");
            List<String> mouvementsTest2 = Files.readAllLines(Paths.get("resources/deplacementsTest2.txt"), StandardCharsets.UTF_8);
            String[] depart2 = mouvementsTest2.get(0).split(",");
            int xDepart2 = Integer.parseInt(depart2[0]);
            int yDepart2 = Integer.parseInt(depart2[1]);
            String sequence2 = mouvementsTest2.get(1).trim();

            if (!maCarte2.estCaseAccessible(xDepart2, yDepart2)) {
             throw new IllegalArgumentException(" Erreur : La case de départ (" + xDepart2 + ", " + yDepart2 + ") est inacessible. Veuillez recommencer");
            }

            Personnage heros2 = new Personnage(xDepart2, yDepart2);
            for (char move : sequence2.toCharArray()) {
                heros2.deplacer(move, maCarte2);
            }
            assert heros2.getX() == 1 : "Test échoué pour x : Attendu 1 mais obtenu " + heros1.getX();
            assert heros2.getY() == 9 : "Test échoué pour y : Attendu 9 mais obtenu " + heros1.getY();
            System.out.println("\n############## SUCCESS ##############");





            
            // Troisième Test
            System.out.println("\n############## Test Fonctionnel n°3 : ##############");
            try {
                Carte maCarte3 = new Carte("resources/carte.txt");
                List<String> mouvementsTest3 = Files.readAllLines(Paths.get("resources/deplacementsTest3.txt"), StandardCharsets.UTF_8);
                String[] depart3 = mouvementsTest3.get(0).split(",");
                int xDepart3 = Integer.parseInt(depart3[0]);
                int yDepart3 = Integer.parseInt(depart3[1]);
                String sequence3 = mouvementsTest3.get(1).trim();

                if (!maCarte3.estCaseAccessible(xDepart3, yDepart3)) {
                    throw new IllegalArgumentException("Erreur : La case de départ (" + xDepart3 + ", " + yDepart3 + ") est inaccessible. Veuillez recommencer");
                }

                Personnage heros3 = new Personnage(xDepart3, yDepart3);
                for (char move : sequence3.toCharArray()) {
                    heros3.deplacer(move, maCarte3);
                }
                
                throw new AssertionError("IllegalArgumentException attendue mais non levée");

                } catch (IllegalArgumentException e) {
                    System.out.println("############## SUCCESS : IllegalArgumentException levée ##############");
                }


            
    }



}



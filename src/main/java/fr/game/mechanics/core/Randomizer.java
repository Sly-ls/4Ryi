package fr.game.mechanics.core;

import java.util.Random;

/**
 * Lance un d�s
 * C'est ici qu'est g�r� le random et les m�thodes pemant de lancer les d�s
 * @author Sly
 *
 */
public class Randomizer {


    private Random die;
    private Integer numberOfFace;


    public Randomizer() {
        this.die = new Random();
    }

    public Randomizer(int numberOfFace) {

        this.die = new Random();
        this.numberOfFace = numberOfFace;
    }

    public String getRandomNumberInRange(int nbDe, int SR){
        int nbTouche = 0,somme = 0;
        String serie = new String(" ");
        String resultatText = new String("Resultat : "+ nbDe +" Vs "+ SR +" = ");

        for(int i=0;i<nbDe;i++){

            int resDe = die.nextInt(numberOfFace)+1;
            somme = 0;
            do{
                somme += resDe;
                resDe = die.nextInt(numberOfFace);

            }while(somme%numberOfFace==0);
            //Formation du texte
            if(i +1< nbDe){
                serie += somme + " - ";
            }else{
                serie += somme;
            }
            //R�ussite !!
            if(somme>SR){
                nbTouche++;
            }
        }
        resultatText+= nbTouche +"HIT = "+serie;
        return resultatText;
    }

    public int rollTheDie(){
        int resultatDe = 0;

        resultatDe = die.nextInt(numberOfFace);

        return resultatDe;
    }

    public int getRandomNumberInRange(int maxRange){
        int resultatDe = 0;

        resultatDe = die.nextInt(maxRange)+1;

        return resultatDe;
    }
}


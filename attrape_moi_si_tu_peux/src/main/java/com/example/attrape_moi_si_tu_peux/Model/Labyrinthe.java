package com.example.attrape_moi_si_tu_peux.Model;

import java.io.*;
import java.util.*;

public class Labyrinthe {
    private int x;
    private int y;
    private int nb_tour;
    private Case[][] lesCases;
    private ArrayList<Animal> lesAnimaux;


    public Labyrinthe(){
        this.x              = 10;
        this.y              = 10;
        this.nb_tour        = 0;
        this.lesCases       = new  Case[this.x][this.y];
        this.lesAnimaux     = new ArrayList<>();
        this.genererGrille();

    }
    public Labyrinthe(int x, int y){
        this.x              = x;
        this.y              = y;
        this.nb_tour        = 0;
        this.lesCases       =  new  Case[this.x][this.y];
        this.lesAnimaux     = new ArrayList<>();
        this.genererGrille();
    }

    public Labyrinthe(File fileSelected) {
        this.importerLabyrinthe(fileSelected);
    }

    public void genererGrille() {
        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                if(i == 0 || j == 0 || i == this.x -1 || j == this.y -1) {
                    this.lesCases[i][j] = new Case(this, new Rocher());
                }
                else {
                    this.lesCases[i][j] = new Case(this, new Herbe());
                }
            }
        }
    }
    public void genererGrilleAleatoire() {
        Random random = new Random();
        for(int i = 1 ; i< this.x -1; i++) {
            for(int j = 1 ; j<this.y -1; j++) {
                int randomIndex = random.nextInt(6);
                if (randomIndex == 1){
                    this.lesCases[i][j].setContenu(new Rocher());
                }
                else this.lesCases[i][j].regeneration();
            }
        }
    }


    public void ajouterAnimal(Animal animal, int x, int y){
        if (!(this.getLesCases()[x][y].getContenu() instanceof Rocher)) {
            this.getLesCases()[x][y].setAnimal(animal);
            animal.setLaCase(getLesCases()[x][y]);
        }
    }

    public void sauvegarderLabyrinthe(File path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (int i = 0; i < this.x; i++) {
                for (int j = 0; j < this.y; j++) {
                    String cellValue;
                    if((i==0 || j ==0|| i == this.x -1 || j == this.y -1) && !(this.lesCases[i][j].getContenu() instanceof Rocher)){
                        cellValue = "s";
                        fileWriter.write(cellValue);
                    }
                    else {
                        cellValue = this.lesCases[i][j].toString();
                        fileWriter.write(cellValue);
                    }
                }
                fileWriter.write(System.lineSeparator()); // Aller à la ligne après chaque ligne de cellules
            }
            System.out.println("Le labyrinthe a été sauvegardé avec succès dans le fichier " );
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du labyrinthe : " + e.getMessage());
        }
    }

    private int[] getLabSizeFromIport(File file) {
        try (Scanner scanner = new Scanner(file)) {
            int nbLigne = 0; // ligne
            int nbcolonne = 0; // colone

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                nbLigne++;

                if (nbcolonne == 0) {
                    nbcolonne = line.length();
                } else if (line.length() != nbcolonne) {
                    System.out.println("Le labyrinthe n'a pas une taille régulière dans le fichier.");
                    return null;
                }
            }
            return new int[]{nbLigne, nbcolonne};
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier spécifié est introuvable : " + e.getMessage());
            return null;
        }
    }

    public void importerLabyrinthe(File path){
            try (Scanner scanner = new Scanner(path)) {
                int[] tailleLab = getLabSizeFromIport(path);
                this.x = tailleLab[0]; this.y = tailleLab[1];
                this.lesCases = new Case[this.x][this.y];
                this.lesAnimaux = new ArrayList<>();

                int ligne = 0;
                while (scanner.hasNextLine() && ligne < this.x) {
                    String line = scanner.nextLine();
                    for (int col = 0; col < this.y && col < line.length(); col++) {
                        char cellValue = line.charAt(col);
                        switch (cellValue){
                            case 'x' -> this.lesCases[ligne][col] = new Case(this, new Rocher());
                            case 'h', 's' -> this.lesCases[ligne][col] = new Case(this, new Herbe());
                            case 'f' -> this.lesCases[ligne][col] = new Case(this, new Marguerite());
                            case 'c' -> this.lesCases[ligne][col] = new Case(this, new Cactus());
                            case 'm' -> {
                                Mouton m = new Mouton(this);
                                this.lesCases[ligne][col] = new Case(this, new Herbe());
                                this.setLesAnimaux(m);
                                this.ajouterAnimal(m, ligne,col);
                            }
                            case 'l' -> {
                                Loup l = new Loup(this);
                                this.lesCases[ligne][col] = new Case(this, new Herbe());
                                this.setLesAnimaux(l);
                                this.ajouterAnimal(l, ligne,col);
                            }
                        }
                    }
                    ligne++;
                }
                if (ligne < this.x ) {
                    System.out.println("Le fichier ne contient pas suffisamment de lignes pour le labyrinthe.");
                } else {
                    System.out.println("Le labyrinthe a été importé avec succès depuis le fichier " + path.getAbsolutePath());
                }
            } catch (FileNotFoundException e) {
                System.out.println("Le fichier spécifié est introuvable : " + e.getMessage());
            }
    }


    public int getNb_tour() {
        return nb_tour;
    }
    public void setNb_tour( int i ){
        this.nb_tour += i;
    }

    public Case[][] getLesCases() {return this.lesCases;}

    public int[] getPosition(Animal animal) {
        int[] p = new int[2];
        for(int i = 0 ; i< this.x ; i++) {
            for(int j = 0 ; j<this.y ; j++) {
                if (this.lesCases[i][j].getAnimal() == animal ) {
                    p[0] = i ; p[1] = j;
                    break;
                }
            }
        }
        return p;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ArrayList<Animal> getLesAnimaux() {
        return this.lesAnimaux;
    }

    public boolean setLesAnimaux(Animal animal) {
        if (this.lesAnimaux.size() == 2) return false;
        else {
            this.lesAnimaux.add(animal);
            };
            return true;
        }

}

package org.example;

import java.text.SimpleDateFormat;

public class Generatornew {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static String state="";

    public static Double[] genData(int n){
        // int n wird der generate methode sagen, was sie generieren soll
        // Alle Kategorien brauchen zufaellig erzeugte Testdaten
        double multiplier=1.0;
        // Jedes Bundesland bekommt einen Faktor mit dem die Daten multipliziert werden um akurate testdaten zu simulieren
        switch(n) {
            case 0:
                multiplier = 1.0;	//burgenland
                state="Burgenland";
                break;
            case 1:
                multiplier = 2.4; 	//kaernten
                state="Kaernten";
                break;
            case 2:
                multiplier = 5.0;	//niederoesterreich
                state="Niederoesterreich";
                break;
            case 3:
                multiplier = 6.2;	//oberoesterreich
                state="Oberoesterreich";
                break;
            case 4:
                multiplier = 1.6;	//salzburg
                state="Salzburg";
                break;
            case 5:
                multiplier = 4.4;	//steiermark
                state="Steiermark";
                break;
            case 6:
                multiplier = 2.1;	//tirol
                state="Tirol";
                break;
            case 7:
                multiplier = 1.3;	//vorarlberg
                state="Vorarlberg";
                break;
            case 8:
                multiplier = 4.2;	//wien
                state="Wien";
                break;
        }

        // stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport

        // stromverbrauch:
        int minstromverbrauch = 300000;
        int maxstromverbrauch = 500000;
        double stromverbrauch = Math.floor(Math.random()*(maxstromverbrauch-minstromverbrauch+1)+minstromverbrauch)/10000 * multiplier;
        stromverbrauch =((double)((int)(stromverbrauch *10000.0)))/10000.0; //Cuttet ueberfluessige Dezimalstellen
        System.out.println("Stromverbrauch von "+ state + ": "+stromverbrauch+"PJ");

        // strompreis:
        int minstrompreis = 12;
        int maxstrompreis = 20;
        int strompreis = (int) Math.floor(Math.random()*(maxstrompreis-minstrompreis+1)+minstrompreis);
        System.out.println("Strompreis von "+ state + ": "+strompreis+"c/kWh");

        //CO2-Emissionen
        int minemission = 50000;
        int maxemission = 100000;
        double emission = Math.floor(Math.random()*(maxemission-minemission+1)+minemission)/10000 * multiplier*1.1;
        emission =((double)((int)(emission *10000.0)))/10000.0;
        System.out.println("CO2-Emissionen von "+ state + ": "+emission+"g/kWh");

        // Stromimport
        int minstromimport = 29000;
        int maxstromimport = 60000;
        double stromimport = Math.floor(Math.random()*(maxstromimport-minstromimport+1)+minstromimport)/10000 * multiplier;
        stromimport =((double)((int)(stromimport *10000.0)))/10000.0;
        System.out.println("Stromimport von "+ state + ": "+stromimport+"GWh");

        // Stromexport
        int minstromexport = 20000;
        int maxstromexport = 30000;
        double stromexport = Math.floor(Math.random()*(maxstromexport-minstromexport+1)+minstromexport)/10000 * (multiplier*0.9);
        stromexport =((double)((int)(stromexport *10000.0)))/10000.0;
        System.out.println("Stromexport von "+ state + ": "+stromexport+"GWh");

        Double[] data = {stromverbrauch, (double) strompreis, emission, stromimport, stromexport, (double) n};
        return data;
    }
    public static String generateSum(){

        return "";
    }
}

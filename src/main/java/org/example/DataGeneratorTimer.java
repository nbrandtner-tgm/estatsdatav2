package org.example;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DataGeneratorTimer {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static Date date;
    static Timestamp timestamp;
    static Timestamp timestampDelete;
    static Timer timer = new Timer();
    // Connection zu MySQL aufbauen
    static Connection conn = null;
    static Statement stmt = null;
    /**
     * @param args the command line arguments
     * Connection zur Datenbank aufbauen und Generator aufrufen, um Daten zu generieren und in die Datenbank einzufuegen
     */
    public static void main(String[] args) throws InterruptedException {

        try {
            try {
                // Treiber damit JDBC funktioniert
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            // Verbindung zur Datenbank aufbauen

            //USE FIRST LINE IF YOU RUN THE PROGRAM ON YOUR LOCAL MACHINE (Remove // in front of the line)
            //conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/estatsdb", "root", "root");
            //USE SECOND LINE IF YOU RUN THE PROGRAM IN DOCKER (Remove // in front of the line)
            conn = DriverManager.getConnection("jdbc:mysql://mysql:3306/estatsdb", "root", "root");

            System.out.println("Connection is created successfully:");

        } catch (SQLException e) {
            System.out.println(e);
        }
        LocalTime timestarted = LocalTime.now();
        // Timer erstellen, der jede Stunde die Daten generiert
        System.out.println("It's "+(60-timestarted.getMinute())+" Minutes and "+(60-timestarted.getSecond())+" Seconds until the next hour begins.");
        Thread.sleep((60-timestarted.getMinute())*60*1000-timestarted.getSecond()*1000);
        timer.scheduleAtFixedRate(new DataGenHourly(), 0, 1000 * 60 * 60);
    }

    private static class DataGenHourly extends TimerTask {
        @Override
        public void run() {
            try {
                // Timestamp erstellen
                date = new Date();
                timestamp = new Timestamp(date.getTime());
                // Timestamp von gestern erstellen um die Daten, die aelter als gestern sind zu loeschen
                Instant instant = timestamp.toInstant().minus(java.time.Duration.ofDays(30));
                timestampDelete = Timestamp.from(instant);
                System.out.println(sdf.format(timestamp) + " oder " + timestamp);
                //Statement machen und Generator aufrufen
                stmt = conn.createStatement();
                String state = "";
                double stromverbrauch = 0;
                int strompreis = 0;
                double co2Emissionen = 0;
                double stromimport = 0;
                double stromexport = 0;
                // Diese Schleife geht durch alle Bundeslaender und speichert die generierten Daten in der Datenbank
                for (int j = 0; j < 9; j++) {
                    // data ist ein Array mit den Daten die der Generator generiert
                    Double[] data = Generatornew.genData(j);
                    // da preis ein int ist muessen wir diesen wieder aus dem Double Array umwandeln
                    int preis = data[1].intValue();
                    // Bundesland herausfinden
                    switch (data[5].intValue()) {
                        case 0 -> state = "Burgenland";
                        case 1 -> state = "Kaernten";
                        case 2 -> state = "Niederoesterreich";
                        case 3 -> state = "Oberoesterreich";
                        case 4 -> state = "Salzburg";
                        case 5 -> state = "Steiermark";
                        case 6 -> state = "Tirol";
                        case 7 -> state = "Vorarlberg";
                        case 8 -> state = "Wien";
                    }
                    // Query fuer jedes Bundesland erstellen und ausfuehren
                    String genquery = "INSERT INTO estats " + "VALUES ('" + state + "',0.0," + data[0] + "," + preis + "," + data[2] + "," + data[3] + "," + data[4] + ",'" + sdf.format(timestamp) + "');";
                    stmt.executeUpdate(genquery);
                    System.out.println("Query für: " + state + " erfolgreich");
                    stromverbrauch += data[0];
                    strompreis += preis;
                    co2Emissionen += data[2];
                    stromimport += data[3];
                    stromexport += data[4];
                }


                // Strompreis Durchschnitt berechnen
                strompreis = strompreis / 9;
                // Query fuer ganz Österreich erstellen und ausfuehren
                String queryAll = "INSERT INTO estats " + "VALUES ('" + "Oesterreich" + "',100.0," + stromverbrauch + "," + strompreis + "," + co2Emissionen + "," + stromimport + "," + stromexport + ",'" + sdf.format(timestamp) + "')";
                System.out.println(queryAll);
                stmt.executeUpdate(queryAll);
                // Gesamtstatistiken fuer ganz Österreich machen
                System.out.println("Record is inserted in the table successfully..................");
                System.out.println("Please check it in the MySQL Table..........");

                // Stromverbrauch Anteile berechnen
                double oesterreichVerbrauch = 0;
                ResultSet oesterreichResSet = stmt.executeQuery("SELECT * FROM estats WHERE date='" + sdf.format(timestamp) + "' AND region='Oesterreich'");
                while (oesterreichResSet.next()) {
                    oesterreichVerbrauch = oesterreichResSet.getDouble("verbrauch");
                }
                Statement anteilStatement = conn.createStatement();
                ResultSet anteilquery = anteilStatement.executeQuery("SELECT * FROM estats WHERE date='" + sdf.format(timestamp) + "' AND region!='Oesterreich'");
                while (anteilquery.next()) {
                    String region = anteilquery.getString("region");
                    double verbrauch = anteilquery.getDouble("verbrauch");
                    String sqlAnteil = "UPDATE estats SET verbrauchAnteil=" + verbrauch / oesterreichVerbrauch * 100 + " WHERE region='" + region + "' AND date='" + sdf.format(timestamp) + "';";
                    stmt.executeUpdate(sqlAnteil);
                }
                System.out.println(anteilStatement);
                // Daten loeschen die aelter als gestern sind
                String sql = "DELETE FROM estats WHERE date < '" + sdf.format(timestampDelete) + "'";
                int anzahlGeloeschterDatensaetze = stmt.executeUpdate(sql);
                System.out.println(anzahlGeloeschterDatensaetze + " Datensätze wurden gelöscht.");
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}



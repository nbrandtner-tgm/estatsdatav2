package org.example;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class MySQLnew {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * @param args
     * @throws SQLException
     * Connection zur Datenbank aufbauen und Generator aufrufen, um Daten zu generieren und in die Datenbank einzufuegen
     */
    public static void main(String[] args) {
        Date date;
        Timestamp timestamp;
        Timestamp timestampDelete;
        // Connection zu MySQL aufbauen
        Connection conn = null;
        Statement stmt = null;
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
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://mysql:3306/estatsdb", "root", "root");

            System.out.println("Connection is created successfully:");

            while (true) {
                LocalTime time = LocalTime.now();
                boolean alreadydone = false;
                if(time.getSecond()<49 | alreadydone==true){
                Thread.sleep(10000);
                }
                // (add this line when not testing anymore, will change save intervall from minutely to hourly)
                if (time.getMinute() == 0 && time.getSecond() == 0 && alreadydone == false) {
                    System.out.println("It's a new hour!");
                    // Timestamp erstellen
                    date = new Date();
                    timestamp = new Timestamp(date.getTime());
                    // Timestamp von gestern erstellen um die Daten, die älter als gestern sind zu löschen
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
                    int count = 0;
                    // Diese Schleife geht durch alle Bundesländer und speichert die generierten Daten in der Datenbank
                    for (int j = 0; j < 9; j++) {
                        // data ist ein Array mit den Daten die der Generator generiert
                        Double[] data = Generatornew.genData(j);
                        // da preis ein int ist müssen wir diesen wieder aus dem Double Array umwandeln
                        int preis = data[1].intValue();
                        // Bundesland herausfinden
                        switch (data[5].intValue()) {
                            case 0:
                                state = "Burgenland";
                                break;
                            case 1:
                                state = "Kaernten";
                                break;
                            case 2:
                                state = "Niederoesterreich";
                                break;
                            case 3:
                                state = "Oberoesterreich";
                                break;
                            case 4:
                                state = "Salzburg";
                                break;
                            case 5:
                                state = "Steiermark";
                                break;
                            case 6:
                                state = "Tirol";
                                break;
                            case 7:
                                state = "Vorarlberg";
                                break;
                            case 8:
                                state = "Wien";
                                break;
                        }
                        // Query für jedes Bundesland erstellen und ausführen
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
                    // Query für ganz Österreich erstellen und ausführen
                    String queryAll = "INSERT INTO estats " + "VALUES ('" + "Oesterreich" + "',100.0," + stromverbrauch + "," + strompreis + "," + co2Emissionen + "," + stromimport + "," + stromexport + ",'" + sdf.format(timestamp) + "')";
                    System.out.println(queryAll);
                    stmt.executeUpdate(queryAll);
                    // Gesamtstatistiken für ganz Österreich machen
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
                    // Daten löschen die älter als gestern sind
                    String sql = "DELETE FROM estats WHERE date < '" + sdf.format(timestampDelete) + "'";
                    int anzahlGeloeschterDatensaetze = stmt.executeUpdate(sql);
                    System.out.println(anzahlGeloeschterDatensaetze + " Datensätze wurden gelöscht.");
                }
                alreadydone = true;
            }
        }catch (SQLException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}


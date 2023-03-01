package org.example;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class MySQLnew {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * @param args
     * @throws SQLException
     * Connection zur Datenbank aufbauen und Generator aufrufen, um Daten zu generieren und in die Datenbank einzufuegen
     */
    public static void main(String[] args)  {
        Date date;
        Timestamp timestamp;
        Timestamp timestampYesterday;
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
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://mysql:3306/testing", "root", "root");

            //conn= (Connection) DriverManager.getConnection("us-cdbr-east-06.cleardb.net:3307/estats", "b4b359dda34c84", "41374086");
            System.out.println("Connection is created successfully:");
            while (true) {
                // Timestamp erstellen
                date = new Date();
                timestamp = new Timestamp(date.getTime());
                // Timestamp von gestern erstellen um die Daten, die älter als gestern sind zu löschen
                Instant instant = timestamp.toInstant().minus(java.time.Duration.ofDays(1));
                timestampYesterday = Timestamp.from(instant);
                System.out.println(sdf.format(timestamp) + " oder " + timestamp);
                try {
                    // Ein Delay von 8 Sekunden damit die Daten nicht zu schnell generiert werden
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Statement machen und Generator aufrufen
                stmt = conn.createStatement();
                 String state="";
                double stromverbrauch=0;
                int strompreis=0;
                double co2Emissionen=0;
                double stromimport=0;
                double stromexport=0;
                int count=0;
                // Diese Schleife geht durch alle Bundesländer und speichert die generierten Daten in der Datenbank
                for (int j = 0; j < 9; j++) {
                    // data ist ein Array mit den Daten die der Generator generiert
                    Double[] data = Generatornew.genData(j);
                    // da preis ein int ist müssen wir diesen wieder aus dem Double Array umwandeln
                    int preis = data[1].intValue();
                    // Bundesland herausfinden
                    switch(data[5].intValue()) {
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
                    String genquery = "INSERT INTO estats " + "VALUES ('" + state + "',0.0," + data[0] + "," + preis + "," + data[2] + "," + data[3] + "," + data[4] + ",'"+sdf.format(timestamp)+"');";
                    stmt.executeUpdate(genquery);
                    System.out.println("Query für: " + state + " erfolgreich");
                    stromverbrauch+=data[0];
                    strompreis+=preis;
                    co2Emissionen+=data[2];
                    stromimport+=data[3];
                    stromexport+=data[4];
                }


                // Strompreis Durchschnitt berechnen
                strompreis=strompreis/9;
                // Query für ganz Österreich erstellen und ausführen
                String queryAll = "INSERT INTO estats " + "VALUES ('" + "Oesterreich" + "',100.0," + stromverbrauch + "," + strompreis + "," + co2Emissionen + "," + stromimport + "," + stromexport + ",'"+ sdf.format(timestamp)+"')";
                stmt.executeUpdate(queryAll);
                // Gesamtstatistiken für ganz Österreich machen
                System.out.println("Record is inserted in the table successfully..................");
                System.out.println("Please check it in the MySQL Table..........");

                // Stromverbrauch Anteile berechnen
                double oesterreichVerbrauch=0;
                ResultSet oesterreichResSet=stmt.executeQuery("SELECT * FROM estats WHERE date='"+sdf.format(timestamp)+"' AND region='Oesterreich'");
                while(oesterreichResSet.next()){
                    oesterreichVerbrauch=oesterreichResSet.getDouble("verbrauch");
                }
                Statement anteilStatement=conn.createStatement();
                ResultSet anteilquery=anteilStatement.executeQuery("SELECT * FROM estats WHERE date='"+sdf.format(timestamp)+"' AND region!='Oesterreich'");
                while(anteilquery.next()){
                    String region = anteilquery.getString("region");
                    double verbrauch = anteilquery.getDouble("verbrauch");
                    String sqlAnteil="UPDATE estats SET verbrauchAnteil="+verbrauch/oesterreichVerbrauch*100+" WHERE region='"+region+"' AND date='"+sdf.format(timestamp)+"';";
                    stmt.executeUpdate(sqlAnteil);
                }
                // Daten löschen die älter als gestern sind
                String sql = "DELETE FROM estats WHERE date < '" + sdf.format(timestampYesterday) + "'";
                int anzahlGeloeschterDatensaetze = stmt.executeUpdate(sql);
                System.out.println(anzahlGeloeschterDatensaetze + " Datensätze wurden gelöscht.");
            }
        }catch (SQLException e) {
            System.out.println(e);
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


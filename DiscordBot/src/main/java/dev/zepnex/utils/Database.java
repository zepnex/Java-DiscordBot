package dev.zepnex.utils;

import net.dv8tion.jda.api.entities.Message;

import java.sql.*;

public class Database {

    private static String url = "jdbc:postgresql://localhost:5432/Maple";

    public void createTables() {
        {
            Connection conn;
            Statement stmt;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, "mateo", "roockie12345");
                stmt = conn.createStatement();


                String sql = "CREATE TABLE IF NOT EXISTS MOVIESUGGESTIONS " +
                        "(ID SERIAL      NOT NULL PRIMARY KEY," +
                        " MOVIELINK      TEXT    NOT NULL, " +
                        " NEEDSANIME     BOOL) ";

                stmt.executeUpdate(sql);
                stmt.close();
                conn.close();

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    /**
     * ############
     * #QUERRYS####
     * ############
     **/

    public static void insert(Message msg, boolean needsAnime) {
        String[] content = msg.getContentDisplay().split("`");
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, "mateo", "roockie12345");
            conn.setAutoCommit(false);

            PreparedStatement ps = conn
                    .prepareStatement("INSERT  INTO movie (movielink, needsanime) VALUES (?,?)");
            ps.setString(1, content[0]);
            ps.setBoolean(2, needsAnime);
            ps.close();
            conn.commit();
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}

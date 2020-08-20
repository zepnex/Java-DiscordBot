package dev.zepnex.utils;

import com.google.gson.Gson;
import net.dv8tion.jda.api.entities.Message;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class Database {
    Gson gson = new Gson();
    private static BotInformation inf;
    static String path = "src\\main\\java\\dev\\zepnex\\utils\\";
    private static String url = "jdbc:postgresql://localhost:5432/Maple";
    BufferedReader reader;

    {
        try {
            reader = new BufferedReader(new FileReader(path + "BotInf.json"));
            inf = gson.fromJson(reader, BotInformation.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void createTables() {
        {
            Connection conn;
            Statement stmt;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, inf.getUser(), inf.getPassword());
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
     * #QUERRIES###
     * ############
     **/

    public static void insert(String table, String arguments, String keys) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, inf.getUser(), inf.getPassword());
            conn.setAutoCommit(false);
            String[] key = arguments.split(",");
            String values = "(";
            for (int i = 0; i < key.length; i++) {
                values += "?,";
            }
            values = values.substring(0, values.length() - 1);
            values += ")";

            String sql = String.format("INSERT INTO " + table + " (%s) VALUES " + values, keys);
            PreparedStatement ps = conn.prepareStatement(sql);


            for (int i = 0; i < key.length; i++) {
                if (key[i].equals("true") || key[i].equals("false")) {
                    ps.setBoolean(i + 1, Boolean.parseBoolean(key[i]));
                } else {
                    ps.setString(i + 1, key[i]);
                }
            }
            System.out.println(ps);
            ps.executeUpdate();
            ps.close();
            conn.commit();
            conn.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}

import config.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by artur on 04.11.2017.
 */
public class jdbc {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:8889/j1b";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Database.getConnection();
//            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //STEP 3: Open a connection
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            String sql_insert;


            Scanner scanner = new Scanner(System.in);

            System.out.println("Wybierz 1 lub 2 \n(1: Lista 2: Dodaj adres):");
            //STEP 5: Extract data from result set
            int x = scanner.nextInt();
            //Retrieve by column name
            switch (x) {
                case (1):
                    sql = "SELECT id_adresu, ulica, miasto, numer_domu FROM adresy";
                    rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int id_adresu = rs.getInt("id_adresu");
                        int numer_domu = rs.getInt("numer_domu");
                        String ulica = rs.getString("ulica");
                        String miasto = rs.getString("miasto");
                        //Display values
                        System.out.print("id_adresu: " + id_adresu);
                        System.out.print(", ulica: " + ulica);
                        System.out.print(", numer_domu: " + numer_domu);
                        System.out.println(", miasto: " + miasto);
                    }
                    rs.close();
                    break;

                case (2):

                    System.out.println("Podaj id adresu");
                    int idAdresu = scanner.nextInt();
                    System.out.println("Wpisz ulice");
                    String ulica = scanner.next();
                    System.out.println("Wpisz numer mieszkania");
                    int numer = scanner.nextInt();
                    System.out.println("Wpisz miasto");
                    String miasto = scanner.next();

                    sql_insert = "INSERT INTO adresy (id_adresu, ulica, miasto, numer_mieszkania)" +
                            " VALUES (" + idAdresu + ", '" + ulica + "', '" + miasto + "', " + numer + ")";
                    int result = stmt.executeUpdate(sql_insert);
                    break;

                    }




            //STEP 6: Clean-up environment

            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample
import java.sql.*;
import java.util.Random;

/**
 * Created by d.baskakov on 01.03.2017.
 */
public class ScriptBase {

    private static final String user = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/users9?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static Connection con;

    public static void main(String[] args) {

        String insert1000 = "INSERT INTO student VALUES(?,?,?,?,?)";
        String testName = "TestName";

        PreparedStatement preparedStatement = null;
        Random r = new Random();
        try {
            con = DriverManager.getConnection(url, user, password);

            for (int i = 1; i < 1001; i++) {
                preparedStatement = con.prepareStatement(insert1000);
                preparedStatement.setInt(1, i); // id
                preparedStatement.setString(2, testName + i); //name
                preparedStatement.setInt(3, (int) (Math.random() * 88)); // Age
                preparedStatement.setBoolean(4,r.nextBoolean()); // isAdmin
                preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // date
                preparedStatement.execute();
            }


        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) {
            }
            try {
                preparedStatement.close();
            } catch (SQLException se) {
            }
        }

        System.out.println("DONE!");
    }
}

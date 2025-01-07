import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // Database details
        String sql = "SELECT name FROM student_info WHERE rollno = 1";
        String url = "jdbc:postgresql://localhost:5432/students";
        String username = "postgres";
        String password = "sa";

        // Initialize connection and statement
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("PostgreSQL JDBC Driver not found. Please check your classpath.");
				e.printStackTrace();
			}
            // Establish connection
            con = DriverManager.getConnection(url, username, password);

            // Create a statement
            st = con.createStatement();

            // Execute query
            rs = st.executeQuery(sql);

            // Process the result
            if (rs.next()) { // Ensure there's a result
                String name = rs.getString("name");
                System.out.println("Name: " + name);
            } else {
                System.out.println("No data found for the given roll number.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print detailed error
        } finally {
            // Close resources in the reverse order of their opening
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

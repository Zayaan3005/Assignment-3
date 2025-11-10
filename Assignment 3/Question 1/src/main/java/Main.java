import java.sql.*;


public class Main {

    static String url = "jdbc:postgresql://localhost:5432/Assignment 3";
    static String user = "postgres";
    static String password = "admin";

    public static void main(String[] args) {

        try {

            Class.forName("org.postgresql.Driver");

/*
            System.out.println("Printing all students");
            getAllStudents();
            System.out.println();

            System.out.println("Adding a new student");
            addStudent("Allen", "Paul", "allen.paul@gmail.com", Date.valueOf("2000-12-24"));
            getAllStudents();
            System.out.println();

            System.out.println("Updating the email");
            updateStudentEmail(4, "paul.paul@gmail.com");
            getAllStudents();
            System.out.println();
*/

            System.out.println("Deleting the student");
            deleteStudent(4);
            getAllStudents();
            System.out.println();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getAllStudents() {

        // We start the function by establishing a connection to the DBMS
        try (Connection conn = DriverManager.getConnection(url, user, password);

             // Creating a statement object to execute SQL queries
             Statement statement = conn.createStatement();

             // Executing this select query to get all the records from the students table
             ResultSet rs = statement.executeQuery("SELECT * FROM students")) {

            // This is for getting the next row of data from the result query
            while (rs.next()) {

                int st_no = rs.getInt("student_id");
                String fname = rs.getString("first_name");
                String lname = rs.getString("last_name");
                String email = rs.getString("email");
                Date date = rs.getDate("enrollment_date");

                // Printing the data we got above
                System.out.println(st_no + " " + fname + " " + lname + "    " + email + "    " + date);
            }
        }

        // SQL related exceptions will be handled here
        catch (SQLException e ) {
            e.printStackTrace();
        }
    }

    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date){

        // This query is written in this way using ? to avoid SQL injection
        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";

        // We start the function by establishing a connection to the DBMS
        try (Connection conn = DriverManager.getConnection(url, user, password);

             // Prepared Statement is used for safe insertion of parameters in the query insertSQL
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            // At the position of the 1st parameter first_name is inserted
            pstmt.setString(1, first_name);

            // At the position of the 2nd parameter last_name is inserted
            // and so on for the statements below
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, enrollment_date);

            // To execute the above operations
            pstmt.executeUpdate();
            System.out.println("Data has been inserted");


        }


        // To catch SQL related exceptions
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudentEmail(int student_id, String new_email){

        // This query is written in this way using ? to avoid SQL injection
        String UpdateSQL = "UPDATE students SET email = ? WHERE student_id = ? ";

        // We start the function by establishing a connection to the DBMS
        try (Connection conn = DriverManager.getConnection(url, user, password);) {

            // Prepared Statement is used for safe insertion of parameters in the query UpdateSQL
            PreparedStatement pstmt = conn.prepareStatement(UpdateSQL);

            // At the 1st parameter of the UpdateSQL query new_email is inserted
            pstmt.setString(1,new_email);

            // At the 2nd parameter of the UpdateSQL query student_id is inserted
            pstmt.setInt(2,student_id);

            // To execute the above operations
            pstmt.executeUpdate();
            System.out.println("Data has been Updated");
        }

        // To catch SQL related exceptions
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteStudent(int student_id){

        // This query is written in this way using ? to avoid SQL injection
        String DeleteSQL = "DELETE FROM students WHERE student_id = ? ";

        // We start the function by establishing a connection to the DBMS
        try (Connection conn = DriverManager.getConnection(url, user, password);) {

            // Prepared Statement is used for safe insertion of parameters in the query DeleteSQL
            PreparedStatement pstmt = conn.prepareStatement(DeleteSQL);

            // At the 1st parameter of the DeleteSQL query student_id is inserted
            pstmt.setInt(1, student_id);

            // To execute the above operations
            pstmt.executeUpdate();
            System.out.println("Student has been Deleted");
        }


        // To catch SQL related exceptions
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package sample;

import com.mysql.jdbc.Statement;
import sample.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DBControl {


    public List<String> loadStudents() {
        List<String> studentNames = new ArrayList<String>();
        Connection myConn = this.Connector();
        try {
            Statement myStmt = (Statement) myConn.createStatement();
            String sql = "select name from student";
            ResultSet myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                String name = myRs.getString("name");
                studentNames.add(name);
            }
            this.close(myConn, myStmt, myRs);
            return studentNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection Connector() {
        try {
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/td1_java", "root", "mouhaha22");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myStmt != null) {
                myStmt.close();
            }
            if (myRs != null) {
                myRs.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Student fetchStudentByName(String name) {
        Student s = null;
        Connection myConn = Connector();
        try {
            Statement myStmt = (Statement) myConn.createStatement();
            String sql = "select * from student where name=\"" + name + "\"";
            ResultSet myRs = myStmt.executeQuery(sql);
            while (myRs.next()) {
                int id = myRs.getInt("idstudent");
                String gender = myRs.getString("gender");
                LocalDate birth = null;
                if (myRs.getDate("dateofbirth") != null) {
                    birth = myRs.getDate("dateofbirth").toLocalDate();
                }
                String photo = myRs.getString("photo");
                double mark = myRs.getDouble("mark");
                String comments = myRs.getString("comments");
                s = new Student(id, name, gender, birth, photo, mark, comments);
            }
            this.close(myConn, myStmt, myRs);
            return s;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean removeStudentByName(String selectedName) {
        Connection connection = Connector();
        try{
            Statement mystatement = (Statement) connection.createStatement();
            String sql = "delete from student where name=\"" + selectedName + "\"";
            mystatement.executeUpdate(sql);
            this.close(connection, mystatement, null);
            return true;
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    boolean updateStudent(Student s) {
        Connection connection = Connector();
        System.out.println(s);
        try {
            Statement mystatement = (Statement) connection.createStatement();
            String sql = "UPDATE student "
                    + "SET gender = \"" + s.getGender() + "\""
                    + ", dateofbirth = \"" +  Date.valueOf(s.getBirth_date()) + "\""
                    +", photo = \"" + s.getURL() + "\""
                    + ", mark = \"" + s.getMark() + "\""
                    + ", comments = \"" + s.getComment() + "\""
                    + "WHERE name=\"" + s.getName()+ "\"";
            mystatement.executeUpdate(sql);
            this.close(connection, mystatement, null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    boolean addStudent(Student s) {
        Connection connection = Connector();
        System.out.println(s);
        try {
            Statement mystatement = (Statement) connection.createStatement();
            String query = "INSERT INTO student (name, gender, dateofbirth, mark, comments)"
                    + "VALUES (\"" + s.getName() + "\","
                    + "\"" + s.getGender() + "\","
                    + "\"" + s.getBirth_date() + "\","
                    + "\"" + s.getMark() + "\","
                    + "\"" + s.getComment() + "\""
                    + ")";
            mystatement.executeUpdate(query);
            this.close(connection, mystatement, null);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

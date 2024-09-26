package ExampleDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOH2 implements  IDAO<Student> {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    @Override
    public Student save (Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // crear una sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO student VALUES (?,?,?,?)");
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getLastName());

            // ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return student;
    };


    @Override
    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // levantar el Driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM studiantes where id = ?");
            preparedStatement.setLong(1, id);

            // ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public Student find(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Student student = null;

        try {
            // levantar el Driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido FROM estudiantes");
            preparedStatement.setLong(1, id);

            // ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            // obtener resultados+
            while (result.next()) {
                Long idStudent = result.getLong("id");
                String name = result.getString("nombre");
                String lastName = result.getString("apellido");
                student = new Student(idStudent, name, lastName);
            }
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Student> students = new ArrayList<>();

        try {
            // levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM estudiantes");

            // ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            // obtener resultados
            while (result.next()) {
                Long id = result.getLong("id");
                String name = result.getString("nombre");
                String lastName = result.getString("apellido");
                students.add(new Student(id, name, lastName));
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

}
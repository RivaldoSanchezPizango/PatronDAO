package ExampleDAO;

import java.util.List;

public class StudentService {

    private IDAO<Student> studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAOH2();
    }

    public StudentService(IDAO<Student> studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setEstudianteDao (IDAO<Student> estudianteDao) {
        this.studentDAO = estudianteDao;
    }

    public Student save (Student student) {
        studentDAO.save(student);
        return student;
    }

    public void delete (Long id) {
        studentDAO.delete(id);
    }

    public Student find (Long id) {
        return studentDAO.find(id);
    }

    public List<Student> findAll () {
        return studentDAO.findAll();
    }

}

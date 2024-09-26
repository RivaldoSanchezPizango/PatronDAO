package ExampleDAO;

import java.util.List;

public class StudentDAOInMemory implements IDAO<Student> {

    private List<Student> studentRepository;

    public StudentDAOInMemory(List<Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        studentRepository.add(student);
        return student;
    }

    @Override
    public void delete(Long id) {
        Student student = find(id);
        studentRepository.remove(student);
    }

    @Override
    public Student find(Long id) {
        Student studentToFind =  null;
        int i = 0;

        while (i < studentRepository.size() && studentToFind == null) {
            Student student = studentRepository.get(i);
            if (student.getId() == id) {
                studentToFind = student;
            }
            i++;
        }
        return studentToFind;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository;
    }
}

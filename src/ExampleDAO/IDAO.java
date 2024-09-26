package ExampleDAO;

import java.util.List;

public interface IDAO<T> {

    T save (T t);

    void delete(Long id);

    T find (Long id);

    public List<T> findAll();
}

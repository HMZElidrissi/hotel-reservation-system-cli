package daos;

import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {
    List<T> findAll(Map<String, Object> conditions);
    T find(int id);
    void save(Map<String, Object> data);
    void update(Map<String, Object> data, int id);
    void delete(int id);
}

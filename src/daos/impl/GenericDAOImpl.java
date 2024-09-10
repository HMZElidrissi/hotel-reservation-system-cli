package daos.impl;

import java.util.List;
import java.util.Map;

import daos.GenericDAO;
import repositories.GenericRepository;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    protected GenericRepository<T> repository;

    public GenericDAOImpl(GenericRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll(Map<String, Object> conditions) {
        return repository.findAll(conditions);
    }

    @Override
    public T find(int id) {
        return repository.find(id);
    }

    @Override
    public void save(Map<String, Object> data) {
        repository.save(data);
    }

    @Override
    public void update(Map<String, Object> data, int id) {
        repository.update(data, id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}

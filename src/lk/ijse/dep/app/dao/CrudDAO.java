package lk.ijse.dep.app.dao;

import lk.ijse.dep.app.entity.SuperEntity;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO {
    boolean save(T entity)throws Exception;
    boolean update(T entity)throws  Exception;
    boolean delete(T entity)throws Exception;
    Optional <T> find(ID key)throws Exception;
    Optional<List<T>> findAll()throws Exception;


}

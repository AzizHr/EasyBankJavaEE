package dao;

import java.util.List;
import java.util.Optional;

public interface IPersonDAO<T> extends IDataDAO<T> {
    boolean update(T t);
    Optional<T> findByCode(String code);

}

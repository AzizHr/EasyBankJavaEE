package dao;

import entities.MissionHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMissionHistoryDAO<T> {

    Optional<List<T>> findAll();
    Optional<T> save(T t);
    boolean delete(String missionCode, String employeeCode, LocalDate startedAt);
}

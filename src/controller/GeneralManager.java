package controller;

import java.util.List;

public interface GeneralManager<T> {
    List<T> findAll();
    void add(T t);
    void editByCode(String code, T t);
    void removeByCode(String code);
    void showAll();
    T searchByCode(String code);
}

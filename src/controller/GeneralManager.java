package controller;

import java.io.IOException;
import java.util.List;

public interface GeneralManager<T> {
    List<T> findAll();
    void add(T t);
    void editByCode(String code, T t) throws IOException;
    void removeByCode(String code) throws IOException;
    void showAll();
    T searchByCode(String code);
}

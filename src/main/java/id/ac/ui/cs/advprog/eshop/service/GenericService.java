package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);
    List<T> findAll();
    T findById(String Id);
    void deleteById(String Id);
}
package com.ufar.studentmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository<I, E> {
    List<E> findAll();

    E add(E e);

    Optional<E> findById(I id);

    Optional<E> update(E e);

    void deleteById(I id);
}

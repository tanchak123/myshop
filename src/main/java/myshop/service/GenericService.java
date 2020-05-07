package myshop.service;

import java.util.List;

public interface GenericService<K, V> {
    void create(K k);

    K get(V id);

    void update(K k);

    void delete(V id);

    List<K> getAll();
}

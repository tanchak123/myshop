package myshop.dao;

import java.util.List;

public interface GenericDao<K, V> {

    void create(K k);

    K get(V v);

    void update(K k);

    void delete(V v);

    List<K> getAll();
}

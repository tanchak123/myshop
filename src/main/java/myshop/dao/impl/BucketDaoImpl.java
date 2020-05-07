package myshop.dao.impl;

import myshop.dao.BucketDao;
import myshop.lib.Dao;
import myshop.model.Bucket;
import myshop.model.Product;
import myshop.storage.Storage;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public void create(Bucket bucket) {
        Storage.buckets.add(bucket);
    }


    @Override
    public Bucket get(Long id) {
        return Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Указаная корзина не найдена"));
    }

    @Override
    public void update(Bucket bucket) {
        IntStream.range(0, Storage.buckets.size())
                .filter(i -> Storage.buckets.get(i).getId().equals(bucket.getId()))
                .forEach(i -> Storage.buckets.set(i, bucket));
    }

    @Override
    public void delete(Long id) {
        Storage.buckets.
                removeIf(bucket -> bucket.getId().equals(id));
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}

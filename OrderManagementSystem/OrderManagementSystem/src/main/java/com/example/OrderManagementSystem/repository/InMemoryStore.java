package com.example.OrderManagementSystem.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public final class InMemoryStore<T> {
    private  final ConcurrentMap<Long, T> data = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    public long nextId() {
        return seq.incrementAndGet();
    }
    public T get(Long id) {
        return data.get(id);
    }
    public List <T> values() {
        return List.copyOf(data.values());
    }
    public T put(Long id, T item) {
        return data.put(id, item);
    }
    public T remove(Long id) {
        return data.remove(id);
    }

}
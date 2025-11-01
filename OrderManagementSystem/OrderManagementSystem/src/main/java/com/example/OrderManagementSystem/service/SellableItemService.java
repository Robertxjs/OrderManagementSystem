package com.example.OrderManagementSystem.service;
import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class SellableItemService {
    private final CrudRepository<SellableItem, Long> repo;
    public SellableItemService(CrudRepository<SellableItem, Long> repo) {
        this.repo = repo;
    }
    public List<SellableItem> findAll() { return repo.findAll(); }
    public Optional<SellableItem> findById(Long id) { return repo.findById(id); }
    public SellableItem save(SellableItem s) { return repo.save(s); }
    public boolean delete(long id) { return repo.deleteById(id); }
}
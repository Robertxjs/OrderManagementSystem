package config;

import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.repository.InMemoryStore;
import com.example.OrderManagementSystem.repository.SellableItemRepository;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryStoresConfig {

    @Bean
    public InMemoryStore<SellableItem> sellableItemInMemoryStore() {
        return new InMemoryStore<>();
    }
}

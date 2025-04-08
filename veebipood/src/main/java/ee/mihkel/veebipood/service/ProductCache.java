package ee.mihkel.veebipood.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import ee.mihkel.veebipood.entity.Product;
import ee.mihkel.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class ProductCache {

    @Autowired
    ProductRepository productRepository;

    LoadingCache<Long, Product> loadingCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(
                    new CacheLoader<>() {
                        @Override
                        public Product load(Long id) {
                            System.out.println("VÕTAN ANDMEBAASIST");
                            return productRepository.findById(id).orElseThrow();
                        }
                    });

    public Product getProductFromCache(Long id) throws ExecutionException {
        System.out.println("VÕTAN TOODET");
        return loadingCache.get(id);
    }
}

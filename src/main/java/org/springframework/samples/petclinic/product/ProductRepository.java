package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAll();
	
	@Query("SELECT p FROM ProductType p")
    List<ProductType> findAllProductTypes();
    Optional<Product> findById(int id);
    
    @Query("SELECT p FROM Product p WHERE p.price < :price")
    List<Product> findByPriceLessThan(double price);
    
    @Query("SELECT p FROM ProductType p WHERE p.name = :name")
    ProductType getProductType(String name);
   
    Product findByName(String name);
    
    Product save(Product p);
}

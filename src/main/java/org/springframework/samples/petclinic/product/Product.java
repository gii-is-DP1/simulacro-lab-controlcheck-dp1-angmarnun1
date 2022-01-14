package org.springframework.samples.petclinic.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {

	
	@Size(min=3, max=50)
	@NotNull
	@JoinColumn(name = "name")
    private String name;
    
	@Min(0)
	@NotNull
	@JoinColumn(name = "price")
    private double price;
    
	@NotNull
	@ManyToOne
	@JoinColumn(name = "product_type_id")
    private ProductType productType;
}

package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
	
	private static final String VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";
	

	private ProductService ps;
	
	@Autowired
	public ProductController(ProductService ps) {
		this.ps = ps;
               
	}
	@ModelAttribute("types")
	public Collection<ProductType> populateProductTypes() {
		return this.ps.getAllProductsType();
	}
	
	@InitBinder("product")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	
	@GetMapping(value = "/product/create")
	public String initCreationForm(Map<String, Object> model) {
		Product product = new Product();
		model.put("product", product);
		return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping(value = "/product/create")
	public String processCreationForm(Product product, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("product", product);
			return VIEWS_PRODUCT_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.ps.save(product);
	}
		return "redirect:/products/{productId}";
	}
	
    
}

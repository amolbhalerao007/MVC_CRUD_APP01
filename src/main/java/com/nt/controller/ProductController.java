package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entity.Product;
import com.nt.repository.ProductRepository;

 

@Controller
public class ProductController { 
	
	@Autowired
	private ProductRepository repo;

	@GetMapping("/")
	public String loadForm(Model model) {
	    model.addAttribute("product", new Product());
	    return "index";
	} 
	
	@PostMapping("/product")
	public String saveProduct(@Validated @ModelAttribute("product") Product p,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "index";
		}
		
	 	p=repo.save(p); 
		if(p.getPid() != null) { 
			model.addAttribute("msg", "Product Save");
		}  
		return "index";
	}
	
	@GetMapping("/products")
	public String getAllProducts(Model model) {
		 List<Product> list = repo.findAll();
		 model.addAttribute("products", list);
		 return "data";
	}
	
	@GetMapping("/delete")
	public String Delete(@RequestParam("pid") Integer pid, Model model) { 
		repo.deleteById(pid);
		
		model.addAttribute("msg", "Product Saved");
		model.addAttribute("products", repo.findAll());
		return "data";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("pid") Integer pid, Model model) {
	    Product product = repo.findById(pid)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
	    model.addAttribute("product", product);
	    return "edit_form";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
	    repo.save(product);
	    redirectAttributes.addFlashAttribute("msg", "Product updated successfully!");
	    return "redirect:/products";
	}

	
	
	
}

package ecommerce.shop.controller;

import ecommerce.shop.dto.ProductRequestDto;
import ecommerce.shop.dto.ProductResponseDto;
import ecommerce.shop.model.Product;
import ecommerce.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto());
        return "create";
    }

    @GetMapping()
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.getById(id); // Предполагается, что productService возвращает объект типа Product
        ProductRequestDto productRequestDto = new ProductRequestDto();
        productRequestDto.setName(product.getName());
        productRequestDto.setDescription(product.getDescription());
        productRequestDto.setPrice(product.getPrice());
        productRequestDto.setUpc(product.getUpc());

        model.addAttribute("product", product);
        model.addAttribute("productRequestDto", productRequestDto);
        return "update";
    }

    @PostMapping()
    public String save(@ModelAttribute("productRequestDto") ProductRequestDto productRequestDto) {
        productService.save(productRequestDto);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("productRequestDto") ProductRequestDto productRequestDto, @PathVariable Long id, Model model) {
        productService.update(productRequestDto, id);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id, Model model) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}


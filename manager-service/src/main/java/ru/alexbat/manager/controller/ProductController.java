package ru.alexbat.manager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.alexbat.manager.client.ProductsRestClient;
import ru.alexbat.manager.client.exception.BadRequestException;
import ru.alexbat.manager.controller.payload.NewProductPayload;
import ru.alexbat.manager.entity.Product;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductController {

    private final ProductsRestClient productsRestClient;

    @GetMapping("/list")
    public String getProductsList(Model model) {
        model.addAttribute("products", productsRestClient.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("/create")
    public String getNewProductPage() {
        return "catalogue/products/new";
    }

    @PostMapping("/create")
    public String createProduct(NewProductPayload payload, Model model) {
        try {
            Product product = productsRestClient.createProduct(payload.title(), payload.details());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException ex) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", ex.getErrors());
            return "catalogue/products/new";
        }
    }
}

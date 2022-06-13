package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.controller.propertyEditors.VendorDtoPropertyEditor;
import ua.goit.model.dto.ProductDto;
import ua.goit.model.dto.VendorDto;
import ua.goit.service.ProductService;
import ua.goit.service.VendorService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    private final ProductService productService;
    private final VendorService vendorService;

    @Autowired
    public ProductController(ProductService productService, VendorService vendorService) {
        this.productService = productService;
        this.vendorService = vendorService;
    }

    @GetMapping(path = "/all")
    public String getAllProducts(Model model) {
        List<ProductDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "productsList";
    }

    @GetMapping(path = "/form/delete")
    public String getProductDeleteForm() {
        return "deleteProductForm";
    }

    @GetMapping(path = "/form/find")
    public String getProductForm() {
        return "findProductForm";
    }

    @GetMapping(path = "/form/update")
    public String getProductUpdateForm() {
        return "updateProductForm";
    }

    @GetMapping(path = "/name/")
    public String getVendor(@RequestParam("name") String productName, Model model) {
        List<ProductDto> products = productService.findByName(productName);
        model.addAttribute("products", products);
        return "findProduct";
    }

    @GetMapping(path = "/form/add")
    public String getNewProductFrom(Model model) {
        List<VendorDto> vendors = vendorService.findAll();
        model.addAttribute("vendors", vendors);
        return "addProductForm";
    }

    @PostMapping(path = "/delete")
    public ModelAndView deleteProduct(@ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult bindingResult,
                                      ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("deleteProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.delete(productDto);
        model.setViewName("productDeleted");
        return model;
    }

    @PostMapping(path = "/update")
    public ModelAndView updateProduct(@ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult bindingResult,
                                      ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("updateProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.update(productDto);
        model.setViewName("productUpdated");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @PostMapping
    public ModelAndView addProduct(@ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult bindingResult,
                                   ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("addProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.save(productDto);
        model.setViewName("productAdded");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @ModelAttribute
    public ProductDto getDefaultProductDto() {
        return new ProductDto();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(VendorDto.class, new VendorDtoPropertyEditor());
    }
}

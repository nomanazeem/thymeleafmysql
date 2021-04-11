package nazeem.web.controller;

import javax.validation.Valid;

import nazeem.web.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producttype")
public class ProductTypeController {

    @Autowired
    private nazeem.web.service.ProductTypeService productTypeService;

    private String add_edit_template="/admin/producttype/add-edit-producttype";
    private String list_template="/admin/producttype/list-producttype";
    private String list_redirect="redirect:/admin/producttype/list";

    @GetMapping("/add")
    public String addProductType(ProductType producttype, Model model){
        model.addAttribute("producttype", producttype);
        return add_edit_template;
    }

    @GetMapping("/edit/{id}")
    public String editProductType(@PathVariable("id") int id, Model model){
        ProductType producttype = productTypeService.get(id);

        model.addAttribute("producttype", producttype);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProductType(@Valid @ModelAttribute("producttype") ProductType producttype, BindingResult result, Model model){
        model.addAttribute("producttype", producttype);

        if(result.hasErrors()){
            return add_edit_template;
        }
        productTypeService.save(producttype);

        return list_redirect;
    }

    @GetMapping("/delete/{id}")
    public String deleteProductType(@PathVariable("id") int id, Model model) {
        productTypeService.delete(id);

        return list_redirect;
    }

    @GetMapping("/list")
    public String listProductType(Model model) {
        List<ProductType> listProductTypes = productTypeService.listAll();
        model.addAttribute("listProductTypes", listProductTypes);

        return list_template;
    }
}
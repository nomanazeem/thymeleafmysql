package nazeem.web.controller;

import javax.validation.Valid;

import nazeem.web.model.Product;
import nazeem.web.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private nazeem.web.service.ProductService productService;

    @Autowired
    private nazeem.web.service.ProductTypeService productTypeService;

    private String add_edit_template="/admin/product/add-edit-product";
    private String list_template="/admin/product/list-product";
    private String list_redirect="redirect:/product/list";


    @GetMapping("/add")
    public String addProduct(Product product, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }


    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        if(result.hasErrors()){
            return add_edit_template;
        }

        productService.save(product);
        return list_redirect;
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.delete(id);

        return list_redirect;
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        List<Product> listProducts = productService.listAll();
        model.addAttribute("listProducts", listProducts);

        return list_template;
    }
    /*@GetMapping("/list")
    public String listProduct(Model model
            ,@RequestParam("search") Optional<String> search
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size)
    {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String criteria = search.orElse("");

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        Page<Product> listProducts = productService.findPaginated(criteria, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("listProducts", listProducts);

        int totalPages = listProducts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return list_template;
    }*/
}
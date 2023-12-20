package fr.dawan.business.category;

import fr.dawan.business.generic.GenericController;
import fr.dawan.business.generic.GenericService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoryController extends GenericController<CategoryDto, CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }
}
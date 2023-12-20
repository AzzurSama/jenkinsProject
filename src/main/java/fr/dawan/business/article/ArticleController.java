package fr.dawan.business.article;

import fr.dawan.business.generic.GenericController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("articles")
public class ArticleController extends GenericController<ArticleDto, ArticleService> {

    public ArticleController(ArticleService service) {
        super(service);
    }

    @GetMapping("byTitle/{title}")
    public Page<ArticleDto> findByTitle(@PathVariable String title, Pageable pageable) {
        return service.findByTitle(title, pageable);
    }

    @GetMapping("byCategory/{name}")
    public Page<ArticleDto> findByCategoryNameLike(@PathVariable String name, Pageable pageable) {
        return service.findByCategory_NameLike(name, pageable);
    }
}
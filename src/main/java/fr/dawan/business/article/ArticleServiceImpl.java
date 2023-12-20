package fr.dawan.business.article;

import fr.dawan.business.generic.GenericServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article, ArticleRepository,ArticleDto, ArticleMapper> implements ArticleService {


    public ArticleServiceImpl(ArticleRepository repository, ArticleMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Page<ArticleDto> findByTitle(String title, Pageable pageable) {
        return repository.findByTitleLike("%" + title + "%", pageable).map(mapper::toDto);
    }

    @Override
    public Page<ArticleDto> findByCategory_NameLike(String name, Pageable pageable) {
        return repository.findByCategory_NameLike("%" + name + "%", pageable).map(mapper::toDto);
    }
}
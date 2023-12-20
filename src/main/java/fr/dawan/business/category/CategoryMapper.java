package fr.dawan.business.category;

import fr.dawan.business.article.Article;
import fr.dawan.business.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper extends GenericMapper<Category,CategoryDto> {
    @Override
    @Mapping(source = "articles", target = "articlesCount", qualifiedByName = "articleConverter")
    CategoryDto toDto(Category entity);

    @Named("articleConverter")
    default long articlesToArticleCount(List<Article> articles) {
        if(articles == null) return 0;
        return articles.size();
    }
}
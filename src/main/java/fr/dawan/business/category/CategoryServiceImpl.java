package fr.dawan.business.category;

import fr.dawan.business.generic.GenericServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryRepository, CategoryDto, CategoryMapper> implements CategoryService {

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(categoryRepository, categoryMapper);
        this.categoryMapper = categoryMapper;
    }

    public Optional<CategoryDto> findDtoById(Long id) {
        return repository.findById(id).map(categoryMapper::toDto);
    }

}

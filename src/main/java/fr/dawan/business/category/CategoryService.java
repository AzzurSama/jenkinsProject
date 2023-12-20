package fr.dawan.business.category;

import fr.dawan.business.generic.GenericService;

import java.util.Optional;

public interface CategoryService extends GenericService<CategoryDto> {

    Optional<CategoryDto> findDtoById(Long id);

}

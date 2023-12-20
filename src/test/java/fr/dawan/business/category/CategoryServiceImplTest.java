package fr.dawan.business.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryServiceImpl service;

    @Mock
    private CategoryMapper mapper;

    @Mock
    private CategoryRepository repository;

    @BeforeEach
    void setup(){
        service = new CategoryServiceImpl(repository, mapper);
    }

    @Test
    void findAllTest() {

    }

}
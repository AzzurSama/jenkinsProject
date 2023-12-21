package fr.dawan.business.article;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ArticleController.class, ArticleService.class, ArticleMapper.class})
class ArticleControllerTest {


    @Autowired
    private MockMvc mockMvc; // Simuler une requête HTTP vers votre application

    @MockBean // Crée un Mock dans le contexte de Spring (pour un test d'intégration)
    private ArticleRepository repository;
    @Test
    void findByTitle() throws Exception {
        // Arrange
        String title = "Un";
        Pageable pageable = PageRequest.of(0,20);

        Article articleUn = new Article("Un article", null);
        Article articleDeux = new Article("Un Article Deux", null);
        Page<Article> entities = new PageImpl<>(List.of(articleUn, articleDeux), pageable, 2);

        Mockito.when(repository.findByTitleLike("%" + title + "%", pageable)).thenReturn(entities);

        // Act
        mockMvc.perform( // Lancer une requête
                        MockMvcRequestBuilders.get("/articles/byTitle/" + title)
                ).andDo(print())
                // Assert
                .andExpect( // Corresponds à un 'Assert'
                        status().isOk()
                ).andExpect( // Vous pouvez enchainer autant de andExpect que vous le souhaitez pour vérifier différents éléments
                        jsonPath("$.content") // jsonPath("$") corresponds au body de la réponse http
                                .isNotEmpty()
                ).andExpect(jsonPath("$.numberOfElements").value(2));
    }
}
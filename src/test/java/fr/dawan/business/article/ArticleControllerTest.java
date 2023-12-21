package fr.dawan.business.article;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest({ArticleController.class, ArticleService.class, ArticleRepository.class, ArticleMapper.class})
class ArticleControllerTest {
    private ArticleRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    void findByTitle() throws Exception {
        String title = "Un";
        Pageable pageable = PageRequest.of(0,20);
        Article articleUn = new Article("Un article", null);
        Article articleDeux = new Article("Article Deux", null);
        Page<Article> entities = new PageImpl<>(List.of(articleUn));
        Mockito.when(repository.findByTitleLike("%" + title + "%", pageable)).thenReturn(entities);

        mvc.perform(
                MockMvcRequestBuilders.get("/articles/byTitle/" + title)
        ).andDo(
                print()
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$").isNotEmpty()
        );
    }
}
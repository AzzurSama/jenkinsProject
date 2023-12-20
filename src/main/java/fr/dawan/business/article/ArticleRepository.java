package fr.dawan.business.article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    Page<Article> findByTitleLike(String title, Pageable pageable);

    Page<Article> findByCategory_NameLike(String name, Pageable pageable);

}
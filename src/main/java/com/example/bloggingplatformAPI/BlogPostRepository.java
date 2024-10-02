package com.example.bloggingplatformAPI;
import com.example.bloggingplatformAPI.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    @Query("SELECT b FROM BlogPost b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<BlogPost> searchByTerm(@Param("term") String term);
}

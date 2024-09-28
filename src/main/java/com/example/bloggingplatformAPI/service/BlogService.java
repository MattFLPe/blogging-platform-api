package com.example.bloggingplatformAPI.service;

import com.example.bloggingplatformAPI.model.BlogPost;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final List<BlogPost> blogPosts = new ArrayList<>();
    public BlogPost createBlogPost(BlogPost blogPost) {
        ZonedDateTime now = ZonedDateTime.now();
        blogPost.setCreatedAt(now);
        blogPost.setUpdatedAt(now);
        blogPost.setId((long) (blogPosts.size() + 1));
        blogPosts.add(blogPost);
        return blogPost;
    }

    public List<BlogPost> getAllPosts() {
        return blogPosts;
    }

    public Optional<BlogPost> updateBlogPost(Long id, BlogPost updatedPost) {
        for (BlogPost blogPost : blogPosts) {
            if (blogPost.getId().equals(id)) {
                blogPost.setTitle(updatedPost.getTitle());
                blogPost.setContent(updatedPost.getContent());
                blogPost.setCategory(updatedPost.getCategory());
                blogPost.setTags(updatedPost.getTags());
                blogPost.setUpdatedAt(ZonedDateTime.now());

                return Optional.of(blogPost);
            }
        }
        return Optional.empty();
    }
    public Optional<BlogPost> findBlogPostById(Long id) {
    return blogPosts.stream().filter(post-> post.getId().equals(id)).findFirst();
    }
}

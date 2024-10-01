package com.example.bloggingplatformAPI.service;

import com.example.bloggingplatformAPI.BlogPostRepository;
import com.example.bloggingplatformAPI.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogPostRepository blogPostRepository;

    public BlogPost createBlogPost(BlogPost blogPost) {
        ZonedDateTime now = ZonedDateTime.now();
        blogPost.setCreatedAt(now);
        blogPost.setUpdatedAt(now);
        return blogPostRepository.save(blogPost);
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> updateBlogPost(Long id, BlogPost updatedPost) {
        Optional<BlogPost> existingPost = blogPostRepository.findById(id);
            if (existingPost.isPresent()) {
                BlogPost blogPost = existingPost.get();
                blogPost.setTitle(updatedPost.getTitle());
                blogPost.setContent(updatedPost.getContent());
                blogPost.setCategory(updatedPost.getCategory());
                blogPost.setTags(updatedPost.getTags());
                blogPost.setUpdatedAt(ZonedDateTime.now());
                return Optional.of(blogPostRepository.save(blogPost));
        }
        return Optional.empty();
    }
    public Optional<BlogPost> findBlogPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public boolean deleteBlogPost(Long id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        if (blogPost.isPresent()) {
            blogPostRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

package com.example.bloggingplatformAPI.controller;
import com.example.bloggingplatformAPI.model.BlogPost;
import com.example.bloggingplatformAPI.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class BlogPostController {
    @Autowired
    private final BlogService blogService;
    public BlogPostController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public ResponseEntity<List<BlogPost>> getPosts(@RequestParam(required = false) String term) {
        List<BlogPost> posts;
        if (term != null && !term.isEmpty()) {
            System.out.println("Search term: " + term);
            posts = blogService.searchBlogPosts(term);
        } else {
            posts = blogService.getAllPosts();
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getSinglePost(@PathVariable Long id) {
        Optional<BlogPost> blogPost = blogService.getSinglePost(id);
        if (blogPost.isPresent()) {
            return new ResponseEntity<>(blogPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        BlogPost newPost = blogService.createBlogPost(blogPost);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost updatedPost) {
        Optional<BlogPost> existingPost = blogService.updateBlogPost(id, updatedPost);
        if (existingPost.isPresent()) {
            return new ResponseEntity<>(existingPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean isDeleted = blogService.deleteBlogPost(id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }


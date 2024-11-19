package com.example.demo.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.post.domain.entity.Post;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends JpaRepository<Post, Long> {
}

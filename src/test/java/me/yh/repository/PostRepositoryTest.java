package me.yh.repository;

import me.yh.dto.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void findPosts() {
        final List<Post> posts = postRepository.findPosts();
        assertEquals(6, posts.size());
        posts.forEach(System.out::println);
    }

    @Test
    public void findPostById() {
        final Post postById = postRepository.findPostById(1L);
        assertNotNull(postById);
        System.out.println(postById);
    }
}
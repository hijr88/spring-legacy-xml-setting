package me.yh.repository;

import me.yh.dto.Post;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    private final SqlSession sqlSession;

    public PostRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Post> findPosts() {
        return sqlSession.selectList("mapper.post.findPosts");
    }

    public Post findPostById(Long id) {
        return sqlSession.selectOne("mapper.post.findPostById",id);
    }
}

package me.yh.repository;

import me.yh.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Repository
public class HomeRepository {

    private final SqlSession sqlSession;

    public HomeRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Member> findMembers() {
        return sqlSession.selectList("mapper.member.findMembers");
    }

    public Optional<Member> findMemberById(String id) {
        return Optional.ofNullable(sqlSession.selectOne("mapper.member.findMemberById",id));
    }

    @Transactional
    public Optional<Member> save(Member m) {
        final int result = sqlSession.insert("mapper.member.save", m);
        if (result == 1)
            return Optional.of(m);
        else return Optional.empty();
    }
}
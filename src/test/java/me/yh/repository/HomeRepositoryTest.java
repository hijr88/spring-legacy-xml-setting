package me.yh.repository;

import me.yh.dto.Member;
import me.yh.service.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class HomeRepositoryTest {

    @Autowired
    HomeRepository homeRepository;

    @Autowired
    HomeService homeService;

    @Test
    public void findMembers() {
        final List<Member> members = homeRepository.findMembers();

        assertEquals(3, members.size());

        members.forEach(System.out::println);
    }

    @Test
    public void findMemberById() {
        final Optional<Member> woo = homeRepository.findMemberById("ADMIN");
        assertTrue(woo.isPresent());
        System.out.println(woo.get());

        final Optional<Member> woo1 = homeRepository.findMemberById("woo");
        assertFalse(woo1.isPresent());
    }

    @Transactional
    @Test
    public void save() {
        Member m = new Member();
        m.setId("haha");
        m.setPassword("asdf");
        m.setName("who");

        final Optional<Member> woo = homeRepository.findMemberById(m.getId());
        if (woo.isPresent()) {
            System.err.println("중복된 아이디입니다.");
            return;
        }

        final Optional<Member> save = homeService.save(m);
        assertTrue(save.isPresent());
        homeService.save(m);
    }
}
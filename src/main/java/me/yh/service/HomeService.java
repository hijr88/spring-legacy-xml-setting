package me.yh.service;

import me.yh.dto.Member;
import me.yh.repository.HomeRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<Member> findMembers() {
        return homeRepository.findMembers();
    }

    @Transactional
    public Optional<Member> save(Member m) {
        return homeRepository.save(m);
    }

    @Async
    public void asy() {
        Runnable run = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("비동기를 햐보아요~");
        };
        run.run();
    }

    //@Scheduled(cron="*/5 * * * * *") // 5초 간격 실행
    public void print() {
        System.out.println(LocalDateTime.now());
    }
}
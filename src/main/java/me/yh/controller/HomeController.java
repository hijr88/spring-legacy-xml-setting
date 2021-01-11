package me.yh.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yh.dto.Member;
import me.yh.repository.HomeRepository;
import me.yh.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HomeService homeService;
    private final HomeRepository homeRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("page_title","하염");
        return "index";
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> members() {
        List<Member> members = homeService.findMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<String> member(@PathVariable("id") String id){
        log.info("입력한 id: {}",id);
        Optional<Member> member = homeRepository.findMemberById(id);
        if(member.isPresent()){
            return ResponseEntity.ok("1");
        }else{
            return new ResponseEntity<>("0", HttpStatus.OK);
        }
    }

    @PostMapping("/members/new")
    public String addMember(Member member) {
        Optional<Member> result = homeRepository.save(member);
        return "redirect:/";
    }

    @GetMapping(value = "/as")
    @ResponseBody
    public String as() {
        homeService.asy();
        return "비동기 리턴~~";
    }
}
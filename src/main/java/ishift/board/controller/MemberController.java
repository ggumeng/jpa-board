package ishift.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 회원 관련 뷰 컨트롤러
@Controller
public class MemberController {

    // 로그인 폼 페이지
    @GetMapping("/member/login-form")
    public String loginForm() {
        return "member/loginForm.html";
    }

    // 회원가입 폼 페이지
    @GetMapping("/member/join-form")
    public String joinForm() {
        return "member/joinForm.html";
    }
}

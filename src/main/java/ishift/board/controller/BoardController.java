package ishift.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ishift.board.config.MemberDetail;
import ishift.board.service.BoardService;

@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;
    
    // 메인 페이지
    @GetMapping("/")
    public String index(Authentication authentication, Model model){
        
        if (authentication != null){
            // 시큐리티 세션에 담긴 회원정보를 MemberDetail로 캐스팅해서 저장
            MemberDetail authMember = (MemberDetail) authentication.getPrincipal();
            // 모델에 authMember 속성으로 저장
            model.addAttribute("authMember", authMember);
            logger.info(authMember.toString());
        }
        
        return "main.html";
    }
    
    // 게시판 작성 페이지
    @GetMapping("/board/form")
    public String boardForm(){        
        return "board/boardForm.html";    
    }

}
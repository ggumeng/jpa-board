package ishift.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ishift.board.model.Board;
import ishift.board.service.BoardService;

@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    // BoardService DI
    @Autowired
    private BoardService boardService;
    
    // 메인 페이지
    // Pageable을 이용해 3개의 컨텐츠로 페이징 처리 
    // boardIdx를 기준으로 내림차순 정렬
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size=5, sort="boardIdx", direction = Sort.Direction.DESC) Pageable pageable){
        
        // DB에 저장된 게시판 목록들을 페이징 처리해 Page에 Board vo 형식으로 저장
        Page<Board> boardList = boardService.getAllBoardList(pageable);

        if (boardList.getTotalElements() > 0){
            // model 속성에 추가 => 게시글이 한 개라도 있을 시 boardList 추가
            model.addAttribute("boardList", boardList);
        } else {
            // 게시글이 없다면 'nullcontent' 라는 임의의 문자열 추가
            model.addAttribute("boardList", "nullcontent");
        }
        
        return "main.html";
    }
    
    // 게시판 작성 페이지
    @GetMapping("/board/form")
    public String boardForm(){        
        return "board/boardForm.html";    
    }

    // 게시글 상세조회 페이지
    @GetMapping("/board/{boardIdx}")
    public String viewBoard(@PathVariable int boardIdx, 
                            Model model, 
                            Authentication authentication){

        Board detailBoard = boardService.getBoardDetail(boardIdx);

        // model에 boardIdx로 조회한 게시글 정보 (detailBoard) 와 로그인한 회원 정보 (principal) 추가
        model.addAttribute("principal", authentication.getPrincipal());
        model.addAttribute("detailBoard", detailBoard);
        return "board/boardDetail.html";
    }

    // 게시글 수정 페이지
    @GetMapping("/board/modify-form/{boardIdx}")
    public String modifyForm(@PathVariable int boardIdx, Model model){
        // 수정할 게시글의 번호를 담아 service에게 전달 => 번호에 맞는 게시글 정보를 받아옴
        Board detailBoard = boardService.getBoardDetail(boardIdx);
        model.addAttribute("detailBoard", detailBoard);
        return "board/modifyForm.html";
    }

}
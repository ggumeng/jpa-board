package ishift.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ishift.board.config.MemberDetail;
import ishift.board.dto.ResponseDto;
import ishift.board.model.Board;
import ishift.board.service.BoardService;

// 게시판 데이터를 다루는 컨트롤러
// CRUD 요청을 주로 다룸
@RestController
public class BoardApiController {
    
    // 정보를 잘 담아오는지 확인하기 위한 logger
    private static final Logger logger = LoggerFactory.getLogger(BoardApiController.class);

    // BoardService DI
    @Autowired
    private BoardService boardService;

    /* 
        게시글 등록 프로세스
        method : POST
        @param Board(title, content)
    */
    @PostMapping("/board/write-proc")
    public ResponseDto<Board> writeProc(@RequestBody Board board, @AuthenticationPrincipal MemberDetail memberDetail){

        // 세션에 담긴 회원 정보와 파라미터로 넘어온 게시글 정보를 담아 서비스로 보냄
        ResponseDto<Board> newBoard = boardService.writeContent(board, memberDetail);
        
        logger.info(newBoard.toString());
        return newBoard; 
    }

}

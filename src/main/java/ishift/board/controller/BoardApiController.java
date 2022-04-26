package ishift.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ishift.board.config.MemberDetail;
import ishift.board.dto.ResponseDto;
import ishift.board.model.Board;
import ishift.board.model.Reply;
import ishift.board.service.BoardService;
import ishift.board.service.ReplyService;

// 게시판 데이터를 다루는 컨트롤러
// CRUD 요청을 주로 다룸
@RestController
public class BoardApiController {
    
    // 정보를 잘 담아오는지 확인하기 위한 logger
    private static final Logger logger = LoggerFactory.getLogger(BoardApiController.class);

    // BoardService DI
    @Autowired
    private BoardService boardService;

    // ReplyService DI
    @Autowired
    private ReplyService replyService;

    /** 
     *  게시글 등록 프로세스
     *  method : POST
     *  @param Board(title, content)
     */
    @PostMapping("/board/write-proc")
    public ResponseDto<String> writeProc(@RequestBody Board board, @AuthenticationPrincipal MemberDetail memberDetail){

        // 세션에 담긴 회원 정보와 파라미터로 넘어온 게시글 정보를 담아 서비스로 보냄
        ResponseDto<String> res = boardService.writeContent(board, memberDetail);
    
        return res; 
    }

    /**
     *  게시글 수정 프로세스
     *  method : PUT
     *  @param boardIdx 게시글 번호
     */
    @PutMapping("/board/{boardIdx}")
    public ResponseDto<String> modifyProc(@PathVariable int boardIdx, @RequestBody Board board){

        logger.info(board.toString());
        ResponseDto<String> res = boardService.modifyContent(boardIdx, board);
        
        return res;
    }

    /** 
     *  게시글 삭제 프로세스
     *  method : DELETE
     *  @param boardIdx 게시글 번호
     */
    @DeleteMapping("/board/{boardIdx}")
    public void deleteProc(@PathVariable int boardIdx){
        boardService.deleteContent(boardIdx);
    }


    /**
     *  댓글 등록 프로세스
     *  method : POST
     *  @param boardIdx 게시글 번호
     *  @param replyContent 댓글 내용
     *  @param id 회원 번호
     */
    @PostMapping("/board/reply-proc/{boardIdx}")
    public ResponseDto<String> replyProc(@RequestBody Reply reply, @PathVariable int boardIdx, Authentication authentication){
        
        // 스프링 시큐리티로 인해 인증받은 회원 정보를 불러온다.
        MemberDetail authMember = (MemberDetail) authentication.getPrincipal();
        logger.info(authMember.getMember().toString());
        logger.info(reply.toString());

        ResponseDto<String> res = replyService.writeReply(reply, authMember.getMember(), boardIdx);        

        return res;
    }

    /**
     *  댓글 삭제 프로세스
     *  method : DELETE
     *  @param replyIdx 댓글 번호
     */
    @DeleteMapping("/board/reply/{replyIdx}")
    public ResponseDto<String> deleteReplyProc(@PathVariable int replyIdx){
        
        ResponseDto<String> res = replyService.deleteReply(replyIdx);

        return res;
    }


}

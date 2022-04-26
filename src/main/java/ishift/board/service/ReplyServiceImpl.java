package ishift.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ishift.board.dto.ResponseDto;
import ishift.board.exception.BoardException;
import ishift.board.model.Board;
import ishift.board.model.Member;
import ishift.board.model.Reply;
import ishift.board.repository.BoardRepository;
import ishift.board.repository.ReplyRepository;

// 댓글 서비스
@Service
public class ReplyServiceImpl implements ReplyService {

    // boardRepository DI
    @Autowired
    private BoardRepository boardRepository;

    // replyRepository DI
    @Autowired
    private ReplyRepository replyRepository;

    /**
     *  댓글 등록 서비스
     *  @param reply 댓글 내용을 담은 객체
     *  @param member 로그인한 회원의 정보
     *  @param boardIdx 댓글을 쓴 게시글 번호
     */
    @Transactional
    public ResponseDto<String> writeReply(Reply reply, Member member, int boardIdx) {
    
        // boardIdx 로 게시글 정보를 받아옴
        Board board = boardRepository.findById(boardIdx)
                    .orElseThrow(() -> {
                        throw new BoardException(HttpStatus.NOT_FOUND,"존재하지 않는 게시글입니다.");
                    });

        // reply 객체에 board와 member를 넣어줌
        reply.setBoard(board);
        reply.setMember(member);

        // db에 reply 객체 insert
        replyRepository.save(reply);

        // httpstatus 상태와 함께 메세지 리턴
        return new ResponseDto<String>(HttpStatus.OK, "댓글 작성이 완료되었습니다.");
    }

    /**
     *  댓글 삭제 서비스
     *  @param replyIdx 댓글 번호
     */
    @Transactional
    public ResponseDto<String> deleteReply(int replyIdx) {
        
        // replyidx 로 데이터 delete
        replyRepository.deleteById(replyIdx);

        // httpstatus 상태와 함께 메시지 리턴
        return new ResponseDto<String>(HttpStatus.OK, "댓글이 삭제되었습니다.");

    }
    
}

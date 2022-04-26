package ishift.board.service;

import ishift.board.dto.ResponseDto;
import ishift.board.model.Member;
import ishift.board.model.Reply;

public interface ReplyService {

    // 댓글 등록 서비스
    ResponseDto<String> writeReply(Reply reply, Member member, int boardIdx);

    // 댓글 삭제 서비스
    ResponseDto<String> deleteReply(int replyIdx);
    
}

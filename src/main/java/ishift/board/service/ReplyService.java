package ishift.board.service;

import ishift.board.dto.ResponseDto;
import ishift.board.model.Member;
import ishift.board.model.Reply;

public interface ReplyService {

    ResponseDto<String> writeReply(Reply reply, Member member, int boardIdx);

    ResponseDto<String> deleteReply(int replyIdx);
    
}

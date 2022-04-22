package ishift.board.service;

import ishift.board.config.MemberDetail;
import ishift.board.dto.ResponseDto;
import ishift.board.model.Board;

public interface BoardService {

    // 게시글 등록 서비스
    ResponseDto<Board> writeContent(Board board, MemberDetail memberDetail);
    
}

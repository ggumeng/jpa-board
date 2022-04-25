package ishift.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ishift.board.config.MemberDetail;
import ishift.board.dto.ResponseDto;
import ishift.board.model.Board;

public interface BoardService {

    // 게시글 등록 서비스
    ResponseDto<Board> writeContent(Board board, MemberDetail memberDetail);

    // 게시글 전체조회 서비스
    Page<Board> getAllBoardList(Pageable pageable);

    // 게시글 상세조회 서비스
    Board getBoardDetail(int boardIdx);

    // 게시글 수정 서비스
    ResponseDto<Board> modifyContent(int boardIdx, Board board);

    // 게시글 삭제 서비스
    void deleteContent(int boardIdx);
    
}

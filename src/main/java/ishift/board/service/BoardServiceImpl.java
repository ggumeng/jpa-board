package ishift.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ishift.board.config.MemberDetail;
import ishift.board.dto.ResponseDto;
import ishift.board.model.Board;
import ishift.board.repository.BoardRepository;

/*
    BoardService 구현부
*/

@Service
public class BoardServiceImpl implements BoardService{

    // boardRepository DI
    @Autowired
    private BoardRepository boardRepository;

    /*
        게시글 등록 서비스
        @param Board(vo) 게시글 정보
    */
    @Transactional
    public ResponseDto<Board> writeContent(Board board, MemberDetail memberDetail) {
        // board 정보에 시큐리티 세션에 담긴 member 정보를 set
        board.setMember(memberDetail.getMember());
        // save 함수를 이용해 db에 저장
        Board newBoard = boardRepository.save(board);

        return new ResponseDto<Board>(HttpStatus.OK, newBoard);
    }

    /*
     *  모든 게시글 조회 서비스
     *  @param Pagealbe 페이징 정보
     */
    @Override
    public Page<Board> getAllBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    /*
     *  게시글 상세조회 서비스
     *  @param boardIdx 게시글 번호
     */
    @Transactional
    public Board getBoardDetail(int boardIdx) {
        
        // boardIdx로 검색해서 없다면 illegalArgumentException throw
        Board detailBoard = boardRepository.findById(boardIdx)
                            .orElseThrow(() -> {
                                throw new IllegalArgumentException();
                            });
        
        
        // 게시글 열람 -> 조회수 1 증가
        // 더티 체킹으로 인해 자동으로 DB에 반영
        detailBoard.setViewCount(detailBoard.getViewCount() + 1);
        return detailBoard;
    }

    /*
     *  게시글 수정 서비스
     *  @param boardIdx 게시글 번호
     */
    @Transactional
    public ResponseDto<Board> modifyContent(int boardIdx, Board board) {
        
        return null;
    }

    /*
     *   게시글 삭제 서비스
     *   @param boardIdx 게시글 번호
     */
    @Transactional
    public void deleteContent(int boardIdx) {
        // 받아온 idx로 게시글 삭제
        boardRepository.deleteById(boardIdx);
    }

    
}

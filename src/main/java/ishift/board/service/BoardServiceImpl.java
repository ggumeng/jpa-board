package ishift.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

    
}

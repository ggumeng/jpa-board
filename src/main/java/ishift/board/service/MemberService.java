package ishift.board.service;

import ishift.board.dto.ResponseDto;
import ishift.board.model.Member;

public interface MemberService {

    // 회원가입 서비스
    ResponseDto<String> joinMember(Member member);
    
}

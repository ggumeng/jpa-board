package ishift.board.service;

import ishift.board.dto.ResponseDto;
import ishift.board.model.Member;

public interface MemberService {

    ResponseDto<Member> joinMember(Member member);
    
}

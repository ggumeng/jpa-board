package ishift.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ishift.board.exception.BoardException;
import ishift.board.model.Member;
import ishift.board.repository.MemberRepository;

// 회원 인증 서비스 인터페이스 상속
@Service
public class MemberDetailService implements UserDetailsService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // 기존에 있는 회원인지 확인
        Member authMember = memberRepository.findByMemberId(username).orElseThrow(() -> {
            throw new BoardException(HttpStatus.INTERNAL_SERVER_ERROR, "존재하지 않는 회원입니다.");
        });

        // 회원 인증정보를 반환한다
        return new MemberDetail(authMember);
    }
    
}

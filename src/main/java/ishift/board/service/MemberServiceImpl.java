package ishift.board.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ishift.board.dto.ResponseDto;
import ishift.board.model.Member;
import ishift.board.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

    // memberRepository DI
    @Autowired
    private MemberRepository memberRepository;

    // passwordEncoder DI
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // 회원가입 서비스 구현
    @Transactional
    public ResponseDto<String> joinMember(Member member) {

        // 회원가입 시 존재하는 아이디일때 500 코드를 담아서 return
        if (memberRepository.findByMemberId(member.getMemberId()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
        
        // 받아온 비밀번호 정보를 encoding 후 member에 set
        String encPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encPassword);
        
        // member 정보를 db에 저장
        memberRepository.save(member);

        return new ResponseDto<String>(HttpStatus.OK, member.getMemberId() + "님의 회원가입이 완료되었습니다.");
    }
    
}

package ishift.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ishift.board.dto.ResponseDto;
import ishift.board.model.Member;
import ishift.board.service.MemberService;

// 회원 데이터를 다루는 컨트롤러
@RestController
public class MemberApiController {
    
    // 정보를 잘 담아오는지 확인하기 위한 logger
    private static final Logger logger = LoggerFactory.getLogger(MemberApiController.class);
    
    // memberService DI
    @Autowired
    private MemberService memberService;

    /**  
     * 회원가입 프로세스
     * method : POST
     * @param Member (id, password, nickname)
     */  
    @PostMapping("/member/join-proc")
    public ResponseDto<String> joinProc(@RequestBody Member member){

        ResponseDto<String> res = memberService.joinMember(member); 
        
        logger.info(res.toString());
        return res;
    }
}

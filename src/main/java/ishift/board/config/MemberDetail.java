package ishift.board.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ishift.board.model.Member;
import lombok.Getter;

@Getter
public class MemberDetail implements UserDetails{

    private Member member;

    public MemberDetail(Member member) {
		this.member = member;
	}

    // 파라미터의 비밀번호 가져옴
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // 파라미터의 아이디 가져옴
    @Override
    public String getUsername() {
        return member.getMemberId();
    }

    // 계정 만료 여부 확인
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 확인
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 비밀번호 만료 여부 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 확인
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 권한 확인
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
	public String toString() {
		return "MemberDetail [member=" + member + "]";
	}
    
}

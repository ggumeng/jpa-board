package ishift.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ishift.board.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

    // @Query("select * from member where id = ?")
    // 회원의 아이디로 DB 조회
    Optional<Member> findByMemberId(String memberId);

}

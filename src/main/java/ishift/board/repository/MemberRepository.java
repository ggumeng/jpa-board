package ishift.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ishift.board.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{

    // @Query("select * from member where id = ?")
    Member findByMemberId(String memberId);

}

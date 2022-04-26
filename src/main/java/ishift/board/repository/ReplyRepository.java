package ishift.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ishift.board.model.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{
    
}

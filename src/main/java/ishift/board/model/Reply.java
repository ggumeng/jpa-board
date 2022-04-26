package ishift.board.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
    name="REPLY_IDX_GENERATOR",
    sequenceName="REPLY_IDX",
    initialValue=1,
    allocationSize=1
)
public class Reply {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "REPLY_IDX_GENERATOR")
    private int replyIdx;

    @ManyToOne
    @JoinColumn(name="id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="boardIdx")
    private Board board;

    @Column (nullable = false, length = 50)
    private String replyContent;

    @CreationTimestamp
    private Timestamp regDate;
}

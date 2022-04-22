package ishift.board.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시글 vo
// 테이블, GETTER , SETTER, 생성자, 빌더, 시퀀스 생성
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
    name="BOARD_IDX_GENERATOR",
    sequenceName="BOARD_IDX",
    initialValue=1,
    allocationSize=1
)
public class Board {
    
    // 게시글 IDX : 시퀀스 전략을 사용해 PRIMARY KEY로 저장, Integer 타입 (1부터 시작)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BOARD_IDX_GENERATOR")
    private int boardIdx;

    // 하나의 id는 다수의 게시글을 작성할 수 있음 (다대일)
    // Member의 id를 외래키로 참조
    @ManyToOne
    @JoinColumn(name="id")
    private Member member;

    // 게시글 제목 : 20 길이의 null 값을 허용하지 않는 String 타입
    @Column(nullable = false, length = 20)
    private String title;

    // 게시글 내용 : 에디터 썸머노트를 이용해 대용량 데이터가 될 수 있으므로 @Lob 어노테이션
    @Lob
    private String content;

    // 게시글 조회수 : default가 0인 Integer 타입
    @ColumnDefault("0")
    private int viewCount;

    // 게시글 생성일자 : CreationTimeStamp 로 생성일자 자동생성
    @CreationTimestamp
    private Timestamp regDate;

}

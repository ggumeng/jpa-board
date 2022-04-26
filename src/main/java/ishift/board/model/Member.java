package ishift.board.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 1부터 시작하는 MEMBER_IDX 이름으로 시퀀스 생성
@SequenceGenerator(
    name="ID_GENERATOR",
    sequenceName="id",
    initialValue=1,
    allocationSize=1
)
// null값이 들어가지 못하게 막는 어노테이션
@DynamicInsert
public class Member {
    
    // 회원 IDX : 시퀀스 전략을 사용해 PRIMARY KEY로 저장, Integer 타입 (1부터 시작)
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
    private int id;

    // H2 Database 같은 경우 null 값을 insert 하면 빈 문자열로 들어가버림 (nullable=false, @notnull X)
    // 회원 ID : null값 허용하지 않으며 20길이의 String 타입
    @Column(length = 20)
    @NotBlank
    private String memberId;

    // 회원 비밀번호 : null값 허용하지 않으며 100길이의 String 타입 => 암호화한 패스워드를 넣기 위함
    @Column(length = 100)
    @NotBlank
    private String password;

    // 회원 닉네임 : null값 허용하지 않으며 10길이의 String 타입
    @Column(length = 10)
    @NotBlank
    private String nickname;

    // 회원 생성일자 : CreationTimestamp로 자동 생성
    @CreationTimestamp
    private Timestamp regDate;

}

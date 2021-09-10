package dev.parctice.order.domain.parthner;

import dev.parctice.order.common.util.TokenGenerator;
import dev.parctice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * 파트너의 Entity를 선언하여 관리하는 클래스 파일
 *
 * @Entity
 * @Entity가 붙은 클래스는 JPA가 관리
 *
 * @NoArgsConstructor
 * 파라미터가 없는 기본 생성자를 생성
 *
 * @Table(name = "partners")
 * Entity와 매핑할 테이블을 지정, name 속성은 매핑할 테이블 이름을 기재(default Entity 이름 사용)
 */
@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "partners")
public class Partner extends AbstractEntity {
    // 파트너 prefix 변수 선언
    private static final String PREFIX_PARTNER = "ptn_";

    /**
     * partner의 Entity를 변수 생성
     *
     * @Id
     * 기본 키 매핑
     *
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * 주키의 값을 위한 자동 생성 전략을 명시, strategy 속성을 IDENTITY로 명시할 경우
     * 기본 키 생성을 DB에 위임 즉, id값을 null로 하면 DB가 자동으로 AUTO_INCREMENT
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;           // 식별키
    private String partnerToken; // 파트너토큰(랜덤토큰)

    // 필수값을 강제화하기 위해 객체를 생성하는 과정에서 필수값이 무조건 들어오게 코딩을 한다.
    private String partnerName;  // 파트너명(필수값)
    private String businessNo;   // 사업자등록번호(필수값)
    private String email;        // 이메일(필수값)

    // partner 의 상태를 Enum 으로 관리하기 위해 선언
    @Enumerated(EnumType.STRING)
    private Status status;

    //partner의 상태값을 내부에서 관리
    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활설화"), DISBALE("비활성화");
        private final String description;
    }

    @Builder
    public Partner(String partnerName, String businessNo, String email){
        // 필수값이 빈값인 경우 throw
        if(StringUtils.isEmpty(partnerName)) throw new RuntimeException("empty partnerName");
        if(StringUtils.isEmpty(businessNo))  throw new RuntimeException("empty businessNo");
        if(StringUtils.isEmpty(email))       throw new RuntimeException("empty email");

        this.partnerToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER); //토큰은 생성 시 랜덤하게 채번
        this.partnerName  = partnerName;
        this.businessNo   = businessNo;
        this.email        = email;
        this.status       = Status.ENABLE;
    }

    // 파트너의 상태를 활성화시켜주는 메서드
    public void enable(){
        this.status = Status.ENABLE;
    }

    // 파트너의 상태를 비활성화시켜주는 메서드
    public void disable(){
        this.status = Status.DISBALE;
    }
}

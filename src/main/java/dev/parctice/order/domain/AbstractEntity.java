package dev.parctice.order.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

/**
 * 공통으로 사용할 Entity를 선언할 클래스 파일
 * -선언한 값들이 MappedSuperclass에 의해 자동으로 생성이 된다?-
 *
 * @MappedSuperclass
 * JPA Entity 클래스들이 해당 추상 클래스를 상속할 경우 createDate, modifiedDate를 컬럼으로 인식
 *
 * @EntityListeners(AuditingEntityListener.class)
 * 해당 클래스에 Auditing 기능을 포함
 * Audit은 감시하다, 감사하다라는 뜻으로 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
 *
 * @CreationTimestamp
 * DB insert 시 시간 자동 저장(ZonedDateTime 지원)
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

//    @CreatedDate - LocalDateTime만 지원
    @CreationTimestamp
    private ZonedDateTime createdAt;

//    @LastModifiedDate - LocalDateTime만 지원
    @CreationTimestamp
    private ZonedDateTime updatedAt;

}

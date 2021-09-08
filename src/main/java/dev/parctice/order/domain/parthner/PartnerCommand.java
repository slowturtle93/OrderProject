package dev.parctice.order.domain.parthner;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Service의 Create, Update, Delete 처리를 위한 파라미터를 관리하는 클래스 파일
 */
@Getter
@Builder
@ToString
public class PartnerCommand {
    private final String partnerName;
    private final String businessNo;
    private final String email;

    public Partner toEntity(){
        return Partner.builder()
                .partnerName(partnerName)
                .businessNo(businessNo)
                .email(email)
                .build();
    }
}

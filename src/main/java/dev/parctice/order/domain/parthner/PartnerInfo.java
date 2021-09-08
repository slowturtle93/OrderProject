package dev.parctice.order.domain.parthner;

import lombok.Builder;
import lombok.Getter;

/**
 * 파트너의 정보를 return을 담당하는 클래스 파일
 * DB에서 조회하여 가져온 Entity를 그대로 리턴하지 않기 위한 객체
 */
@Getter
public class PartnerInfo {
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;
    private Partner.Status status;

    @Builder
    public PartnerInfo(Partner partner) {
        this.id           = partner.getId();
        this.partnerToken = partner.getPartnerToken();
        this.partnerName  = partner.getPartnerName();
        this.businessNo   = partner.getBusinessNo();
        this.email        = partner.getEmail();
        this.status       = partner.getStatus();
    }
}

package dev.parctice.order.domain.parthner;

/**
 * 파트너 도메인의 요구사항을 관리하는 interface
 */
public interface PartnerService {
    PartnerInfo registerPartner(PartnerCommand command);
    PartnerInfo getPartnerInfo(String partnerToken);
    PartnerInfo enablePartner(String partnerToken);
    PartnerInfo disablePartner(String partnerToken);
}

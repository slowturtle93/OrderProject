package dev.parctice.order.domain.parthner;

/**
 * 파트너 도메인의 조회를 담당하는 클래스 파일
 */
public interface PartnerReader {
    Partner getPartner(String partnerToken);
}

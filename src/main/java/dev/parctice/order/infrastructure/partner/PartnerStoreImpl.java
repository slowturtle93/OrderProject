package dev.parctice.order.infrastructure.partner;

import dev.parctice.order.common.exception.InvalidParamException;
import dev.parctice.order.domain.parthner.Partner;
import dev.parctice.order.domain.parthner.PartnerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * LowLevel 요구사항을 담당, PartnerStore의 실제 구현체를 담당하는 클래스 파일
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerStoreImpl implements PartnerStore {

    private final PartnerRepository partnerRepository;

    @Override
    public Partner store(Partner partner) {
        if (StringUtils.isEmpty(partner.getPartnerToken())) throw new InvalidParamException("partner.getPartnerToken()");
        if (StringUtils.isEmpty(partner.getPartnerName()))  throw new InvalidParamException("partner.getPartnerName()");
        if (StringUtils.isEmpty(partner.getBusinessNo()))   throw new InvalidParamException("partner.getBusinessNo()");
        if (StringUtils.isEmpty(partner.getEmail()))        throw new InvalidParamException("partner.getEmail()");
        if (partner.getStatus() == null)                    throw new InvalidParamException("partner.getStatus()");

        return partnerRepository.save(partner);
    }
}

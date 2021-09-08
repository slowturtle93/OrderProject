package dev.parctice.order.infrastructure.partner;

import dev.parctice.order.common.exception.EntityNotFoundException;
import dev.parctice.order.domain.parthner.Partner;
import dev.parctice.order.domain.parthner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * LowLevel 요구사항을 담당, PartnerReader의 실제 구현체를 담당하는 클래스 파일
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PartnerReadImpl implements PartnerReader {
    private final PartnerRepository partnerRepository;

    @Override
    public Partner getPartner(String partnerToken) {
        return partnerRepository.findByPartnerToken(partnerToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}

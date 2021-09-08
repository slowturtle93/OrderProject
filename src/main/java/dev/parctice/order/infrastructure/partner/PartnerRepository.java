package dev.parctice.order.infrastructure.partner;

import dev.parctice.order.domain.parthner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
     // 파트너 정보 조회 시 값이 없는 경우가 있을 수 있으므로 리턴값은 Optional
     Optional<Partner> findByPartnerToken(String partnerToken);
}

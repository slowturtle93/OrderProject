package dev.parctice.order.application.partner;

import dev.parctice.order.domain.notification.NotificationService;
import dev.parctice.order.domain.parthner.PartnerCommand;
import dev.parctice.order.domain.parthner.PartnerInfo;
import dev.parctice.order.domain.parthner.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 비즈니스 결정을 내리진 않지만 수행할 작업을 정의
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerFacade {
    private final PartnerService partnerService;
    private final NotificationService notificationService;

    // PartnerApiContoller에서 받은 command정보를 DB 저장 관련 수행할 작업을 정의한다.
    public PartnerInfo registerPartner(PartnerCommand command){
        // 1. 신규로 접수한 파트너의 정보를 등록한다.
        // 2. 등록이 완료된 파트너에게 이메일로 고지를 한다.

        var partnerInfo = partnerService.registerPartner(command); // 파트너 정보를 등록한다.
        notificationService.sendEmail(partnerInfo.getEmail(), "title", "description"); // 등록 후 이메일 발송을 한다.
        return partnerInfo; // 등록 된 파트너 정보를 리턴한다.
    }
}

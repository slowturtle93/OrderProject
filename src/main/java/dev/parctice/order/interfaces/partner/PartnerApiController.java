package dev.parctice.order.interfaces.partner;

import dev.parctice.order.application.partner.PartnerFacade;
import dev.parctice.order.common.response.CommonResponse;
import dev.parctice.order.domain.parthner.PartnerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/partners")
public class PartnerApiController {
    private final PartnerFacade partnerFacade;

    @PostMapping
    public CommonResponse registerPartner(PartnerDto.RegisterRequest request){
        // 1. 외부에서 전달된 파라미터(dto) -> Command, Criteria convert
        // 2. facade 호출 .. PartnerInfo
        // 3. PartnerInfo -> CommonResponse convert AND return

        var Command = request.toCommand();                   // request 로 전달된 정보를 Command 로 정보를 받는다.
        var partnerInfo= partnerFacade.registerPartner(Command); // Command 정보를 저장 후 정보를 조회한다.
        var response = new PartnerDto.RegisterResponse(partnerInfo);        // 저장된 파트너 객체를 생성한다.
        return CommonResponse.success(response);                            // 응답 메시지를 리턴한다.
    }
}

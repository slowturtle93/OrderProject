package dev.parctice.order.interfaces.partner;

import dev.parctice.order.domain.parthner.Partner;
import dev.parctice.order.domain.parthner.PartnerCommand;
import dev.parctice.order.domain.parthner.PartnerInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class PartnerDto {
    // static inner class

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest{

        @NotEmpty(message = "partnerName는 필수값입니다.")
        private String partnerName;

        @NotEmpty(message = "businessNo는 필수값입니다.")
        private String businessNo;

        @Email(message = "email 형식에 맞아야 합니다.")
        @NotEmpty(message = "email 필수값입니다.")
        private String email;

        // Request dto 를 domain 의 Command 객체로 변환
        public PartnerCommand toCommand(){
            return PartnerCommand.builder()
                    .partnerName(partnerName)
                    .businessNo(businessNo)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterResponse{
        private final String partnerToken;
        private final String partnerName;
        private final String businessNo;
        private final String email;
        private final Partner.Status status;

        // Response dto 로 변환하여 응집도를 높인다.
        public RegisterResponse(PartnerInfo partnerInfo) {
            this.partnerToken = partnerInfo.getPartnerToken();
            this.partnerName = partnerInfo.getPartnerName();
            this.businessNo = partnerInfo.getBusinessNo();
            this.email = partnerInfo.getEmail();
            this.status = partnerInfo.getStatus();
        }
    }
}

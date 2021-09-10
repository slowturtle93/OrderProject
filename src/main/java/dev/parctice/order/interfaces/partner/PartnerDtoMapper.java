package dev.parctice.order.interfaces.partner;

import dev.parctice.order.domain.parthner.PartnerInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface PartnerDtoMapper {

    // retrieve
    PartnerDto.Main of(PartnerInfo main);
}

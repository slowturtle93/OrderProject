package dev.parctice.order.interfaces.item;

import dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionGroupRequest.RegisterItemOptionGroupRequestBuilder;
import dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionRequest.RegisterItemOptionRequestBuilder;
import dev.parctice.order.domain.item.ItemCommand.RegisterItemRequest.RegisterItemRequestBuilder;
import dev.parctice.order.domain.item.ItemInfo.ItemOptionGroupInfo;
import dev.parctice.order.domain.item.ItemInfo.ItemOptionInfo;
import dev.parctice.order.interfaces.item.ItemDto.ItemOptionGroupInfo.ItemOptionGroupInfoBuilder;
import dev.parctice.order.interfaces.item.ItemDto.ItemOptionInfo.ItemOptionInfoBuilder;
import dev.parctice.order.interfaces.item.ItemDto.Main;
import dev.parctice.order.interfaces.item.ItemDto.Main.MainBuilder;
import dev.parctice.order.interfaces.item.ItemDto.RegisterItemOptionGroupRequest;
import dev.parctice.order.interfaces.item.ItemDto.RegisterItemOptionRequest;
import dev.parctice.order.interfaces.item.ItemDto.RegisterItemRequest;
import dev.parctice.order.interfaces.item.ItemDto.RegisterResponse;
import dev.parctice.order.interfaces.item.ItemDto.RegisterResponse.RegisterResponseBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-10T14:50:11+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Eclipse Foundation)"
)
@Component
public class ItemDtoMapperImpl implements ItemDtoMapper {

    @Override
    public dev.parctice.order.domain.item.ItemCommand.RegisterItemRequest of(RegisterItemRequest request) {
        if ( request == null ) {
            return null;
        }

        RegisterItemRequestBuilder registerItemRequest = dev.parctice.order.domain.item.ItemCommand.RegisterItemRequest.builder();

        registerItemRequest.itemOptionGroupRequestList( registerItemOptionGroupRequestListToRegisterItemOptionGroupRequestList( request.getItemOptionGroupList() ) );
        registerItemRequest.itemName( request.getItemName() );
        registerItemRequest.itemPrice( request.getItemPrice() );

        return registerItemRequest.build();
    }

    @Override
    public dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionGroupRequest of(RegisterItemOptionGroupRequest request) {
        if ( request == null ) {
            return null;
        }

        RegisterItemOptionGroupRequestBuilder registerItemOptionGroupRequest = dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionGroupRequest.builder();

        registerItemOptionGroupRequest.itemOptionRequestList( registerItemOptionRequestListToRegisterItemOptionRequestList( request.getItemOptionList() ) );
        registerItemOptionGroupRequest.ordering( request.getOrdering() );
        registerItemOptionGroupRequest.itemOptionGroupName( request.getItemOptionGroupName() );

        return registerItemOptionGroupRequest.build();
    }

    @Override
    public dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionRequest of(RegisterItemOptionRequest request) {
        if ( request == null ) {
            return null;
        }

        RegisterItemOptionRequestBuilder registerItemOptionRequest = dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionRequest.builder();

        registerItemOptionRequest.ordering( request.getOrdering() );
        registerItemOptionRequest.itemOptionName( request.getItemOptionName() );
        registerItemOptionRequest.itemOptionPrice( request.getItemOptionPrice() );

        return registerItemOptionRequest.build();
    }

    @Override
    public RegisterResponse of(String itemToken) {
        if ( itemToken == null ) {
            return null;
        }

        RegisterResponseBuilder registerResponse = RegisterResponse.builder();

        registerResponse.itemToken( itemToken );

        return registerResponse.build();
    }

    @Override
    public Main of(dev.parctice.order.domain.item.ItemInfo.Main main) {
        if ( main == null ) {
            return null;
        }

        MainBuilder main1 = Main.builder();

        main1.itemToken( main.getItemToken() );
        main1.partnerId( main.getPartnerId() );
        main1.itemName( main.getItemName() );
        main1.itemPrice( main.getItemPrice() );
        main1.status( main.getStatus() );
        main1.itemOptionGroupList( itemOptionGroupInfoListToItemOptionGroupInfoList( main.getItemOptionGroupList() ) );

        return main1.build();
    }

    @Override
    public dev.parctice.order.interfaces.item.ItemDto.ItemOptionGroupInfo of(ItemOptionGroupInfo itemOptionGroup) {
        if ( itemOptionGroup == null ) {
            return null;
        }

        ItemOptionGroupInfoBuilder itemOptionGroupInfo = dev.parctice.order.interfaces.item.ItemDto.ItemOptionGroupInfo.builder();

        itemOptionGroupInfo.ordering( itemOptionGroup.getOrdering() );
        itemOptionGroupInfo.itemOptionGroupName( itemOptionGroup.getItemOptionGroupName() );
        itemOptionGroupInfo.itemOptionList( itemOptionInfoListToItemOptionInfoList( itemOptionGroup.getItemOptionList() ) );

        return itemOptionGroupInfo.build();
    }

    @Override
    public dev.parctice.order.interfaces.item.ItemDto.ItemOptionInfo of(ItemOptionInfo itemOption) {
        if ( itemOption == null ) {
            return null;
        }

        ItemOptionInfoBuilder itemOptionInfo = dev.parctice.order.interfaces.item.ItemDto.ItemOptionInfo.builder();

        itemOptionInfo.ordering( itemOption.getOrdering() );
        itemOptionInfo.itemOptionName( itemOption.getItemOptionName() );
        itemOptionInfo.itemOptionPrice( itemOption.getItemOptionPrice() );

        return itemOptionInfo.build();
    }

    protected List<dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionGroupRequest> registerItemOptionGroupRequestListToRegisterItemOptionGroupRequestList(List<RegisterItemOptionGroupRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionGroupRequest> list1 = new ArrayList<dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionGroupRequest>( list.size() );
        for ( RegisterItemOptionGroupRequest registerItemOptionGroupRequest : list ) {
            list1.add( of( registerItemOptionGroupRequest ) );
        }

        return list1;
    }

    protected List<dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionRequest> registerItemOptionRequestListToRegisterItemOptionRequestList(List<RegisterItemOptionRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionRequest> list1 = new ArrayList<dev.parctice.order.domain.item.ItemCommand.RegisterItemOptionRequest>( list.size() );
        for ( RegisterItemOptionRequest registerItemOptionRequest : list ) {
            list1.add( of( registerItemOptionRequest ) );
        }

        return list1;
    }

    protected List<dev.parctice.order.interfaces.item.ItemDto.ItemOptionGroupInfo> itemOptionGroupInfoListToItemOptionGroupInfoList(List<ItemOptionGroupInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.parctice.order.interfaces.item.ItemDto.ItemOptionGroupInfo> list1 = new ArrayList<dev.parctice.order.interfaces.item.ItemDto.ItemOptionGroupInfo>( list.size() );
        for ( ItemOptionGroupInfo itemOptionGroupInfo : list ) {
            list1.add( of( itemOptionGroupInfo ) );
        }

        return list1;
    }

    protected List<dev.parctice.order.interfaces.item.ItemDto.ItemOptionInfo> itemOptionInfoListToItemOptionInfoList(List<ItemOptionInfo> list) {
        if ( list == null ) {
            return null;
        }

        List<dev.parctice.order.interfaces.item.ItemDto.ItemOptionInfo> list1 = new ArrayList<dev.parctice.order.interfaces.item.ItemDto.ItemOptionInfo>( list.size() );
        for ( ItemOptionInfo itemOptionInfo : list ) {
            list1.add( of( itemOptionInfo ) );
        }

        return list1;
    }
}

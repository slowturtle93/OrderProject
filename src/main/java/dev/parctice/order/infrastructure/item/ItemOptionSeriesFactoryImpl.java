package dev.parctice.order.infrastructure.item;

import dev.parctice.order.domain.item.Item;
import dev.parctice.order.domain.item.ItemCommand;
import dev.parctice.order.domain.item.ItemOptionSeriesFactory;
import dev.parctice.order.domain.item.option.ItemOptionStore;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    /**
     * ItemOptionGroup, ItemOption List 정보 저장
     *
     * @param command
     * @param item
     * @return
     */
    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {
        var ItemOptionGroupRequestList = command.getItemOptionGroupRequestList();
        if(CollectionUtils.isEmpty(ItemOptionGroupRequestList)) return Collections.emptyList();

        return ItemOptionGroupRequestList.stream()
                .map(requestItemOptionGroup ->{
                    // itemOptionGroup store
                    var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                    var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

                    // itemOption store
                    requestItemOptionGroup.getItemOptionRequestList().forEach(requestItemOption ->{
                        var initItemOption = requestItemOption.toEntity(itemOptionGroup);
                        itemOptionStore.store(initItemOption);
                    });

                    return itemOptionGroup;
                }).collect(Collectors.toList());
    }
}

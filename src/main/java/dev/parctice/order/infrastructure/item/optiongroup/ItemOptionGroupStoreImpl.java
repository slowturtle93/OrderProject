package dev.parctice.order.infrastructure.item.optiongroup;

import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import dev.parctice.order.domain.item.optionGroup.ItemOptionGroupStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionGroupStoreImpl implements ItemOptionGroupStore {
    private final ItemOptionGroupRepository itemOptionGroupRepository;

    /**
     * ItemOptionGroup 정보 저장
     *
     * @param itemOptionGroup
     * @return
     */
    @Override
    public ItemOptionGroup store(ItemOptionGroup itemOptionGroup) {
        return itemOptionGroupRepository.save(itemOptionGroup);
    }
}

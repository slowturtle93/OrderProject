package dev.parctice.order.infrastructure.item.option;

import dev.parctice.order.domain.item.option.ItemOption;
import dev.parctice.order.domain.item.option.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionStoreImpl implements ItemOptionStore {
    private final ItemOptionRepository itemOptionRepository;

    /**
     * ItemOption 정보 저장
     * @param itemOption
     */
    @Override
    public void store(ItemOption itemOption) {
        itemOptionRepository.save(itemOption);
    }
}

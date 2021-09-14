package dev.parctice.order.infrastructure.item.optiongroup;

import dev.parctice.order.domain.item.optionGroup.ItemOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionGroupRepository extends JpaRepository<ItemOptionGroup, Long> {
}

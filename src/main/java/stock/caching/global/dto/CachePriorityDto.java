package stock.caching.global.dto;

import lombok.Builder;
import lombok.Getter;
import stock.caching.global.entity.CachePriority;

@Getter
@Builder
public class CachePriorityDto {
    private String key;
    private Integer priority;

    public CachePriority toEntity() {
        return CachePriority.builder()
            .key(key)
            .priority(priority)
            .build();
    }
}

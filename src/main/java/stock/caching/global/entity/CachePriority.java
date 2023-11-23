package stock.caching.global.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CachePriority implements Serializable {

    private String key;
    private Integer priority;

    public void decreasePriority() {
        priority--;
    }
}

package stock.caching.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.caching.global.dto.CachePriorityDto;
import stock.caching.global.entity.CachePriority;
import stock.caching.global.repository.CachePriorityRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CachePriorityService {

    private final CachePriorityRepository cachePriorityRepository;

    public void updatePriority(CachePriorityDto cachePriorityDto) {
        CachePriority priority = cachePriorityRepository.getPriority(cachePriorityDto);
        cachePriorityRepository.updatePriority(priority);
    }

    public boolean isNeedCache(CachePriorityDto cachePriorityDto) {
        CachePriority priority = cachePriorityRepository.getPriority(cachePriorityDto);
        return priority.getPriority() <= 0;
    }

}

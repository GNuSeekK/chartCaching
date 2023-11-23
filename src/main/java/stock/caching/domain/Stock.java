package stock.caching.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import stock.caching.domain.base.BaseTimeEntity;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Stock extends BaseTimeEntity implements Persistable<String> {

    @Id
    @Column(name = "stock_code")
    private String code;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockPrice> stockPrices = new ArrayList<>();
    @Override
    public String getId() {
        return this.getCode();
    }
    @Override
    public boolean isNew() {
        return this.getCreatedDate() == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        Stock stock = (Stock) o;
        return Objects.equals(getCode(), stock.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}

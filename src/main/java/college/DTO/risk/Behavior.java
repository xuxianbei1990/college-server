package college.DTO.risk;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Behavior {

    private Long userId;

    private Long entrustId;

    private String sourceCoin;

    private String targetCoin;
    /**
     * 0-市价 1-限价 2-指价
     */
    private Integer marketType;

    /**
     * 0-买入 1-卖出
     */
    private Integer direction;

    private BigDecimal entrustPrice;
    /**
     * 市价买入为额，其余为量
     */
    private BigDecimal entrustAmount;

    /**
     * 平仓单保证金
     */
    private BigDecimal deposit;
    /**
     * 平仓单盈亏
     */
    private BigDecimal profit;

    private Date eventTime;
}
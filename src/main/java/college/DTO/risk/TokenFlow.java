package college.DTO.risk;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TokenFlow {

    private Long userId;
    /**
     * 币种
     */
    private String symbol;
    /**
     * 数量
     */
    private BigDecimal amount;
    /**
     * 充币地址/提币地址
     */
    private String address;

    /**
     * 提币时的登录IP
     */
    private String ip;
    /**
     * 事件发生时间
     */
    private Date eventTime;
}
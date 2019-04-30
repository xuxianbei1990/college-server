package college.service;

public interface Topics {
    String USER_LOGIN = "risk-user-login";
    String TOKEN_OUT = "risk-token-out";
    String TOKEN_IN = "risk-token-in";
    String C2C_OUT = "risk-c2c-out";
    String C2C_IN = "risk-c2c-in";

    String NORMAL_ORDER_MAKE = "risk-order-make";
    String LEVERAGE_ORDER_MAKE = "risk-lever-make";
    String CONTRACT_ORDER_MAKE = "risk-contract-mak";

    String LEVERAGE_POSITION_CLOSE = "risk-lever-close";
    String CONTRACT_POSITION_CLOSE = "risk-contract-close";
}

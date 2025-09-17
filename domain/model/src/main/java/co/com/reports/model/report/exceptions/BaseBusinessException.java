package co.com.reports.model.report.exceptions;

import co.com.reports.model.report.enums.ErrorCodesEnum;
import lombok.Getter;

@Getter
public class BaseBusinessException extends RuntimeException {
    private final String code;

    public BaseBusinessException(ErrorCodesEnum errorCodesEnum) {
        super(errorCodesEnum.getDefaultMessage());
        this.code = errorCodesEnum.getCode();
    }
}

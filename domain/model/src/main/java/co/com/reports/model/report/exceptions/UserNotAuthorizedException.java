package co.com.reports.model.report.exceptions;

import co.com.reports.model.report.enums.ErrorCodesEnum;

public class UserNotAuthorizedException extends BaseBusinessException {
    public UserNotAuthorizedException() {
        super(ErrorCodesEnum.USER_NOT_AUTHORIZED_401);
    }
}

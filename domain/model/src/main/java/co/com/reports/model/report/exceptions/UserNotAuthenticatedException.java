package co.com.reports.model.report.exceptions;

import co.com.reports.model.report.enums.ErrorCodesEnum;

public class UserNotAuthenticatedException extends BaseBusinessException {
    public UserNotAuthenticatedException() {
        super(ErrorCodesEnum.USER_NOT_AUTHENTICATED_403);
    }
}

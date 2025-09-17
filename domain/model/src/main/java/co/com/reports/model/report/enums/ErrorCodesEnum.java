package co.com.reports.model.report.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodesEnum {
    USER_NOT_AUTHORIZED_401("USER_NOT_AUTHORIZED_401", "User not authorized to perform this operation"),
    USER_NOT_AUTHENTICATED_403("USER_NOT_AUTHENTICATED_403", "User must be authenticated");

    private final String code;
    private final String defaultMessage;
}

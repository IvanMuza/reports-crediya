package co.com.reports.api;

import co.com.reports.usecase.loansreport.GetApprovedLoansReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final GetApprovedLoansReportUseCase getApprovedLoansReportUseCase;

    public Mono<ServerResponse> listenGetApprovedLoans(ServerRequest request) {
        return getApprovedLoansReportUseCase.getReport()
                .flatMap(report -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(report));
    }
}

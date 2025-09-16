package co.com.reports.api;

import co.com.reports.usecase.loansreport.GetApprovedLoansReportUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class Handler {

//    private final GetApprovedLoansReportUseCase getApprovedLoansReportUseCase;
//
//    public Mono<ServerResponse> getApprovedLoans(ServerRequest request) {
//        return getApprovedLoansReportUseCase.getTotalApprovedLoans()
//                .flatMap(total -> ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .bodyValue(Map.of("totalApprovedLoans", total)));
//    }
}

package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetApprovedLoansReportUseCase {
    private final ApprovedLoansReportRepository approvedLoansReportRepository;

    public Mono<Long> getTotalApprovedLoans() {
        return approvedLoansReportRepository.findByMetric("report_approved")
                .map(ApprovedLoansReport::getValue)
                .switchIfEmpty(Mono.just(0L));
    }
}

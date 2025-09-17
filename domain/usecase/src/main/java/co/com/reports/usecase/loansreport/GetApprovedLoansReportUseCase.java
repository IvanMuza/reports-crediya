package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetApprovedLoansReportUseCase {
    private final ApprovedLoansReportRepository approvedLoansReportRepository;

    public Mono<ApprovedLoansReport> getReport() {
        return approvedLoansReportRepository.findByMetric("report_approved")
                .switchIfEmpty(Mono.just(
                        ApprovedLoansReport.builder()
                                .metric("report_approved")
                                .totalApprovedLoans(0L)
                                .amountTotalApprovedLoans(0.0)
                                .updatedAt(null)
                                .build()
                ));
    }
}

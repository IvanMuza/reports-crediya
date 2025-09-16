package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateApprovedLoansReportUseCase {
    private final ApprovedLoansReportRepository approvedLoansReportRepository;

    public Mono<ApprovedLoansReport> incrementCounter() {
        return approvedLoansReportRepository.findByMetric("report_approved")
                .defaultIfEmpty(ApprovedLoansReport.builder()
                        .metric("report_approved")
                        .value(0L)
                        .build())
                .flatMap(approvedLoansReport -> {
                    approvedLoansReport.setValue(approvedLoansReport.getValue() + 1);
                    return approvedLoansReportRepository.save(approvedLoansReport);
                });
    }
}

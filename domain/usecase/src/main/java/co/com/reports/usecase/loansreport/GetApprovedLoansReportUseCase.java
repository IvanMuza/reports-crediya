package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.Report;
import co.com.reports.model.report.gateways.ReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetApprovedLoansReportUseCase {
    private final ReportRepository reportRepository;

    public Mono<Long> getTotalApprovedLoans() {
        return reportRepository.findByMetric("total_approved")
                .map(Report::getValue)
                .switchIfEmpty(Mono.just(0L));
    }
}

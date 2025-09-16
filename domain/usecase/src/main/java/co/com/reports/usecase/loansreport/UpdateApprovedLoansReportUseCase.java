package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.Report;
import co.com.reports.model.report.gateways.ReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateApprovedLoansReportUseCase {
    private final ReportRepository reportRepository;

    public Mono<Report> incrementCounter() {
        return reportRepository.findByMetric("total_approved")
                .defaultIfEmpty(Report.builder()
                        .metric("total_approved")
                        .value(0L)
                        .build())
                .flatMap(report -> {
                    report.setValue(report.getValue() + 1);
                    return reportRepository.save(report);
                });
    }
}

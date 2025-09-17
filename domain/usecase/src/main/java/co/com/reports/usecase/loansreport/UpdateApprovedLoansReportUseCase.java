package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class UpdateApprovedLoansReportUseCase {
    private final ApprovedLoansReportRepository approvedLoansReportRepository;

    public Mono<ApprovedLoansReport> updateReport(Double approvedAmount) {
        return approvedLoansReportRepository.findByMetric("report_approved")
                .defaultIfEmpty(ApprovedLoansReport.builder()
                        .metric("report_approved")
                        .totalApprovedLoans(0L)
                        .amountTotalApprovedLoans(0.0)
                        .build())
                .flatMap(report -> {
                    report.setTotalApprovedLoans(report.getTotalApprovedLoans() + 1);
                    report.setAmountTotalApprovedLoans(
                            report.getAmountTotalApprovedLoans() + approvedAmount
                    );
                    report.setUpdatedAt(LocalDateTime.now());
                    return approvedLoansReportRepository.save(report);
                });
    }
}

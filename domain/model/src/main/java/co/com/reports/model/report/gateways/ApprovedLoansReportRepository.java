package co.com.reports.model.report.gateways;

import co.com.reports.model.report.ApprovedLoansReport;
import reactor.core.publisher.Mono;

public interface ApprovedLoansReportRepository {
    Mono<ApprovedLoansReport> findByMetric(String metric);
    Mono<ApprovedLoansReport> save(ApprovedLoansReport approvedLoansReport);
}

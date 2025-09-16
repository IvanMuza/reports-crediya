package co.com.reports.model.report.gateways;

import co.com.reports.model.report.Report;
import reactor.core.publisher.Mono;

public interface ReportRepository {
    Mono<Report> findByMetric(String metric);
    Mono<Report> save(Report report);
}

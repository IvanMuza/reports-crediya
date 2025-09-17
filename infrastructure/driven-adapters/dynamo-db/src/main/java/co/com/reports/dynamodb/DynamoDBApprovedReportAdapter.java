package co.com.reports.dynamodb;

import co.com.reports.dynamodb.helper.TemplateAdapterOperations;
import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;

@Repository
public class DynamoDBApprovedReportAdapter
        extends TemplateAdapterOperations<ApprovedLoansReport, String, ReportApprovedEntity>
implements ApprovedLoansReportRepository {

    public DynamoDBApprovedReportAdapter(DynamoDbEnhancedAsyncClient connectionFactory,
                                         ObjectMapper mapper) {
        super(
                connectionFactory,
                mapper,
                d -> mapper.map(d, ApprovedLoansReport.class),
                "report_approved"
        );
    }

    @Override
    public Mono<ApprovedLoansReport> findByMetric(String metric) {
        return this.getById(metric);
    }

    @Override
    public Mono<ApprovedLoansReport> save(ApprovedLoansReport report) {
        return super.save(report);
    }
}

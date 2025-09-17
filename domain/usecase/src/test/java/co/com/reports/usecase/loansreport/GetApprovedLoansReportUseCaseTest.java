package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class GetApprovedLoansReportUseCaseTest {
    private ApprovedLoansReportRepository approvedLoansReportRepository;
    private GetApprovedLoansReportUseCase getApprovedLoansReportUseCase;

    @BeforeEach
    void setUp() {
        approvedLoansReportRepository = Mockito.mock(ApprovedLoansReportRepository.class);
        getApprovedLoansReportUseCase = new GetApprovedLoansReportUseCase(approvedLoansReportRepository);
    }

    private ApprovedLoansReport buildReport() {
        return ApprovedLoansReport.builder()
                .metric("report_approved")
                .totalApprovedLoans(5L)
                .amountTotalApprovedLoans(10000.0)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldReturnReportWhenExists() {
        ApprovedLoansReport report = buildReport();
        when(approvedLoansReportRepository.findByMetric("report_approved"))
                .thenReturn(Mono.just(report));

        StepVerifier.create(getApprovedLoansReportUseCase.getReport())
                .expectNext(report)
                .verifyComplete();

        verify(approvedLoansReportRepository).findByMetric("report_approved");
    }

    @Test
    void shouldReturnDefaultReportWhenNotExists() {
        when(approvedLoansReportRepository.findByMetric("report_approved"))
                .thenReturn(Mono.empty());

        StepVerifier.create(getApprovedLoansReportUseCase.getReport())
                .expectNextMatches(report ->
                        report.getMetric().equals("report_approved") &&
                                report.getTotalApprovedLoans() == 0L &&
                                report.getAmountTotalApprovedLoans() == 0.0 &&
                                report.getUpdatedAt() == null
                )
                .verifyComplete();

        verify(approvedLoansReportRepository).findByMetric("report_approved");
    }
}

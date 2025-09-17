package co.com.reports.usecase.loansreport;

import co.com.reports.model.report.ApprovedLoansReport;
import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpdateApprovedLoansReportUseCaseTest {
    private ApprovedLoansReportRepository approvedLoansReportRepository;
    private UpdateApprovedLoansReportUseCase updateApprovedLoansReportUseCase;

    @BeforeEach
    void setUp() {
        approvedLoansReportRepository = Mockito.mock(ApprovedLoansReportRepository.class);
        updateApprovedLoansReportUseCase = new UpdateApprovedLoansReportUseCase(approvedLoansReportRepository);
    }

    private ApprovedLoansReport buildExistingReport() {
        return ApprovedLoansReport.builder()
                .metric("report_approved")
                .totalApprovedLoans(3L)
                .amountTotalApprovedLoans(5000.0)
                .updatedAt(LocalDateTime.now().minusDays(1))
                .build();
    }

    @Test
    void shouldUpdateExistingReport() {
        ApprovedLoansReport existing = buildExistingReport();
        when(approvedLoansReportRepository.findByMetric("report_approved"))
                .thenReturn(Mono.just(existing));
        when(approvedLoansReportRepository.save(any(ApprovedLoansReport.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(updateApprovedLoansReportUseCase.updateReport(2000.0))
                .assertNext(updated -> {
                    assertThat(updated.getTotalApprovedLoans()).isEqualTo(4L);
                    assertThat(updated.getAmountTotalApprovedLoans()).isEqualTo(7000.0);
                    assertThat(updated.getUpdatedAt()).isNotNull();
                })
                .verifyComplete();

        verify(approvedLoansReportRepository).save(any(ApprovedLoansReport.class));
    }

    @Test
    void shouldCreateNewReportWhenNotExists() {
        when(approvedLoansReportRepository.findByMetric("report_approved"))
                .thenReturn(Mono.empty());
        when(approvedLoansReportRepository.save(any(ApprovedLoansReport.class)))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(updateApprovedLoansReportUseCase.updateReport(1500.0))
                .assertNext(newReport -> {
                    assertThat(newReport.getTotalApprovedLoans()).isEqualTo(1L);
                    assertThat(newReport.getAmountTotalApprovedLoans()).isEqualTo(1500.0);
                    assertThat(newReport.getUpdatedAt()).isNotNull();
                })
                .verifyComplete();

        verify(approvedLoansReportRepository).save(any(ApprovedLoansReport.class));
    }
}

package co.com.reports.api.config;

import co.com.reports.model.report.gateways.ApprovedLoansReportRepository;
import co.com.reports.usecase.loansreport.GetApprovedLoansReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportUseCasesConfig {

    @Bean
    public GetApprovedLoansReportUseCase getApprovedLoansReportUseCase(
            ApprovedLoansReportRepository approvedLoansReportRepository
    ) {
        return new GetApprovedLoansReportUseCase(approvedLoansReportRepository);
    }
}

package co.com.reports.sqs.listener;

import co.com.reports.model.report.LoanApprovedEvent;
import co.com.reports.usecase.loansreport.UpdateApprovedLoansReportUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.function.Function;

@Log4j2
@Service
@RequiredArgsConstructor
public class SQSProcessor implements Function<Message, Mono<Void>> {
    private final ObjectMapper objectMapper;
    private final UpdateApprovedLoansReportUseCase updateApprovedLoansReportUseCase;

    @Override
    public Mono<Void> apply(Message message) {
        return Mono.fromCallable(() -> objectMapper.readValue(message.body(), LoanApprovedEvent.class))
                .flatMap(event -> updateApprovedLoansReportUseCase.updateReport(event.getAmount()))
                .doOnSuccess(r -> log.info("Updated ApprovedLoansReport with metric={} amount={}",
                        r.getMetric(), r.getAmountTotalApprovedLoans()))
                .doOnError(e -> log.error("Error processing SQS message body={}", message.body(), e))
                .then();
    }
}

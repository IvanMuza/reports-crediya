package co.com.reports.model.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovedEvent {
    Long loanId;
    String email;
    Double amount;
}

package co.com.reports.model.report;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApprovedLoansReport {
    private String metric;
    private Long totalApprovedLoans;
    private Double amountTotalApprovedLoans;
    private LocalDateTime updatedAt;
}

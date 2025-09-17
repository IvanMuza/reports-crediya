package co.com.reports.dynamodb;

import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

import java.time.LocalDateTime;

@Setter
@DynamoDbBean
public class ReportApprovedEntity {

    private String metric;
    private Long totalApprovedLoans;
    private Double amountTotalApprovedLoans;
    private LocalDateTime updatedAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("metric")
    public String getMetric() {
        return metric;
    }

    @DynamoDbAttribute("totalApprovedLoans")
    public Long getTotalApprovedLoans() {
        return totalApprovedLoans;
    }

    @DynamoDbAttribute("amountTotalApprovedLoans")
    public Double getAmountTotalApprovedLoans() {
        return amountTotalApprovedLoans;
    }

    @DynamoDbAttribute("updatedAt")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}

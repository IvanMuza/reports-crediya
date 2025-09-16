package co.com.reports.dynamodb;

import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@Setter
@DynamoDbBean
public class ReportApprovedEntity {

    private String metric;
    private Long value;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("metric")
    public String getMetric() {
        return metric;
    }

    @DynamoDbAttribute("value")
    public Long getValue() {
        return value;
    }

}

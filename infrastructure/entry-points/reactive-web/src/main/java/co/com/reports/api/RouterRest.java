package co.com.reports.api;

import co.com.reports.model.report.ApprovedLoansReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    @RouterOperation(
            path = "/api/v1/reports",
            beanClass = Handler.class,
            beanMethod = "listenGetApprovedLoans",
            operation = @Operation(
                    operationId = "listenGetApprovedLoans",
                    summary = "Get metrics of approved loans",
                    description = "\n" +
                            "The system displays an updated report showing the total number and total amount of all approved loans.",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Metrics successfully",
                                    content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApprovedLoansReport.class))),
                            @ApiResponse(responseCode = "500", description = "Internal server error",
                                    content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ApprovedLoansReport.class)))
                    }
            )
    )
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/v1/reports"), handler::listenGetApprovedLoans);
    }
}

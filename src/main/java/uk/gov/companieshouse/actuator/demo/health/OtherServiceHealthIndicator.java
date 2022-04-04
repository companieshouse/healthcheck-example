package uk.gov.companieshouse.actuator.demo.health;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriTemplate;
import uk.gov.companieshouse.actuator.demo.api.ApiClientService;
import uk.gov.companieshouse.actuator.demo.exception.ServiceException;
import uk.gov.companieshouse.api.ApiClient;
import uk.gov.companieshouse.api.error.ApiErrorResponseException;
import uk.gov.companieshouse.api.handler.companyaccount.CompanyAccountsResourceHandler;
import uk.gov.companieshouse.api.handler.companyaccount.request.CompanyAccountsGet;
import uk.gov.companieshouse.api.handler.exception.URIValidationException;
import uk.gov.companieshouse.api.model.accounts.CompanyAccountsApi;

@Component
public class OtherServiceHealthIndicator implements HealthIndicator {

    @Autowired
    private ApiClientService apiClientService;

    private static final UriTemplate COMPANY_ACCOUNTS_HEALTH =
        new UriTemplate("/healthcheck");

    @Override
    public Health health() {
        ApiClient apiClient = apiClientService.getApiClient();
        String uri = COMPANY_ACCOUNTS_HEALTH.expand().toString();
        Health.Builder status = Health.up();
        Map<String, Object> details = new HashMap<>();

        try {
            CompanyAccountsApi companyAccountsApi = apiClient.companyAccounts().get(uri).execute().getData();
        } catch (ApiErrorResponseException | URIValidationException e) {
            details.put("exception", e.getMessage());
            status = Health.down();
        }

        return status.withDetails(details).build();
    }
}

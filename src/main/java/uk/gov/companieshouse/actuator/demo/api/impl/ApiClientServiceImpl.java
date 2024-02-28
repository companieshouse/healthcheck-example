package uk.gov.companieshouse.actuator.demo.api.impl;

import org.springframework.stereotype.Component;
import uk.gov.companieshouse.actuator.demo.api.ApiClientService;
import uk.gov.companieshouse.api.ApiClient;
import uk.gov.companieshouse.sdk.manager.ApiClientManager;

@Component
public class ApiClientServiceImpl implements ApiClientService {

    @Override
    public ApiClient getApiClient() {
        return ApiClientManager.getSDK();
    }
}

package kr.co.study.bunjang.component.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzureInfo {

    public static final String DISCOVERY_URL = "https://login.microsoftonline.com/common/discovery/keys";

    public static final String LOGIN_URL = "https://login.microsoftonline.com";

    public static String TENANT_ID;

    @Value("${azure.tenant.id}")
    public void setTenantId(String tenantId) {
        AzureInfo.TENANT_ID = tenantId;
    }

    public static String CLIENT_ID;

    @Value("${azure.client.id}")
    public void setClientId(String ClientId) {
        AzureInfo.CLIENT_ID = ClientId;
    }

    public static String CLIENT_URL;

    @Value("${azure.client.url}")
    public void setClientUrl(String clientUrl) {
        AzureInfo.CLIENT_URL = clientUrl;
    }

    public static String CLIENT_SECRET;

    @Value("${azure.client.secret}")
    public void setClientSecret(String clientSecret) {
        AzureInfo.CLIENT_SECRET = clientSecret;
    }
}
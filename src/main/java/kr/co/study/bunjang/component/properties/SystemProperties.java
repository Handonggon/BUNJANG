package kr.co.study.bunjang.component.properties;

import org.springframework.beans.factory.annotation.Value;

public class SystemProperties {
    
    public static String FILE_BASE_UPLOAD_PATH;

    @Value("${system.file.baseUploadPath}")
    public void setTenantId(String fileBaseUploadPath) {
        SystemProperties.FILE_BASE_UPLOAD_PATH = fileBaseUploadPath;
    }
}
package kr.co.study.bunjang.component.utility;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import kr.co.study.bunjang.ApplicationContextServe;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class PropertyUtils {
	
    public String getProperty(String propertyName) {
    	return getProperty(propertyName, null);
    }
 
    public String getProperty(String propertyName, String defaultValue) {
        String value = defaultValue;
    	
        ApplicationContext applicationContext = ApplicationContextServe.getApplicationContext();
        if (applicationContext.getEnvironment().containsProperty(propertyName)) {
            value = applicationContext.getEnvironment().getProperty(propertyName).toString();
        } else {
            log.warn(propertyName + " properties was not loaded.");
        }
        return value;
    }

    public Set<String> getPropertySet(String propertyName) {
    	return getPropertySet(propertyName, new HashSet<String>());
    }

    public Set<String> getPropertySet(String propertyName, Set<String> defaultValue) {
        Set<String> value = defaultValue;
    	
        ApplicationContext applicationContext = ApplicationContextServe.getApplicationContext();
        int i = 0;
        if (applicationContext.getEnvironment().containsProperty(propertyName + "[" + i + "]")) {
            value = new HashSet<String>();
            do {
                value.add(applicationContext.getEnvironment().getProperty(propertyName + "[" + i++ + "]"));
            } while(applicationContext.getEnvironment().containsProperty(propertyName + "[" + i + "]"));
        } else {
            log.warn(propertyName + " properties was not loaded.");
        }
        return value;
    }
}
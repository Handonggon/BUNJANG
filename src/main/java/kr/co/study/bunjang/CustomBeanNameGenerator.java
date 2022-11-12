package kr.co.study.bunjang;

import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public class CustomBeanNameGenerator implements BeanNameGenerator  {

	private static final BeanNameGenerator DEFAULT_GENERATOR = new AnnotationBeanNameGenerator();

	@Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if(isTargetPackageBean(definition)) {
            return getFullName((AnnotatedBeanDefinition) definition);
        }
        return DEFAULT_GENERATOR.generateBeanName(definition, registry);
    }
	
	private boolean isTargetPackageBean(BeanDefinition definition) {
		if(definition instanceof AnnotatedBeanDefinition) {
            final Set<String> annotationTypes = ((AnnotatedBeanDefinition) definition).getMetadata().getAnnotationTypes();
            
            return annotationTypes.stream()
								  .filter(type->type.equals(Service.class.getName()) || type.equals(Controller.class.getName()))
								  .findAny()
								  .isPresent();
        }
        return false;
	}

	private String getFullName(final AnnotatedBeanDefinition definition) {
        return definition.getMetadata().getClassName();
    }
}
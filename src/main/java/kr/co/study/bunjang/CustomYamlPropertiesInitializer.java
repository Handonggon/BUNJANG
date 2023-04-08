package kr.co.study.bunjang;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomYamlPropertiesInitializer implements EnvironmentPostProcessor, Ordered {

	private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		log.info("** START CustomYamlPropertiesInitializer **");
		ResourceLoader resourceLoader = Optional
				.ofNullable(application.getResourceLoader())
				.orElseGet(DefaultResourceLoader::new);

		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(resourceLoader);

		Resource[] resources = new Resource[]{};
		try {
			resources = resourcePatternResolver.getResources("classpath*:config/*.yaml");
		} catch(IOException e){
			log.warn("{}", e.getMessage(), e);
		}

		String[] activeProfiles = environment.getActiveProfiles();
		for (String activeProfile : activeProfiles) {
			for (Resource resource : resources) {
				if (!resource.getFilename().contains(String.format("-%s.yaml", activeProfile))) continue;
				loadYaml(resource).forEach(propertySource->environment.getPropertySources().addLast(propertySource));
			}
		}
	}

	private List<PropertySource<?>> loadYaml(Resource path) {
		if (!path.exists()) {
			throw new IllegalArgumentException("Resource " + path + " does not exist");
		}
		try {
			return this.loader.load(path.getFilename(), path);
		} catch (IOException e) {
			throw new IllegalStateException("Failed to load yaml configuration from " + path, e);
		}
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
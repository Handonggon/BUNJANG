package kr.co.study.bunjang.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder().title("API")
			.description("API 가이드")
			.version("1.0")
			.build();
	}

	@Bean
	public Docket shop() {
		return new Docket(DocumentationType.SWAGGER_2)
			.groupName("Shop")
			.consumes(getConsumeContentTypes())
			.produces(getProduceContentTypes())
			//.globalOperationParameters()
			.apiInfo(swaggerInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("kr.co.study.bunjang.mvc.controller.v1"))
			.paths(PathSelectors.ant("/v1/shop/**"))
			.build()
			.useDefaultResponseMessages(false);
	}

	@Bean
	public Docket category() {
		return new Docket(DocumentationType.SWAGGER_2)
			.groupName("Category")
			.consumes(getConsumeContentTypes())
			.produces(getProduceContentTypes())
			//.globalOperationParameters()
			.apiInfo(swaggerInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("kr.co.study.bunjang.mvc.controller.v1"))
			.paths(PathSelectors.ant("/v1/category/**"))
			.build()
			.useDefaultResponseMessages(false);
	}








	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}
}

package kr.co.study.bunjang.mvc.batch.chunk.reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.study.bunjang.component.util.CommonMap;
import kr.co.study.bunjang.component.utility.ObjUtils;
import kr.co.study.bunjang.mvc.batch.item.ProductItem;

@Component
@StepScope
public class ProductReader implements ItemReader<ProductItem>, StepExecutionListener {

    private Iterator<ProductItem> products = Collections.emptyIterator();

    @Override
    public void beforeStep(StepExecution stepExecution) {
        WebClient webClient = WebClient.builder()
            .baseUrl("https://api.bunjang.co.kr")
            .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
                .build())
            .build();
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "1");
        params.add("size", "100");

        CommonMap responseBody = new CommonMap();
        responseBody.putAll(webClient.get()
                .uri(uriBuilder->uriBuilder.path("/api/rec/v1/products/personalized/main").queryParams(params).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response->{
                    return response.bodyToMono(CommonMap.class);
                }).block());

        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductItem> resultData = objectMapper.convertValue(ObjUtils.nvl(responseBody.getList("data"), new ArrayList<ProductItem>()), new TypeReference<List<ProductItem>>(){});
        products = resultData.iterator();
    }

    @Override
    public ProductItem read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (products.hasNext()) {
            return products.next();
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}


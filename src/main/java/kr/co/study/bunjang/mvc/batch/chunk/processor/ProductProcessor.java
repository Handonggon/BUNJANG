package kr.co.study.bunjang.mvc.batch.chunk.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import kr.co.study.bunjang.mvc.batch.item.ProductItem;
import kr.co.study.bunjang.mvc.domain.home.model.entity.Product;

@Component
@StepScope
public class ProductProcessor implements ItemProcessor<ProductItem, Product> {

    @Override
    public Product process(ProductItem item) throws Exception {
        return item.toEntity();
    }
}
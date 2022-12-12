package kr.co.study.bunjang.mvc.batch.chunk.writer;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import kr.co.study.bunjang.mvc.domain.home.model.entity.Product;

@Component
@StepScope
public class ProductWriter implements ItemWriter<Product> {

    @Override
    public void write(List<? extends Product> items) throws Exception {
        
    }
}
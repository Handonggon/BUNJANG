package kr.co.study.bunjang.mvc.vo;

import lombok.Getter;

@Getter
public enum AllYn {
    ALL("ALL"),
    Y("Y"),
    N("N");

    private String value;

    private AllYn(String value) {
        this.value = value;
    }
}
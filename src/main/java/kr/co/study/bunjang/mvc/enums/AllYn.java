package kr.co.study.bunjang.mvc.enums;

import lombok.Getter;

@Getter
public enum AllYn {
    ALL("ALL"),
    Y("Y"),
    N("N");

    private String useYn;

    AllYn(String useYn) {
        this.useYn = useYn;
    }
}

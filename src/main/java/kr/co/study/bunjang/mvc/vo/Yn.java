package kr.co.study.bunjang.mvc.vo;

import lombok.Getter;

@Getter
public enum Yn {
    Y('Y'),
    N('N');

    private Character value;

    private Yn(Character value) {
        this.value = value;
    }
}
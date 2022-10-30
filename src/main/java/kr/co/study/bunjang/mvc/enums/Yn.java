package kr.co.study.bunjang.mvc.enums;

import lombok.Getter;

@Getter
public enum Yn {
    Y('Y'),
    N('N');

    private Character useYn;

    Yn(Character useYn) {
        this.useYn = useYn;
    }
}

package ru.beorg.test.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Vlad on 05.02.2017.
 */
@Getter
public class PriceException extends RuntimeException {

    private Code code;

    public PriceException(Code code) {
        super(code.getMsg());
        this.code = code;
    }

    @Getter
    @AllArgsConstructor
    public enum Code {

        PRICE_NOT_IN_RANGE(1, "Price isn't in available range");


        private int code;
        private String msg;
    }
}

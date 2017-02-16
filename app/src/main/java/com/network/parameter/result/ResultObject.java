package com.network.parameter.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.network.parameter.BaseResult;


/**
 * Created by Administrator on 2016/4/8.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultObject<T> extends BaseResult {
    private T obj;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}

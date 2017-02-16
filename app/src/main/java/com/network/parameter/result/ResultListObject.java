package com.network.parameter.result;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.network.parameter.BaseResult;
import com.network.parameter.Paginator;

import java.io.Serializable;
import java.util.List;



/**
 * 返回多个对象
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultListObject<T> extends BaseResult implements Serializable {

    private List<T> list;
    private Paginator paginator;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public Paginator getPaginator() {
        return paginator;
    }

    @Override
    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
package org.hillel.homework.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest {
    private QueryType queryType;
    private int pageNumber;
    private int pageSize;
    private String orderFieldName;
    private boolean orderAsc;

    public int getFirstResult(){
        return (pageNumber - 1) * pageSize;
    }

    public String getOrderStr(){
        if (!StringUtils.isEmpty(orderFieldName)){
            return " order by " + orderFieldName + (orderAsc ? " asc" : " desc");
        }
        return "";
    }
}

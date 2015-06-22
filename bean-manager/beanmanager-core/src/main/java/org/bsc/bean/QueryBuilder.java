/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bsc.bean;

import org.bsc.bean.Functional.F;

/**
 *
 * @author softphone
 */
public class QueryBuilder {

    private boolean distinct = false;

    private String where; 

    private String groupBy;
    
    private String having; 
    private String orderBy; 
    private String limit;     


    public final QueryBuilder distinct() {
        this.distinct = true;
        return this;
    }

    public final QueryBuilder Where(String where) {
        if( where == null ) throw new IllegalArgumentException("where is null!");
        this.where = where;
        return this;
    }

    public final QueryBuilder groupBy(String groupBy) {
        if( groupBy == null ) throw new IllegalArgumentException("groupBy is null!");
        this.groupBy = groupBy;
        return this;
    }


    public final QueryBuilder having(String having) {
        if( having == null ) throw new IllegalArgumentException("having is null!");
        this.having = having;
        return this;
    }

    public final QueryBuilder orderBy(String orderBy) {
        if( orderBy == null ) throw new IllegalArgumentException("orderBy is null!");
        this.orderBy = orderBy;
        return this;
    }

    public final QueryBuilder limit(String limit) {
        if( limit == null ) throw new IllegalArgumentException("limit is null!");
        this.limit = limit;
        return this;
    }
    
    public String build(BeanManager bm) {
        if( bm == null ) throw new IllegalArgumentException("bm is null!");
        return "";
    }
    
    public <T> void buildAndExec(BeanManager bm, F<T> fetchResult ) {
        if( bm == null ) throw new IllegalArgumentException("bm is null!");
    }
    
}

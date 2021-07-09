package org.mountcloud.graphql.request.query;

import org.mountcloud.graphql.request.MultiGraphqlRequest;

/**
 * 2021/7/9. multi query 的总父类
 *
 * @author firstmetcs
 * @version V1.0
 */
public abstract class MultiGraphqlQuery extends MultiGraphqlRequest {

    @Override
    public String toString() {
        String superStr = super.toString();
        return "{\"query\":\"{" + superStr + "}\"}";
    }

}

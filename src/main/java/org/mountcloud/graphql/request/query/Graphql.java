package org.mountcloud.graphql.request.query;

import org.mountcloud.graphql.request.GraphqlRequest;
import org.mountcloud.graphql.request.param.RequestVariable;

public class Graphql extends GraphqlRequest {

    private String parms;
    private RequestVariable variable;

    public Graphql(String method) {
        super(method);
        this.parms = null;
        this.variable = null;
    }

    public Graphql(String method, String parms) {
        super(method);
        this.parms = parms;
        this.variable = null;
    }

    public void setVariable(RequestVariable variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        StringBuilder stringQuery;

        stringQuery = new StringBuilder();

        stringQuery
                .append("query");

        if(this.parms != null) {
            stringQuery
                    .append("(")
                    .append(this.parms)
                    .append(")");
        }

        stringQuery
                .append("{")
                .append(super.toString())
                .append("}");


        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("{ \"query\": \"")
                .append(stringQuery.toString())
                .append("\"");

        if(this.variable != null) {
            stringBuilder
                    .append(",\"variables\":\"")
                    .append(this.variable.toString())
                    .append("\"");
        }

        stringBuilder
                .append("}");

        return stringBuilder.toString();
    }
}


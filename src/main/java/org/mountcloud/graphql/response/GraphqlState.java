package org.mountcloud.graphql.response;

/**
 * @author zhanghaishan
 * @version V1.0
 * @Package com.ugirls.graphql.response
 * @Description: TODO
 * @date 2018/2/23.
 */
public enum  GraphqlState {

    SUCCESS(0),
    FAILED(-1);

    private int value;

    GraphqlState(int i) {
        value = i;
    }

    public int getValue(){
        return this.value;
    }

    public static GraphqlState getEnum(int i){
        switch (i){
            case 1:
                return FAILED;
                default:
                    return SUCCESS;
        }

    }
}

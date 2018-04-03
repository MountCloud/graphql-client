package org.mountcloud.graphql.response;

/**
 * 2018/2/23. 返回结果状态
 * @author zhanghaishan
 * @version V1.0
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

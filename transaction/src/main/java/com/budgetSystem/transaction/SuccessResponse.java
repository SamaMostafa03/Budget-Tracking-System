package com.budgetSystem.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {
    private Boolean success = true;
    private Object data;

    public SuccessResponse() {
        super();
        this.success = Boolean.TRUE;
        this.data="";
    }

    public SuccessResponse(Object data) {
        super();
        this.data = data;
        this.success = Boolean.TRUE;
    }

}

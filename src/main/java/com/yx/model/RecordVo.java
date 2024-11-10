package com.yx.model;

import lombok.Data;

@Data
public class RecordVo extends  Records{
    //户主
    private String username;
    //门牌号
    private String numbers;
    //费用类型
    private String typename;
}

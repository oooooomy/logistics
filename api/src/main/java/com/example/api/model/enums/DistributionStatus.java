package com.example.api.model.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * 配送单状态
 */
public enum DistributionStatus {

    //正在审核
    REVIEWING(0),

    //审核成功
    REVIEW_SUCCESS(1),

    //审核失败
    REVIEW_FAILED(2),

    //订单完成
    END(3);

    @Getter
    @Setter
    private Integer code;

    DistributionStatus(Integer code) {
        this.code = code;
    }

}

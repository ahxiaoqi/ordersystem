package com.ordersystem.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ahxiaoqi
 * @date 2020/3/23 0:07
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class productBuyDto {
    private Integer productId;
    private Integer specId;
    private Integer type;
    private Integer count;

}

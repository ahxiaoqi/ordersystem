package com.ordersystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author ahxiaoqi
 * @date 2019/12/28 10:33
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@TableName("t_account")
public class Account {

    @TableId(type = IdType.AUTO,value = "accountId")
    private Integer accountId;

    @NotEmpty(message = "用户名不能为空!")
    @TableField(value = "userName")
    private String userName;

    @NotEmpty(message = "密码不能为空!")
    @TableField(value = "passWord")
    private String passWord;

    @NotEmpty(message = "昵称不能为空!")
    @TableField(value = "nickName")
    private String nickName;

    @TableField(value = "createTime")
    private String createTime;

    @TableField(value = "accountType")
    private Integer accountType;

    @TableField(value = "headImg")
    private String headImg;
}

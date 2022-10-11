package com.dy.redispractice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyang
 * @date 2022/10/11 19:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userCode;

    private String userName;
}

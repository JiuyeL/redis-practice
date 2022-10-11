package com.dy.redispractice.chapter.chapter1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongyang
 * @date 2022/10/11 19:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private String id;
    private String title;
    private long time;
    private double score;

}

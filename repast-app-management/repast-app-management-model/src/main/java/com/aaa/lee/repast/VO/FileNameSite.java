package com.aaa.lee.repast.VO;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author 丁平达
 * @Date 2020/3/17 22:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
public class FileNameSite implements Serializable {
    private String filenameSite;
    private Boolean ifSuccess;
}

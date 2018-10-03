package com.vasiliyplatonov.splatwork.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Banner {
    private int id;
    private int width;
    private int height;
    private String imgSrc;
    private String targetUrl;
    private String langId;


}

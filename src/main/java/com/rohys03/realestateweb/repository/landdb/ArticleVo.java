package com.rohys03.realestateweb.repository.landdb;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
public class ArticleVo {
    private String clct_dt;
    private String complexNo;
    private String articleNo;
    private String tradeTypeName;
    private String floorInfo;
    private String dealOrWarrantPrc;
    private String areaName;
    private String area1;
    private String area2;
    private String buildingName;
}

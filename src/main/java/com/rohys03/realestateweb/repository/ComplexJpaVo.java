package com.rohys03.realestateweb.repository;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="realestate_apt_complex")
public class ComplexJpaVo {

    @Id
    @Column(name="complexNo")
    private int complexNo;

    private String complexName;

    private String complexTypeName;
    private String totalHouseHoldCount;
    private String totalDongCount;
    private String constructYearMonth;
    private String minArea;
    private String maxArea;
}

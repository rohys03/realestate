package com.rohys03.realestateweb.repository.landdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplexVo {

    public ComplexVo(int complexNo, String complexName) {
        this.complexNo = complexNo;
        this.complexName = complexName;
    }

    private int complexNo;

    private String complexName;

    private String complexTypeName;
    private String totalHouseHoldCount;
    private String totalDongCount;
    private String constructYearMonth;
    private String minArea;
    private String maxArea;
}

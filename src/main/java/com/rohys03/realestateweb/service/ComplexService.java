package com.rohys03.realestateweb.service;

import com.rohys03.realestateweb.repository.landdb.ComplexVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ComplexService {

    @Autowired
    @Resource(name="db1JdbcTemplate")
    private JdbcTemplate db1Template;

    public List<ComplexVo> getRealestate_apt_complex() {
        String sql = "select * From landcrawler.realestate_apt_complex";
        return db1Template.query(sql, new BeanPropertyRowMapper(ComplexVo.class));
    }

    public List<ComplexVo> getRealestate_apt_complex(String complexNm) {
        String searchString = '%'+complexNm+'%';
        String sql = "select * From landcrawler.realestate_apt_complex WHERE complexName LIKE ?";

        return db1Template.query(sql, new Object[] {searchString}, new BeanPropertyRowMapper(ComplexVo.class));
    }

}

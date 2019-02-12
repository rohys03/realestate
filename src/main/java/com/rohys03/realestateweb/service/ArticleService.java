package com.rohys03.realestateweb.service;

import com.rohys03.realestateweb.repository.landdb.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    @Resource(name="db1JdbcTemplate")
    private JdbcTemplate db1Template;

    public List<ArticleVo> getRealestateArticleByClctDt(String complexNo, String clctDt) {

        String sql = "select * From realestate_article_link_esxi where complexNo = ? and clct_dt = ?";

        return db1Template.query(sql, new Object[] {complexNo, clctDt}, new BeanPropertyRowMapper(ArticleVo.class));
    }
    public List<ArticleVo> getRealestateArticleByClctDtByArea(String complexNo, String clctDt, String area) {

        String sql = "select * From realestate_article_link_esxi where complexNo = ? and clct_dt = ? and area2 = ?";

        return db1Template.query(sql, new Object[] {complexNo, clctDt, area}, new BeanPropertyRowMapper(ArticleVo.class));
    }

}
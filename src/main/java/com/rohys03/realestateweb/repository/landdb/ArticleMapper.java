package com.rohys03.realestateweb.repository.landdb;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Mapper
@Resource(name="db1SessionFactory")
public interface ArticleMapper {

    List<ArticleVo> getArticleList();
    List<ArticleVo> getArticleListByArticleName(String articleName);
    List<ArticleVo> getArticleListByArticleNo(int articleNo);
}
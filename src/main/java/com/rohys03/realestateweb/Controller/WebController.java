package com.rohys03.realestateweb.Controller;

import com.rohys03.realestateweb.repository.landdb.ArticleVo;
import com.rohys03.realestateweb.repository.landdb.ComplexVo;
import com.rohys03.realestateweb.service.ArticleService;
import com.rohys03.realestateweb.service.ComplexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class WebController {

    @Autowired
    ComplexService complexService;

    @Autowired
    ArticleService articleService;

    private String getCurrentTime() {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);

        String formattedDate = dateFormat.format(date);

        return formattedDate;

    }

    @RequestMapping(value = {"", "index"})
    public String index(Model model) {

        model.addAttribute("age", 19);
        model.addAttribute("serverTime", getCurrentTime());

        return "content/dashboard";
    }

    @RequestMapping("complexList")
    public String complexList(HttpServletRequest request, Model model) {

        String searchString = request.getParameter("searchString");
        System.out.println("/articleList/searchString:"+ searchString);

        List<ComplexVo> complexes = null;

//        System.out.println("/ComplexList/"+ searchString);

        if (searchString != null) {
            complexes = complexService.getRealestate_apt_complex(searchString);
        } else {
            complexes = complexService.getRealestate_apt_complex();
        }

        model.addAttribute("serverTime", getCurrentTime());
        model.addAttribute("complexes", complexes);
        return "content/complexList";
    }

    @RequestMapping("articleList")
    public String articleList(HttpServletRequest request, Model model) {
        String complexNo = request.getParameter("complexNo");
        String clctDt = request.getParameter("clctDt");
        String area = request.getParameter("area");
        System.out.println("/articleList/complexNo:"+ complexNo + "/clctDt:"+ clctDt + "/area:"+ area);

        List<ArticleVo> articles = null;
        if (clctDt != null && complexNo != null) {
            articles = articleService.getRealestateArticleByClctDt(complexNo, clctDt);
        } else {
            articles = null;
        }

        model.addAttribute("articles", articles);
        model.addAttribute("serverTime", getCurrentTime());

        return "content/articleList";
    }

}

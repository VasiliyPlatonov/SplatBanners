package com.vasiliyplatonov.splatwork.controller;

import com.vasiliyplatonov.splatwork.dao.impls.BannerJdbcDAO;
import com.vasiliyplatonov.splatwork.domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BannerController {

    @Autowired
    private BannerJdbcDAO bannerDAO;

    @GetMapping("/banners/{id}")
    public ModelAndView getBanner(@PathVariable Integer id, ModelAndView modelAndView) {
        Banner banner = bannerDAO.findOne(id);
        modelAndView.setViewName("banners");
        modelAndView.addObject("banner", banner);
        return modelAndView;
    }
}

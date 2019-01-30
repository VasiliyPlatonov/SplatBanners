package com.vasiliyplatonov.splatwork.controller;

import com.vasiliyplatonov.splatwork.dao.impls.BannerJdbcDAO;
import com.vasiliyplatonov.splatwork.domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("banner") /*todo: Change "banner" to "banners". That will be more correctly in according to REST approach*/
public class BannerController {

    /*TODO: Explore service level, approach to work with it  */
    @Autowired
    private BannerJdbcDAO bannerDAO;

    @GetMapping
    public List<Banner> getAllBanners() {
        return bannerDAO.findAll();
    }

    @GetMapping("{id}")
    public Banner getBanner(@PathVariable("id") Integer id) {
        return bannerDAO.findOne(id);
    }

    @PostMapping
    public Banner createBanner(@RequestBody Banner banner) {
        return bannerDAO.save(banner);
    }

    @DeleteMapping("{id}")
    public void deleteBanner(@PathVariable("id") Integer id) {
        bannerDAO.delete(id);
    }
}

package com.vasiliyplatonov.splatwork.controller;

import com.vasiliyplatonov.splatwork.dao.exceptions.BannerJdbcDAOException;
import com.vasiliyplatonov.splatwork.dao.impls.BannerJdbcDAO;
import com.vasiliyplatonov.splatwork.domain.Banner;
import com.vasiliyplatonov.splatwork.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("banner") /*todo: Change "banner" to "banners". That will be more correctly in according to REST approach*/
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping
    public List<Banner> getAllBanners() {
        return bannerService.getAllBanners();
    }

    @GetMapping("{id}")
    public Banner getBanner(@PathVariable("id") Integer id) {
        return bannerService.getBanner(id);
    }

    @PostMapping
    public Banner createBanner(@RequestBody Banner banner) {
        return bannerService.createBanner(banner);
    }

    @DeleteMapping("{id}")
    public void deleteBanner(@PathVariable("id") Integer id) {
        bannerService.deleteBanner(id);
    }

    @PutMapping("{id}")
    public Banner update(@RequestBody Banner banner) throws BannerJdbcDAOException {
        return bannerService.update(banner);
    }
}

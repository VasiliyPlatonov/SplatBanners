package com.vasiliyplatonov.splatwork.service;

import com.vasiliyplatonov.splatwork.dao.exceptions.BannerJdbcDAOException;
import com.vasiliyplatonov.splatwork.dao.impls.BannerJdbcDAO;
import com.vasiliyplatonov.splatwork.domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    @Autowired
    private BannerJdbcDAO bannerDAO;

    public Banner getBanner(int id) {
        return bannerDAO.findOne(id);
    }

    public List<Banner> getAllBanners() {
        return bannerDAO.findAll();
    }

    public void deleteBanner(int id) {
        bannerDAO.delete(id);
    }

    public Banner createBanner(Banner banner) {
        return bannerDAO.save(banner);
    }

    public Banner update(Banner banner) throws BannerJdbcDAOException {
        bannerDAO.update(banner);
        return bannerDAO.findOne(banner.getId());
    }

}

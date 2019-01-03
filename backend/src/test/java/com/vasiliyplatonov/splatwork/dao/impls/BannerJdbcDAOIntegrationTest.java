package com.vasiliyplatonov.splatwork.dao.impls;

import com.vasiliyplatonov.splatwork.domain.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
//@Sql("/sql/create-test-schema.sql")
@Sql(value = "/sql/create-banner-test-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/insert-banner-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql/delete-banner-test-table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BannerJdbcDAOIntegrationTest {

    @Autowired
    private BannerJdbcDAO bannerDAO;

    @Test
    public void exists() {
        //  positive test
        int id = 2;
        boolean result = bannerDAO.exists(id);
        assertThat(result).isTrue();

        // negative test
        id = 5;
        result = bannerDAO.exists(id);
        assertThat(result).isFalse();
    }

    @Test
    public void findAll() {
        List<Banner> bannerList = (ArrayList<Banner>) bannerDAO.findAll();
        ArrayList<Integer> bannerIds = new ArrayList<>(4);
        bannerList.forEach(banner -> bannerIds.add(banner.getId()));

        //  positive test
        assertThat(bannerIds).containsExactlyInAnyOrder(1, 2, 3, 4);

        // negative test
        assertThat(bannerIds).doesNotContain(5, 0, 12);
    }

    @Test
    public void findAllById() {
        List<Integer> existsIds = Arrays.asList(2, 3, 4);
        List<Banner> bannerList = (ArrayList<Banner>) bannerDAO.findAll(existsIds);
        ArrayList<Integer> bannerIds = new ArrayList<>(4);
        bannerList.forEach(banner -> bannerIds.add(banner.getId()));

        //  positive test
        assertThat(bannerList).isNotNull();
        assertThat(bannerIds).containsExactlyInAnyOrder(2, 3, 4);

        // negative test
        assertThat(bannerIds).doesNotContain(1, 5);
    }

    @Test
    public void save() throws Exception {
        Banner banner = new Banner();
        banner.setWidth(200);
        banner.setHeight(500);
        banner.setImgSrc("imgSRC from save test banner");
        banner.setTargetUrl("targetUrl from save test banner");
        banner.setLangId("en");

        assertThat(bannerDAO.count()).isEqualTo(4);
        bannerDAO.save(banner);
        assertThat(bannerDAO.count()).isEqualTo(5);
        assertThat(bannerDAO.exists(banner.getId())).isTrue();
    }

    @Test
    public void saveList() throws Exception {
        ArrayList<Banner> banners = new ArrayList<>();
        int countOfNewBanners = 5;

        for (int i = 0; i < countOfNewBanners; i++) {
            Banner banner = new Banner();
            banner.setWidth(i * 10);
            banner.setHeight(i * 50);
            banner.setImgSrc("imgSRC from save test banner " + i + "th");
            banner.setTargetUrl("targetUrl from save test banner " + i + "th");
            banner.setLangId("en");
            banners.add(banner);
        }

        assertThat(bannerDAO.count()).isEqualTo(4);
        bannerDAO.save(banners);
        assertThat(bannerDAO.count()).isEqualTo(4 + countOfNewBanners);
    }

    @Test
    public void deleteById() throws Exception {
        final int id = 3;
        boolean exists = bannerDAO.exists(id);
        assertThat(exists).isTrue();

        bannerDAO.delete(id);
        exists = bannerDAO.exists(id);
        assertThat(exists).isFalse();
    }

    @Test
    public void deleteByObj() throws Exception {
    }

    @Test
    public void deleteListObj() throws Exception {
    }


}
package com.vasiliyplatonov.splatwork.dao.impls;

import com.vasiliyplatonov.splatwork.dao.interfaces.BannerDAO;
import com.vasiliyplatonov.splatwork.domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BatchUpdateUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BannerJdbcDAO implements BannerDAO<Banner, Integer> {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert bannerInsert;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.bannerInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("banner")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Banner save(Banner banner) {

        SqlParameterSource params = new BeanPropertySqlParameterSource(banner);
        Number newId = bannerInsert.executeAndReturnKey(params);
        banner.setId(newId.intValue());

        return banner;
    }

    @Override
    public int save(List<Banner> banners) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(banners.toArray());
        return bannerInsert.executeBatch(batch).length;
    }

    @Override
    public Banner findOne(Integer id) {
        String sql = "SELECT id, height, width, imgSrc, langId, targetUrl FROM banner WHERE id=:banner_id";
        MapSqlParameterSource params = new MapSqlParameterSource("banner_id", id);

        return jdbcTemplate.queryForObject(sql, params, new BannerMapper());
    }

    @Override
    public boolean exists(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        String sql = "SELECT count(id) FROM banner WHERE id=:banner_id";
        MapSqlParameterSource params = new MapSqlParameterSource("banner_id", id);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);

        if (count != null && count > 0) return true;
        else return false;
    }

    @Override
    public List<Banner> findAll() {
        String sql = "SELECT id, height, width, imgSrc, langId, targetUrl FROM banner";

        return jdbcTemplate.query(sql, new BannerMapper());
    }

    @Override
    public List<Banner> findAll(List<Integer> ids) {
        String sql = "SELECT id, height, width, imgSrc, langId, targetUrl FROM banner WHERE id IN (:banner_ids)";

        MapSqlParameterSource params = new MapSqlParameterSource("banner_ids", ids);
        return jdbcTemplate.query(sql, params, new BannerMapper());
    }

    @Override

    public int count() {
        String sql = "SELECT count(*) FROM banner";
        Integer result = jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
        if (result == null) {
            result = 0;
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM banner WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(Banner banner) {
        delete(banner.getId());
    }

    @Override
    public void delete(List<? extends Banner> banners) {
        List<Integer> bannerIds = new ArrayList<>();
        banners.forEach(banner -> bannerIds.add(banner.getId()));

        String sql = "DELETE FROM banner WHERE id IN (:banner_ids)";

        MapSqlParameterSource params = new MapSqlParameterSource("banner_ids", bannerIds);
        jdbcTemplate.query(sql, params, new BannerMapper());
    }

    private static final class BannerMapper implements RowMapper<Banner> {
        @Override
        public Banner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Banner banner = new Banner();
            banner.setId(rs.getInt("id"));
            banner.setWidth(rs.getInt("width"));
            banner.setHeight(rs.getInt("height"));
            banner.setImgSrc(rs.getString("imgSrc"));
            banner.setTargetUrl(rs.getString("targetUrl"));
            banner.setLangId(rs.getString("langId"));
            return banner;
        }
    }

}

package com.vasiliyplatonov.splatwork.dao.impls;

import com.vasiliyplatonov.splatwork.dao.exceptions.BannerJdbcDAOException;
import com.vasiliyplatonov.splatwork.dao.interfaces.BannerDAO;
import com.vasiliyplatonov.splatwork.domain.Banner;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
    public Banner save(@NonNull Banner banner) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(banner);
        Number newId = bannerInsert.executeAndReturnKey(params);
        banner.setId(newId.intValue());

        return banner;
    }

    @Override
    public int save(@NonNull List<Banner> banners) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(banners.toArray());
        return bannerInsert.executeBatch(batch).length;
    }

    @Override
    public Banner findOne(@NonNull Integer id) {
        String sql = "SELECT id, height, width, imgSrc, langId, targetUrl FROM banner WHERE id=:banner_id";
        MapSqlParameterSource params = new MapSqlParameterSource("banner_id", id);

        return jdbcTemplate.queryForObject(sql, params, new BannerMapper());
    }

    @Override
    public boolean exists(@NonNull Integer id) {
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
    public List<Banner> findAll(@NonNull List<Integer> ids) {
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
    public void delete(@NonNull Integer id) {
        String sql = "DELETE FROM banner WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(@NonNull Banner banner) {
        delete(banner.getId());
    }

    @Override
    public void delete(@NonNull List<? extends Banner> banners) {
        List<Integer> bannerIds = new ArrayList<>();
        banners.forEach(banner -> bannerIds.add(banner.getId()));

        String sql = "DELETE FROM banner WHERE id IN (:banner_ids)";

        MapSqlParameterSource params = new MapSqlParameterSource("banner_ids", bannerIds);
        jdbcTemplate.query(sql, params, new BannerMapper());
    }

    @Override
    public void update(@NonNull Banner banner) throws BannerJdbcDAOException {
        String sql =
                "UPDATE banner " +
                        "SET width = :width, " +
                        "height = :height, " +
                        "imgSrc = :imgSrc, " +
                        "targetUrl = :targetUrl, " +
                        "langId = :langId " +
                        "WHERE id = :id";


        SqlParameterSource params = new BeanPropertySqlParameterSource(banner);
        if (jdbcTemplate.update(sql, params) == 0)
            throw new BannerJdbcDAOException("There is an exception while trying to update the banner." +
                    " Expected the number of rows affected is more then 1 but actual is 0.");
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

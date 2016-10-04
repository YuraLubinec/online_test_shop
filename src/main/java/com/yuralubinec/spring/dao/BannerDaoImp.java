package com.yuralubinec.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.yuralubinec.spring.model.Banner;

/**
 * BannerDaoImp is the {@link BannnerDao} implementation for CRUD operations on
 * {@link Banner} instance in the database
 *
 */

@Repository
public class BannerDaoImp extends AbstractDao<Integer, Banner> implements BannerDao {

    /**
     * Finds {@link Banner} in DB by banner id
     * 
     * @param id of the Banner instance stored in DB
     * @return Banner object
     * @throws DataAccessException
     */
    @Override
    public Banner findBannerById(int id) {

        return getById(id);
    }

    /**
     * Finds all {@link Banner} in DB
     * 
     * @return List of Banners
     * @throws DataAccessException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Banner> getAllBanners() {

        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("id"));
        return (List<Banner>) crit.list();
    }

    /**
     * Saves {@link Banner} to DB
     * 
     * @param Banner instance which should be saved to DB
     * @throws DataAccessException
     */
    @Override
    public void saveBanner(Banner banner) {

        persist(banner);
    }

    /**
     * Deletes {@link Banner} from DB
     * 
     * @param id of the Banner instance stored in DB
     * @throws DataAccessException
     */
    @Override
    public void deleteBanner(int id) {

        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Banner banner = (Banner) crit.uniqueResult();
        delete(banner);
    }
}

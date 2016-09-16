package com.yuralubinec.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.yuralubinec.spring.model.Banner;

@Repository
public class BannerDaoImp extends AbstractDao<Integer, Banner> implements BannerDao {

    @Override
    public Banner findBannerById(int id) {

        return getById(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Banner> getAllBanners() {

        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.desc("id"));
        return (List<Banner>) crit.list();
    }

    @Override
    public void deleteBanner(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Banner banner = (Banner) crit.uniqueResult();

        delete(banner);
    }

    @Override
    public void saveBanner(Banner banner) {
        persist(banner);
        
    }

}

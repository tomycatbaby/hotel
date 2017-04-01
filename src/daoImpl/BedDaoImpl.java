package daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.BedDao;
import entity.Bed;


public class BedDaoImpl extends HibernateDaoSupport implements BedDao {

	@Override
	public void saveBed(Bed bed) {
		this.getHibernateTemplate().save(bed);

	}

	@Override
	public void updateBed(Bed bed) {
		this.getHibernateTemplate().update(bed);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bed> findAllBeds() {
		String hql = "from Bed bed order by bed.id desc";
		return (List<Bed>) this.getHibernateTemplate().find(hql);
	}

}

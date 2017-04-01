package serviceImpl;

import java.util.Iterator;
import java.util.List;

import dao.BedDao;
import entity.Bed;
import service.BedService;

public class BedServiceImpl implements BedService {
	private BedDao bedDao;
	public BedDao getBedDao() {
		return bedDao;
	}

	public void setBedDao(BedDao bedDao) {
		this.bedDao = bedDao;
	}

	@Override
	public void save(Bed bed) {
		this.bedDao.saveBed(bed);

	}

	@Override
	public void update(Bed bed) {
		this.bedDao.updateBed(bed);

	}

	@Override
	public Bed findBedById(String bedId) {
		Bed bed=null;
		List<Bed> beds = this.bedDao.findAllBeds();
		Iterator<Bed> it = beds.iterator();
		while(it.hasNext()){
			bed = it.next();
			if(bed.getBedId().equals(bedId));
				break;
		}
		return bed;
		
	}

	@Override
	public List<Bed> findAllBeds() {
		
		return (List<Bed>)this.bedDao.findAllBeds();
	}

}

package dao;

import java.util.List;

import entity.Bed;



public interface BedDao {
	public void saveBed(Bed bed);
	public void updateBed(Bed bed);
	public List<Bed> findAllBeds();
}

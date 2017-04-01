package service;

import java.util.List;

import entity.Bed;

public interface BedService {
	public void save(Bed bed);
	public void update(Bed bed);
	public Bed findBedById(String bedId);
	public List<Bed> findAllBeds();
}

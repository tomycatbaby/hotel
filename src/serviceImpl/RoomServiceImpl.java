package serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.RoomDao;
import entity.Room;
import service.RoomService;

public class RoomServiceImpl implements RoomService {
	private RoomDao roomDao;

	public RoomDao getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	@Override
	public void save(Room room) {
		this.roomDao.saveRoom(room);
	}

	@Override
	public void update(Room room) {
		this.roomDao.updateRoom(room);
	}

	@Override
	public List<Room> findAllRooms() {

		return (List<Room>) this.roomDao.findAllRooms();
	}

	@Override
	public List<Room> findRoomByDirection(String direction) {
		Room room =null;
		List<Room> rooms = new ArrayList();
		List<Room> rs = this.roomDao.findAllRooms();
		Iterator<Room> it = rs.iterator();
		while(it.hasNext()){
			room = it.next();
			if(room.getDirection().equals(direction)){
				rooms.add(room);
			}
		}
		return rooms;
	}

}

package dao;

import java.util.List;


import entity.Room;

public interface RoomDao {
	public void saveRoom(Room room);
	public void updateRoom(Room room);
	public List<Room> findAllRooms();
}

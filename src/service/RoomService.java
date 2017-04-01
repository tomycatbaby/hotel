package service;

import java.util.List;

import entity.Room;

public interface RoomService {
 public void save(Room room );
 public void update(Room room);
 public List<Room> findAllRooms(); 
 public List<Room> findRoomByDirection(String direction);
}

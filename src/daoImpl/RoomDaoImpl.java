package daoImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.RoomDao;

import entity.Room;

public class RoomDaoImpl extends HibernateDaoSupport implements RoomDao {

	@Override
	public void saveRoom(Room room) {
		this.getHibernateTemplate().save(room);

	}

	@Override
	public void updateRoom(Room room) {
		this.getHibernateTemplate().update(room);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAllRooms() {
		String hql = "from Room room order by room.id desc";
		return (List<Room>) this.getHibernateTemplate().find(hql);
	}

}

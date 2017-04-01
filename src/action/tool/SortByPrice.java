package action.tool;

import java.util.Comparator;

import entity.Room;

public class SortByPrice implements Comparator<Room>{

	@Override
	public int compare(Room o1, Room o2) {
		// TODO Auto-generated method stub
		return o1.getPrice()-o2.getPrice();
	}

}

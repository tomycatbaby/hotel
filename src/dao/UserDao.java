package dao;

import java.util.List;

import entity.User;

public interface UserDao {
	public void saveUser(User user);
	public List<User> findAllUsers();
	public void removeUser(User user);
	public void updateUser(User user);
	public User findUserById(Integer id);
	public User loginUser(User user);
}

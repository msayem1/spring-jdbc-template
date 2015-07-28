package com.msayem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.msayem.entity.User;
import com.msayem.dao.UserDao;

/**
 * User DAO Implementation class.
 * 
 * @author MSAYEM
 * 
 */
public class UserDaoImpl implements UserDao {

	private JdbcTemplate jdbcTemplate;

	public UserDaoImpl(DataSource dataSource) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addUser(User user) {
		
		String sql = "INSERT INTO user (userid, username, password, email)"
				+ " VALUES (?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, user.getUserid(), user.getUsername(),
				user.getPassword(), user.getEmail());
	}

	@Override
	public List<User> listUser() {

		String sql = "SELECT * FROM user";

		List<User> listUser = jdbcTemplate.query(sql, new RowMapper<User>() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User aContact = new User();

				aContact.setUserid(rs.getInt("userid"));
				aContact.setUsername(rs.getString("username"));
				aContact.setPassword(rs.getString("password"));
				aContact.setEmail(rs.getString("email"));

				return aContact;
			}
		});

		return listUser;
	}

	@Override
	public User getUserById(Integer userId) {

		String sql = "SELECT * FROM user WHERE userid=" + userId;
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					
					User contact = new User();
					contact.setUserid(rs.getInt("userid"));
					contact.setUsername(rs.getString("username"));
					contact.setPassword(rs.getString("password"));
					contact.setEmail(rs.getString("email"));

					return contact;
				}

				return null;
			}
		});
	}

	@Override
	public void updateUser(User user) {

		String sql = "UPDATE user SET userid=?, username=?, password=?, "
			+ "email=? WHERE userid=?";
		
		jdbcTemplate.update(sql, user.getUserid(), user.getUsername(),
		user.getPassword(), user.getEmail(), user.getUserid());
	}

 	@Override
 	public void deleteUser(Integer id) {
 		
 		String sql = "DELETE FROM user WHERE userid=?";
 		jdbcTemplate.update(sql, id);
 	}
}
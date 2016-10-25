package form.dao.impl;

import form.dao.AbstractDao;
import form.dao.UserDao;
import form.model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository("userDao")
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserDaoImpl extends AbstractDao implements UserDao {


	@Override
	public User findById(Integer id) {

		Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));

		return (User) criteria.uniqueResult();
	}

	@Override
	public List<User> findAll() {

		Criteria criteria = getSession().createCriteria(User.class);
		return (List<User>) criteria.list();

	}

	@Override
	public void save(User user) {
		persist(user);
		
	}

	@Override
	public void update(User user) {

		getSession().update(user);

	}

	@Override
	public void delete(Integer id) {

		Query query = getSession().createSQLQuery("delete from USERS where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();

	}

	private SqlParameterSource getSqlParameterByModel(User user) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("newsletter", user.isNewsletter());

		paramSource.addValue("sex", user.getSex());
		paramSource.addValue("number", user.getNumber());
		paramSource.addValue("country", user.getCountry());


		return paramSource;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setAddress(rs.getString("address"));
			user.setCountry(rs.getString("country"));
			user.setNewsletter(rs.getBoolean("newsletter"));
			user.setNumber(rs.getInt("number"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getString("sex"));
			return user;
		}
	}


}
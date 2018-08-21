package com.lidong.spring.pratice4;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class CityDaoImpl {
	private JdbcTemplate jt;

	public void setJt(JdbcTemplate jt) {
		this.jt = jt;
	}
	
	public boolean dropTableIfExist() {
		String sql = "drop table if exists CityInfo";
		int result = jt.update(sql);
		return result == 1;
	}

	public boolean createCityTable() {
		String sql = "CREATE TABLE IF NOT EXISTS CityInfo ( "
		        +"id INT NOT NULL AUTO_INCREMENT, "
		        +"pid INT NOT NULL, "
		        +"name VARCHAR(40) NOT NULL, "
		        +"PRIMARY KEY ( id ))ENGINE=InnoDB DEFAULT CHARSET=utf8; ";
		int result = jt.update(sql);
		return result == 1;
	}
	
	/**
	 * JdbcTemplate 将连接数据库执行添加操作的流程 封装在update()中 增删改都是使用update方法
	 */
	public boolean addCity(City city) {
		String sql = " insert into CityInfo values(?,?,?)";
		Object[] args = {  city.getId(), city.getPid(), city.getName() };
		int result = jt.update(sql, args); // result 执行该操作影响的数据量
		return result == 1; // 影响一条 则添加成功
	}

	public boolean deleteCity(Integer id) {
		String sql = "delete from CityInfo where id=" + id;
		int result = jt.update(sql, id);
		return result == 1;
	}

	public boolean updateCity(City city) {
		String sql = "update CityInfo set pid=? , name=?  where id = ?";
		Object[] args = { city.getPid(), city.getName(), city.getId() };
		int result = jt.update(sql, args);
		return result == 1;
	}

	/**
	 * 查询单条数据 在使用queryForMap()查询单条数据时， 必须能够保证传入sql可以并且只能查询一条数据，否则会抛异常
	 */
	public Map selectCityById(Integer id) {
		String sql = "select * from CityInfo where id=" + id;
		Map map = jt.queryForMap(sql); // jdbc技术并非orm工具，并不能把直接查出来的关系型数据封装到对象，只能封装到map中
		// key 字段名 value 字段值
		return map;
	}

	/**
	 * 查询批量数据
	 */
	public List selectAllCitys() {
		String sql = "select * from CityInfo";
		List list = jt.queryForList(sql);
		return list;
	}

	/**
	 * 查询数据量
	 */
	public int selectCityCount() {
		// String sql = "select count(*) from CityInfo";
		// return jt.quer(sql);
		return selectAllCitys().size();
	}

	/**
	 * 其他查询
	 */
	public List selectCityByPage(int start, int end) {
		String sql = "select * from CityInfo limit ?,?";
		Object[] args = { start, end };
		return jt.queryForList(sql, args);
	}
}

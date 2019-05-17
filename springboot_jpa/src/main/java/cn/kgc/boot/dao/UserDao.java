package cn.kgc.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.kgc.boot.entity.User;

// User：操作的数据表实体类
// Long：主键类型
public interface UserDao extends JpaRepository<User, Long>{

	@Query("from User u where u.cellphone=:cellphone")
	public User findUserByCellphone(@Param("cellphone")String cellphone) throws Exception;

}

package org.androidpn.server.dao;

import java.util.List;

import org.androidpn.server.model.User;


/**
 * 
 * UserMapper数据库操作接口类
 * 
 */

public interface ApnUserMapper{


	/**
	 * 
	 * 添加
	 * 
	 **/
	 public void save( User user);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	 public void saveBySelective( User user);

	/**
	 * 
	 * 批量新增
	 * 
	 **/
	 public void saveBatchUser(List<User> list);

	/**
	 * 
	 * 主键ID修改 
	 * 
	 **/
	 public int updateByPrimaryKey ( User user );

	/**
	 * 
	 * 删除 
	 * 
	 **/
	 public void delByPrimaryKey (Long id );


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	 public User  selectByPrimaryKey (Long id );

	/**
	 * 
	 * 多条数据
	 * 
	 **/
	 public List<User>  queryList (User user);

	/**
	 * 
	 * 分页查询
	 * 
	 **/
	 public List<User>  queryPageList(User user);

	/**
	 * 
	 * 分页查询
	 * 
	 **/
	 public Integer  queryPageCount (User user);

}
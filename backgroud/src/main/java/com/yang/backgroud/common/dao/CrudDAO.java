package com.yang.backgroud.common.dao;

import java.util.List;

public interface CrudDAO<T> extends BaseDAO {
	public T find(Long id);

	public T findSelective(T entity);

	public List<T> findList(T entity);
	
	public int getCount(T entity);

	public int insert(T entity);
	
	public int insertSelective(T entity);

	public int update(T entity);
	
	public int updateSelective(T entity);

	public int delete(Long id);
	
	public int deleteSelective(T entity);
}

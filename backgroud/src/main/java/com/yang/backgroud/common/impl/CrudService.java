package com.yang.backgroud.common.impl;


import com.yang.backgroud.common.dao.CrudDAO;
import com.yang.backgroud.common.persistence.BaseEntity;
import com.yang.backgroud.common.persistence.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDAO<T>, T extends BaseEntity<T>> extends BaseService {
	@Autowired
	protected ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	protected D dao;

	public T get(Long id) {
		return dao.find(id);
	}

	public T get(T entity) {
		return dao.findSelective(entity);
	}

	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	public Pages<T> findPage(Pages<T> pages, T entity) {
		entity.setPages(pages);
		pages.setRows(dao.findList(entity));
		return pages;
	}

	@Transactional(readOnly = false)
	public int save(T entity) {
		return dao.insert(entity);
	}

	@Transactional(readOnly = false)
	public int update(T entity) {
		return dao.update(entity);
	}

	@Transactional(readOnly = false)
	public int delete(Long id) {
		return dao.delete(id);
	}
}

package com.yang.backgroud.common.persistence;


import java.util.Date;

public abstract class BaseDataEntity<T> extends BaseEntity<T> {
	private static final long serialVersionUID = -8891256380181811704L;

	public BaseDataEntity() {
		super();
	}

	public BaseDataEntity(Long id) {
		super(id);
	}

	@Override
	public void preInsert() {
//		String user = String.valueOf(UserUtils.getUser().getId());
//		super.setCreateBy(user);
//		super.setUpdateBy(user);
//		super.setCreateTime(new Date());
//		super.setUpdateTime(new Date());
	}

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate() {
//		String user = String.valueOf(UserUtils.getUser().getId());
//		super.setUpdateBy(user);
//		super.setUpdateTime(new Date());
	}
}
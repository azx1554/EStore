package com.yang.background.common.impl;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseService {
	protected static final Logger logger = Logger.getLogger(BaseService.class);

}

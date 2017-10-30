package com.yang.background.common.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {
	protected static final Logger logger = Logger.getLogger(BaseController.class);
}
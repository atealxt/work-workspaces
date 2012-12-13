package com.easyjf.simpleblog.dao;

import org.springframework.test.jpa.AbstractJpaTests;


public abstract class JpaDaoTest extends AbstractJpaTests {

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:application.xml" };
	}

}

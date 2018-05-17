package com.siaam.auth.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * BaseDao class for all Dao class for this project.
 *
 * @author Kapil Pruthi
 *
 */


public abstract class BaseDaoImpl {

	/**
	 * Spring jdbcTemplate object.
	 */
	private JdbcTemplate jdbcTemplate;

    /**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param inJdbcTemplate the jdbcTemplate to set.
	 */
	public void setJdbcTemplate(final JdbcTemplate inJdbcTemplate) {
		this.jdbcTemplate = inJdbcTemplate;
	}

	/**
     * Set a datasource to JDBC template.
     * @param dataSource DataSource
     */
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

}

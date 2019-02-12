package com.obido.configuration;

import org.springframework.orm.jpa.JpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;

interface EnvironmentConfiguration {

    JpaVendorAdapter jpaVendorAdapter();

    DataSource dataSource() throws SQLException;
}

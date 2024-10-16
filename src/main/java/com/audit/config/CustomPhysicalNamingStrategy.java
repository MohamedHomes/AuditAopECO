package com.audit.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

@SuppressWarnings("serial")
public class CustomPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

	private final static String databasePrefix = "AUDT_RW.";

	@Override
	public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertToSnakeCase(identifier);
	}

	private Identifier convertToSnakeCase(final Identifier identifier) {
		if (identifier == null)
			return null;
		final String newName = databasePrefix + identifier.getText();
		return Identifier.toIdentifier(newName);
	}
}
/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2011, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.engine.jdbc.spi;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.hibernate.ScrollMode;

/**
 * Contracting for preparing SQL statements
 *
 * @author Steve Ebersole
 * @author Brett Meyer
 */
public interface StatementPreparer {
	/**
	 * Create a statement.
	 *
	 * @return the statement
	 */
	public Statement createStatement();
	
	/**
	 * Prepare a statement.
	 *
	 * @param sql The SQL the statement to be prepared
	 *
	 * @return the prepared statement
	 */
	public PreparedStatement prepareStatement(String sql);

	/**
	 * Prepare a statement.
	 *
	 * @param sql The SQL the statement to be prepared
	 * @param isCallable Whether to prepare as a callable statement.
	 *
	 * @return the prepared statement
	 */
	public PreparedStatement prepareStatement(String sql, boolean isCallable);

	/**
	 * Prepare an INSERT statement, specifying how auto-generated (by the database) keys should be handled.  Really this
	 * is a boolean, but JDBC opted to define it instead using 2 int constants:<ul>
	 *     <li>{@link PreparedStatement#RETURN_GENERATED_KEYS}</li>
	 *     <li>{@link PreparedStatement#NO_GENERATED_KEYS}</li>
	 * </ul>
	 * Generated keys are accessed afterwards via {@link java.sql.PreparedStatement#getGeneratedKeys}
	 *
	 * @param sql The INSERT SQL
	 * @param autoGeneratedKeys The autoGeneratedKeys flag
	 *
	 * @return the prepared statement
	 *
	 * @see java.sql.Connection#prepareStatement(String, int)
	 */
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys);

	/**
	 * Prepare an INSERT statement, specifying columns which are auto-generated values to be returned.
	 * Generated keys are accessed afterwards via {@link java.sql.PreparedStatement#getGeneratedKeys}
	 *
	 * @param sql - the SQL for the statement to be prepared
	 * @param columnNames The name of the columns to be returned in the generated keys result set.
	 *
	 * @return the prepared statement
	 *
	 * @see java.sql.Connection#prepareStatement(String, String[])
	 */
	public PreparedStatement prepareStatement(String sql, String[] columnNames);

	/**
	 * Get a prepared statement for use in loading / querying.
	 *
	 * @param sql The SQL the statement to be prepared
	 * @param isCallable Whether to prepare as a callable statement.
	 * @param scrollMode (optional) scroll mode to be applied to the resulting result set; may be null to indicate
	 * no scrolling should be applied.
	 *
	 * @return the prepared statement
	 */
	public PreparedStatement prepareQueryStatement(String sql, boolean isCallable, ScrollMode scrollMode);
}
package org.zk.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.zk.enums.BaseEnum;
import org.zk.enums.UserStatus;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@MappedTypes({UserStatus.class})
public class EnumCodeTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

	private Class<E> type;

	public EnumCodeTypeHandler(Class<E> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getCode());
	}

	@Override
	public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		if (null == rs.getString(columnName) && rs.wasNull()) {
			return null;
		}
		return getEnumByCode(rs.getInt(columnName));
	}

	@Override
	public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		if (null == rs.getString(columnIndex) && rs.wasNull()) {
			return null;
		}
		return getEnumByCode(rs.getInt(columnIndex));
	}

	@Override
	public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		if (null == cs.getString(columnIndex) && cs.wasNull()) {
			return null;
		}
		return getEnumByCode(cs.getInt(columnIndex));
	}

	private BaseEnum getEnumByCode(int enumCode) {
		E[] es = type.getEnumConstants();
		for (E e : es) {
			if (Objects.equals(e.getCode(), enumCode)) {
				return e;
			}
		}
		return null;
	}
}

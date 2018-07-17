package com.lifesciences.api.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lifesciences.api.constants.AppConstants;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

@SuppressWarnings("rawtypes")
public class DoctorRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(rs.getInt(AppConstants.DOC_ID));
        doctor.setName(StringUtils.trimWhitespace(rs.getString(AppConstants.DOC_NM)));
		return doctor;
	}
}
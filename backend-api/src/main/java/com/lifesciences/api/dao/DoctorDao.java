package com.lifesciences.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifesciences.api.constants.AppConstants;
import com.lifesciences.api.model.Doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Configuration
public class DoctorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Doctor> getDoctorsDetails(int docId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_doctor_details");

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("doc_id", docId);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);

		return (List<Doctor>) simpleJdbcCallResult.get(AppConstants.RESULT_LIST);
	}

}
package com.lifesciences.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lifesciences.api.constants.AppConstants;
import com.lifesciences.api.model.Doctor;
import com.lifesciences.api.model.DoctorRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Configuration
public class DoctorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Doctor> getDoctorsDetails(int docId) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("get_doctor_details")
				.declareParameters(new SqlReturnResultSet(AppConstants.RESULT_LIST, new DoctorRowMapper()));

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("doc_id", docId);
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);

		return (List<Doctor>) simpleJdbcCallResult.get(AppConstants.RESULT_LIST);
	}

	public List<Doctor> getDoctors() {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("get_doctors")
				.declareParameters(new SqlReturnResultSet(AppConstants.RESULT_LIST, new DoctorRowMapper()));

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute();
		System.out.println(simpleJdbcCallResult);

		return (List<Doctor>) simpleJdbcCallResult.get(AppConstants.RESULT_LIST);
	}

	public void createDoctor(Doctor doc) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("upsert_doc");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put(AppConstants.OPERATION, AppConstants.INSERT);
			inParamMap.put(AppConstants.IN_DOC_ID, 0);
			inParamMap.put(AppConstants.IN_DOC_NAME, doc.getName());
			inParamMap.put(AppConstants.IN_DOC_BLDG, doc.getBuilding());
			inParamMap.put(AppConstants.IN_DOC_CITY, doc.getCity());
			inParamMap.put(AppConstants.IN_DOC_DEGREE, doc.getDegree());
			inParamMap.put(AppConstants.IN_DOC_SPECIALITY, doc.getSpeciality());
			inParamMap.put(AppConstants.IN_DOC_STREET, doc.getStreet());
			inParamMap.put(AppConstants.IN_DOC_ZIP, doc.getZip());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);
	}

	public void updateDoctor(Doctor doc) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("upsert_doc");
		
		Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put(AppConstants.OPERATION, AppConstants.UPDATE);
			inParamMap.put(AppConstants.IN_DOC_ID, doc.getId());
			inParamMap.put(AppConstants.IN_DOC_NAME, doc.getName());
			inParamMap.put(AppConstants.IN_DOC_BLDG, doc.getBuilding());
			inParamMap.put(AppConstants.IN_DOC_CITY, doc.getCity());
			inParamMap.put(AppConstants.IN_DOC_DEGREE, doc.getDegree());
			inParamMap.put(AppConstants.IN_DOC_SPECIALITY, doc.getSpeciality());
			inParamMap.put(AppConstants.IN_DOC_STREET, doc.getStreet());
			inParamMap.put(AppConstants.IN_DOC_ZIP, doc.getZip());
		
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);
	}

}
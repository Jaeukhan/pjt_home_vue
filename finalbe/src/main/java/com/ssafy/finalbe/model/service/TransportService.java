package com.ssafy.finalbe.model.service;

import java.sql.SQLException;
import java.util.List;


import com.ssafy.finalbe.model.TransportDto;

public interface TransportService {
	public List<TransportDto> getList(int sidocode) throws SQLException;
}

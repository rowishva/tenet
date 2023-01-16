package com.tenet.web.rest.admin.service;

import javax.servlet.http.HttpServletResponse;

public interface ReportService {

	public void generateExcel(HttpServletResponse response, Long massTimeId);

}

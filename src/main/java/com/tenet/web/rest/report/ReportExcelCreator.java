package com.tenet.web.rest.report;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tenet.web.rest.admin.dto.ReportMassBookingDTO;

public class ReportExcelCreator {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private Map<String, List<ReportMassBookingDTO>> reportMassBookingDTOMap;
	private String title;

	public ReportExcelCreator(Map<String, List<ReportMassBookingDTO>> reportMassBookingDTOMap, String title) {
		this.reportMassBookingDTOMap = reportMassBookingDTOMap;
		workbook = new XSSFWorkbook();
		this.title = title;
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Report");
		// Title
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(14);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillBackgroundColor(IndexedColors.YELLOW1.getIndex());
		//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFont(font);
		createCell(row, 0, "Registration Name List - " + title, style);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		sheet.setHorizontallyCenter(true);

		// Headers
		row = sheet.createRow(3);
		CellStyle styleHeader = workbook.createCellStyle();
		XSSFFont fontHeader = workbook.createFont();
		fontHeader.setBold(true);
		fontHeader.setFontHeight(12);
		styleHeader.setFont(fontHeader);
		styleHeader.setBorderBottom(BorderStyle.THIN);
		styleHeader.setBorderLeft(BorderStyle.THIN);
		styleHeader.setBorderRight(BorderStyle.THIN);
		styleHeader.setBorderTop(BorderStyle.THIN);

		createCell(row, 0, "No", styleHeader);
		createCell(row, 1, "MassBookingNo", styleHeader);
		createCell(row, 2, "Name of Person", styleHeader);
		createCell(row, 3, "Seating No (Seating Prefix - Seat No)", styleHeader);
		createCell(row, 4, "Category", styleHeader);
		createCell(row, 5, "CarPark Allocation", styleHeader);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 4;
		int count = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		style.setFont(font);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		
		Iterator<Map.Entry<String, List<ReportMassBookingDTO>>> iterator = reportMassBookingDTOMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, List<ReportMassBookingDTO>> entry = iterator.next();
			String massBookingNo = entry.getKey();
			List<ReportMassBookingDTO> reportMassBookingDTOs = entry.getValue();
			Row row = sheet.createRow(rowCount);
			int rowStart = rowCount;
			int rowEnd = rowCount + reportMassBookingDTOs.size() - 1;
			rowCount++;
			int columnCount = 0;
			createCell(row, columnCount++, count++, style);
			createCell(row, columnCount++, massBookingNo, style);
			int listCount = 0;
			for (ReportMassBookingDTO reportMassBookingDTO : reportMassBookingDTOs) {
				columnCount = 2;
				if (listCount > 0)
					row = sheet.createRow(rowCount++);
				createCell(row, columnCount++, reportMassBookingDTO.getFullName(), style);
				createCell(row, columnCount++, reportMassBookingDTO.getPrefixSeatNo(), style);
				createCell(row, columnCount++, reportMassBookingDTO.getTagDescription(), style);
				createCell(row, columnCount++, reportMassBookingDTO.getCarParkAllocation(), style);
				listCount++;
			}
			sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, 0, 0));
			sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, 1, 1));
		}

	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}

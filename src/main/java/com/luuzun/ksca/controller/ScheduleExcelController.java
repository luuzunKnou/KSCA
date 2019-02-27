package com.luuzun.ksca.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Cat1HasCat2;
import com.luuzun.ksca.domain.ExcelOutput;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.service.Cat1Service;
import com.luuzun.ksca.service.ScheduleService;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleExcelController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ScheduleExcelController.class);
	
	@Inject	private ScheduleService scheduleService;
	@Inject	private Cat1Service cat1Service;

	private HSSFWorkbook objWorkBook;

	@RequestMapping(value="/ExcelPoi")
	public String ExcelPoi(HttpServletResponse response, HttpSession session, RedirectAttributes rttr, 
			String regMonth) throws Exception {

		logger.info("Excel Download..........");
		Manager manager = (Manager) session.getAttribute("login");
		
		//권한 확인
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/"; 
		}
		String areaCode = manager.getArea();
		
		objWorkBook = new HSSFWorkbook(); //파일 obj 생성
		HSSFSheet objSheet = null;		  //Sheet obj 생성
		HSSFRow objRow = null;			  //Row obj 생성
		HSSFCell objCell = null;    	  //셀 obj 생성 
	
		objSheet = objWorkBook.createSheet("Sheet1");     //워크시트 생성
		
		objSheet.addMergedRegion(new CellRangeAddress( //Merge cell
				0, //startRowIndx 
				0, //endRowIndx 
				0, //startColIndx
				14 //endColIndx
		));

		//타이틀 스타일
			HSSFFont font_title = objWorkBook.createFont();
			font_title.setFontName("맑은 고딕");				//글자체
			font_title.setFontHeightInPoints((short)11);		//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_title = objWorkBook.createCellStyle();   	  	//스타일
			style_title.setFont(font_title);									//폰트 적용
			style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  	//가운데 정렬
			style_title.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//세로 가운데 정렬
			style_title.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_title.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_title.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				
				
		//Table header 스타일 bg gray25
			HSSFFont font_th1 = objWorkBook.createFont();
			font_th1.setFontName("맑은 고딕");				//글자체
			font_th1.setFontHeightInPoints((short)11);		//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_th1 = objWorkBook.createCellStyle();   	  	//스타일
			style_th1.setFont(font_th1);									//폰트 적용
			style_th1.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  	//가운데 정렬
			style_th1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); //세로 가운데 정렬
			style_th1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  //배경색 gray 25%
			style_th1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//배경색 적용
			style_th1.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_th1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_th1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_th1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	
			
		//Tabel header 스타일 2 bg gray40
			HSSFFont font_th2 = objWorkBook.createFont();
			font_th2.setFontName("맑은 고딕");				//글자체
			font_th2.setFontHeightInPoints((short)11);		//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_th2 = objWorkBook.createCellStyle();   	  		//스타일
			style_th2.setFont(font_th2);										//폰트 적용
			style_th2.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  		//가운데 정렬
			style_th2.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//세로 가운데 정렬
			style_th2.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index); 	//배경색 gray 40%
			style_th2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//배경색 적용
			style_th2.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_th2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_th2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_th2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
		//Content 스타일 1 (align center)
			HSSFFont font_cont1 = objWorkBook.createFont();
			font_cont1.setFontName("맑은 고딕");			//글자체
			font_cont1.setFontHeightInPoints((short)11);	//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_cont1 = objWorkBook.createCellStyle();   	  	//스타일
			style_cont1.setFont(font_cont1);									//폰트 적용
			style_cont1.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  	//가운데 정렬
			style_cont1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//세로 가운데 정렬
			style_cont1.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_cont1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		//Content 스타일 2 (align left)
			HSSFFont font_cont2 = objWorkBook.createFont();
			font_cont2.setFontName("맑은 고딕");			//글자체
			font_cont2.setFontHeightInPoints((short)11);	//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_cont2 = objWorkBook.createCellStyle();   	  	//스타일
			style_cont2.setFont(font_cont2);									//폰트 적용
			style_cont2.setAlignment(HSSFCellStyle.ALIGN_LEFT);			  		//왼쪽 정렬
			style_cont2.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//세로 가운데 정렬
			style_cont2.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_cont2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		//Content 스타일 3 (align center, bg tan)
			HSSFFont font_cont3 = objWorkBook.createFont();
			font_cont3.setFontName("맑은 고딕");			//글자체
			font_cont3.setFontHeightInPoints((short)11);	//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_cont3 = objWorkBook.createCellStyle();   	  	//스타일
			style_cont3.setFont(font_cont3);									//폰트 적용
			style_cont3.setAlignment(HSSFCellStyle.ALIGN_LEFT);			  		//가운데 정렬
			style_cont3.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//세로 가운데 정렬
			style_cont3.setFillForegroundColor(HSSFColor.TAN.index);		 	//배경색 TAN
			style_cont3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//배경색 적용
			style_cont3.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_cont3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont3.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
		//Content 스타일 4 (align center, bg PALE_BLUE)
			HSSFFont font_cont4 = objWorkBook.createFont();
			font_cont4.setFontName("맑은 고딕");			//글자체
			font_cont4.setFontHeightInPoints((short)11);	//글자 크기
				
			//제목 스타일에 폰트 적용, 정렬
			HSSFCellStyle style_cont4 = objWorkBook.createCellStyle();   	  	//스타일
			style_cont4.setFont(font_cont4);									//폰트 적용
			style_cont4.setAlignment(HSSFCellStyle.ALIGN_LEFT);			  		//가운데 정렬
			style_cont4.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//세로 가운데 정렬
			style_cont4.setFillForegroundColor(HSSFColor.PALE_BLUE.index);		//배경색 PALE_BLUE			
			style_cont4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//배경색 적용
			style_cont4.setBorderRight(HSSFCellStyle.BORDER_THIN);	//테두리
			style_cont4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont4.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
		//first row
		objRow = objSheet.createRow(0);
		objCell = objRow.createCell(0);
		objCell.setCellValue("경로당별 프로그램 운영");
		objCell.setCellStyle(style_title);
		
		// second row
		String titleList[] = {"번호","연합회","지회","경로당코드","경로당명",
				"프로그램코드1","프로그램명1","프로그램코드2","프로그램명2",
				"프로그램명","기간시작일","기간종료일","이용자수","제공자(기관,단체)",
				"월운영횟수"
		};
		
		String catList[] = {"프로그램코드1","코드","프로그램코드2","코드"};
		
		
		objRow = objSheet.createRow(1);
		
		//1~15 scc th
		for (int i=0; i<titleList.length; i++) {
			objCell = objRow.createCell(i);
			objCell.setCellValue(titleList[i]);
			objCell.setCellStyle(style_th1);
		}
		
		//16~20 cat th
		for (int i=0; i<catList.length; i++) {
			objCell = objRow.createCell(i+16);
			objCell.setCellValue(catList[i]);
			objCell.setCellStyle(style_th2);
		}
		
		// get scc and program date from database
		List<ExcelOutput> dataList = scheduleService.excelOutput(areaCode, regMonth);
		if(dataList.size()==0) {
			rttr.addFlashAttribute("msg","데이터가 존재하지 않습니다.");
			return "redirect:/";
		}
		
		List<List<String>> strList = new ArrayList<List<String>>();
		
		int idx = 0;
		for (ExcelOutput e : dataList) {
			List<String> addList = new ArrayList<String>();

			addList.add(idx+1+""); //번호
			addList.add(e.getScc().getAreaCode().substring(0,2));		//연합회
			addList.add(e.getScc().getAreaCode().split("-")[0]+e.getScc().getAreaCode().split("-")[1]);		//지회
			addList.add(e.getScc().getAreaCode()+"-"+e.getScc().getBranchCode()+"-"+e.getScc().getSccCode());	//경로당코드
			addList.add(e.getScc().getName());							//경로당명
			addList.add(e.getCat1().getCode());						//프로그램코드1
			addList.add(e.getCat1().getName());						//프로그램명1
			addList.add(e.getCat1().getCode()+e.getCat2().getCode());	//프로그램코드2
			addList.add(e.getCat2().getName());						//프로그램명2
			addList.add(e.getProgram().getName());						//프로그램명
			addList.add(e.getOfferprogram().getSimpleBeginDate());		//기간시작일
			addList.add(e.getOfferprogram().getSimpleEndDate());		//기간종료일
			addList.add(e.getOffer().getActiveUser()+"");				//이용자수
			addList.add(e.getagency().getName());						//제공자(기관,단체)
			addList.add(e.getOffer().getMonthlyOper()+"");				//월운영횟수
			
			strList.add(addList);
			
			idx++;
		}
	
		// input data
		for (int j=0; j<strList.size(); j++) {
			objRow = objSheet.createRow(j+2);
			objRow.setHeight ((short) 0x150);
			for (int i=0; i<strList.get(0).size(); i++) {
				objCell = objRow.createCell(i);
				objCell.setCellValue(strList.get(j).get(i));
				
				if(i<=1) { //1,2번째 값은 가운데정렬
					objCell.setCellStyle(style_cont1);
				} else {
					objCell.setCellStyle(style_cont2);
				}
			}
		}

		//input category
		List<Cat1HasCat2> catDataList = cat1Service.listCat1HasCat2();
		if(catDataList.size()==0) {
			rttr.addFlashAttribute("msg","카테고리가 존재하지 않습니다.");
			return "redirect:/"; 			
		}
		
		if(catDataList.get(0).getCat2List().size()==0) {
			rttr.addFlashAttribute("msg","카테고리가 존재하지 않습니다.");
			return "redirect:/"; 
		}
		
		int colCnt = titleList.length+1;
		int rowCnt = 2;
		
		for (int i=0; i<catDataList.size(); i++) { //cat1 row 출력
			
			if(objSheet.getRow(rowCnt)==null) { //row가 없다면 새로만듦.
				objRow = objSheet.createRow(rowCnt);
			} else {
				objRow = objSheet.getRow(rowCnt);
			}
			
			objCell = objRow.createCell(colCnt);
			objCell.setCellValue(catDataList.get(i).getName());
			objCell.setCellStyle(style_cont3);
			
			objCell = objRow.createCell(colCnt+1);
			objCell.setCellValue(catDataList.get(i).getCode());
			objCell.setCellStyle(style_cont3);

			for(int j=0; j<catDataList.get(i).getCat2List().size(); j++) { //cat2 row 출력
				if(objSheet.getRow(rowCnt)==null) { //row가 없다면 새로만듦.
					objRow = objSheet.createRow(rowCnt);
				} else {
					objRow = objSheet.getRow(rowCnt);
				}
				
				objCell = objRow.createCell(colCnt+2);
				objCell.setCellValue(catDataList.get(i).getCat2List().get(j).getName());
				objCell.setCellStyle(style_cont4);

				objCell = objRow.createCell(colCnt+3);
				objCell.setCellValue(catDataList.get(i).getCode()+catDataList.get(i).getCat2List().get(j).getCode());
				objCell.setCellStyle(style_cont4);

				rowCnt++;
			}
		}
		
		
		//autuSizeColumn after setColumnWidth setting
		for (int i=0;i<titleList.length+catList.length+1;i++)  
		{ 
			objSheet.autoSizeColumn(i);
			objSheet.setColumnWidth(i, (objSheet.getColumnWidth(i))+512 ); 
		}
		
		//title
		String fileName = "경로당별 프로그램운영";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replaceAll("\\+", "%20");

		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "ATTachment; Filename="
				+fileName+".xls");
	
		//output
		OutputStream fileOut  = response.getOutputStream();
		objWorkBook.write(fileOut);
		fileOut.close();
	
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		return fileName;
	}
}

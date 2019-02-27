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
		
		//���� Ȯ��
		if(manager==null) {
			rttr.addFlashAttribute("msg","������ �����ϴ�.");
			return "redirect:/"; 
		}
		String areaCode = manager.getArea();
		
		objWorkBook = new HSSFWorkbook(); //���� obj ����
		HSSFSheet objSheet = null;		  //Sheet obj ����
		HSSFRow objRow = null;			  //Row obj ����
		HSSFCell objCell = null;    	  //�� obj ���� 
	
		objSheet = objWorkBook.createSheet("Sheet1");     //��ũ��Ʈ ����
		
		objSheet.addMergedRegion(new CellRangeAddress( //Merge cell
				0, //startRowIndx 
				0, //endRowIndx 
				0, //startColIndx
				14 //endColIndx
		));

		//Ÿ��Ʋ ��Ÿ��
			HSSFFont font_title = objWorkBook.createFont();
			font_title.setFontName("���� ���");				//����ü
			font_title.setFontHeightInPoints((short)11);		//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_title = objWorkBook.createCellStyle();   	  	//��Ÿ��
			style_title.setFont(font_title);									//��Ʈ ����
			style_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  	//��� ����
			style_title.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//���� ��� ����
			style_title.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_title.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_title.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				
				
		//Table header ��Ÿ�� bg gray25
			HSSFFont font_th1 = objWorkBook.createFont();
			font_th1.setFontName("���� ���");				//����ü
			font_th1.setFontHeightInPoints((short)11);		//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_th1 = objWorkBook.createCellStyle();   	  	//��Ÿ��
			style_th1.setFont(font_th1);									//��Ʈ ����
			style_th1.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  	//��� ����
			style_th1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); //���� ��� ����
			style_th1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  //���� gray 25%
			style_th1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//���� ����
			style_th1.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_th1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_th1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_th1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	
			
		//Tabel header ��Ÿ�� 2 bg gray40
			HSSFFont font_th2 = objWorkBook.createFont();
			font_th2.setFontName("���� ���");				//����ü
			font_th2.setFontHeightInPoints((short)11);		//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_th2 = objWorkBook.createCellStyle();   	  		//��Ÿ��
			style_th2.setFont(font_th2);										//��Ʈ ����
			style_th2.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  		//��� ����
			style_th2.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//���� ��� ����
			style_th2.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index); 	//���� gray 40%
			style_th2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//���� ����
			style_th2.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_th2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_th2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_th2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
		//Content ��Ÿ�� 1 (align center)
			HSSFFont font_cont1 = objWorkBook.createFont();
			font_cont1.setFontName("���� ���");			//����ü
			font_cont1.setFontHeightInPoints((short)11);	//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_cont1 = objWorkBook.createCellStyle();   	  	//��Ÿ��
			style_cont1.setFont(font_cont1);									//��Ʈ ����
			style_cont1.setAlignment(HSSFCellStyle.ALIGN_CENTER);			  	//��� ����
			style_cont1.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//���� ��� ����
			style_cont1.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_cont1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		//Content ��Ÿ�� 2 (align left)
			HSSFFont font_cont2 = objWorkBook.createFont();
			font_cont2.setFontName("���� ���");			//����ü
			font_cont2.setFontHeightInPoints((short)11);	//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_cont2 = objWorkBook.createCellStyle();   	  	//��Ÿ��
			style_cont2.setFont(font_cont2);									//��Ʈ ����
			style_cont2.setAlignment(HSSFCellStyle.ALIGN_LEFT);			  		//���� ����
			style_cont2.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//���� ��� ����
			style_cont2.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_cont2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		//Content ��Ÿ�� 3 (align center, bg tan)
			HSSFFont font_cont3 = objWorkBook.createFont();
			font_cont3.setFontName("���� ���");			//����ü
			font_cont3.setFontHeightInPoints((short)11);	//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_cont3 = objWorkBook.createCellStyle();   	  	//��Ÿ��
			style_cont3.setFont(font_cont3);									//��Ʈ ����
			style_cont3.setAlignment(HSSFCellStyle.ALIGN_LEFT);			  		//��� ����
			style_cont3.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//���� ��� ����
			style_cont3.setFillForegroundColor(HSSFColor.TAN.index);		 	//���� TAN
			style_cont3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//���� ����
			style_cont3.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_cont3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont3.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
		//Content ��Ÿ�� 4 (align center, bg PALE_BLUE)
			HSSFFont font_cont4 = objWorkBook.createFont();
			font_cont4.setFontName("���� ���");			//����ü
			font_cont4.setFontHeightInPoints((short)11);	//���� ũ��
				
			//���� ��Ÿ�Ͽ� ��Ʈ ����, ����
			HSSFCellStyle style_cont4 = objWorkBook.createCellStyle();   	  	//��Ÿ��
			style_cont4.setFont(font_cont4);									//��Ʈ ����
			style_cont4.setAlignment(HSSFCellStyle.ALIGN_LEFT);			  		//��� ����
			style_cont4.setVerticalAlignment (HSSFCellStyle.VERTICAL_CENTER); 	//���� ��� ����
			style_cont4.setFillForegroundColor(HSSFColor.PALE_BLUE.index);		//���� PALE_BLUE			
			style_cont4.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);			//���� ����
			style_cont4.setBorderRight(HSSFCellStyle.BORDER_THIN);	//�׵θ�
			style_cont4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style_cont4.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style_cont4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
		//first row
		objRow = objSheet.createRow(0);
		objCell = objRow.createCell(0);
		objCell.setCellValue("��δ纰 ���α׷� �");
		objCell.setCellStyle(style_title);
		
		// second row
		String titleList[] = {"��ȣ","����ȸ","��ȸ","��δ��ڵ�","��δ��",
				"���α׷��ڵ�1","���α׷���1","���α׷��ڵ�2","���α׷���2",
				"���α׷���","�Ⱓ������","�Ⱓ������","�̿��ڼ�","������(���,��ü)",
				"���Ƚ��"
		};
		
		String catList[] = {"���α׷��ڵ�1","�ڵ�","���α׷��ڵ�2","�ڵ�"};
		
		
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
			rttr.addFlashAttribute("msg","�����Ͱ� �������� �ʽ��ϴ�.");
			return "redirect:/";
		}
		
		List<List<String>> strList = new ArrayList<List<String>>();
		
		int idx = 0;
		for (ExcelOutput e : dataList) {
			List<String> addList = new ArrayList<String>();

			addList.add(idx+1+""); //��ȣ
			addList.add(e.getScc().getAreaCode().substring(0,2));		//����ȸ
			addList.add(e.getScc().getAreaCode().split("-")[0]+e.getScc().getAreaCode().split("-")[1]);		//��ȸ
			addList.add(e.getScc().getAreaCode()+"-"+e.getScc().getBranchCode()+"-"+e.getScc().getSccCode());	//��δ��ڵ�
			addList.add(e.getScc().getName());							//��δ��
			addList.add(e.getCat1().getCode());						//���α׷��ڵ�1
			addList.add(e.getCat1().getName());						//���α׷���1
			addList.add(e.getCat1().getCode()+e.getCat2().getCode());	//���α׷��ڵ�2
			addList.add(e.getCat2().getName());						//���α׷���2
			addList.add(e.getProgram().getName());						//���α׷���
			addList.add(e.getOfferprogram().getSimpleBeginDate());		//�Ⱓ������
			addList.add(e.getOfferprogram().getSimpleEndDate());		//�Ⱓ������
			addList.add(e.getOffer().getActiveUser()+"");				//�̿��ڼ�
			addList.add(e.getagency().getName());						//������(���,��ü)
			addList.add(e.getOffer().getMonthlyOper()+"");				//���Ƚ��
			
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
				
				if(i<=1) { //1,2��° ���� �������
					objCell.setCellStyle(style_cont1);
				} else {
					objCell.setCellStyle(style_cont2);
				}
			}
		}

		//input category
		List<Cat1HasCat2> catDataList = cat1Service.listCat1HasCat2();
		if(catDataList.size()==0) {
			rttr.addFlashAttribute("msg","ī�װ��� �������� �ʽ��ϴ�.");
			return "redirect:/"; 			
		}
		
		if(catDataList.get(0).getCat2List().size()==0) {
			rttr.addFlashAttribute("msg","ī�װ��� �������� �ʽ��ϴ�.");
			return "redirect:/"; 
		}
		
		int colCnt = titleList.length+1;
		int rowCnt = 2;
		
		for (int i=0; i<catDataList.size(); i++) { //cat1 row ���
			
			if(objSheet.getRow(rowCnt)==null) { //row�� ���ٸ� ���θ���.
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

			for(int j=0; j<catDataList.get(i).getCat2List().size(); j++) { //cat2 row ���
				if(objSheet.getRow(rowCnt)==null) { //row�� ���ٸ� ���θ���.
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
		String fileName = "��δ纰 ���α׷��";
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

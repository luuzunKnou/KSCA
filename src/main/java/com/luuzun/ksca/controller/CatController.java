package com.luuzun.ksca.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.luuzun.ksca.domain.Cat1;
import com.luuzun.ksca.domain.Cat1HasCat2;
import com.luuzun.ksca.domain.Cat2;
import com.luuzun.ksca.domain.Manager;
import com.luuzun.ksca.service.Cat1Service;
import com.luuzun.ksca.service.Cat2Service;

@Controller
@RequestMapping("/cat/*")
public class CatController {
	private static final Logger logger = LoggerFactory.getLogger(CatController.class);
	
	@Inject	private Cat1Service cat1Service;
	@Inject	private Cat2Service cat2Service;

	//Cat List
	@RequestMapping(value="/catList")
	public String catListGET(Model model, HttpSession session, RedirectAttributes rttr) throws Exception{
		logger.info("Area List Page..........");
		Manager manager = (Manager) session.getAttribute("login");
		
		//권한 확인
		if(manager==null) {
			rttr.addFlashAttribute("msg","권한이 없습니다.");
			return "redirect:/"; 
		}
		
		List<Cat1HasCat2> categoryList = cat1Service.listCat1HasCat2();
		model.addAttribute("categoryList",categoryList);
		
		return "cat/catList";
	}
	
	
	
	//Category 1 추가
	@ResponseBody
	@RequestMapping(value="/createCat1", method=RequestMethod.POST)
	public Cat1 createCat1(Cat1 cat1, Model model) throws Exception  {
		logger.info("Create Category1..........");
		logger.info("Category1: "+cat1);
		
		try {
			cat1Service.create(cat1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cat1;
	}
	
	//Category 2 추가
	@ResponseBody
	@RequestMapping(value="/createCat2", method=RequestMethod.POST)
	public Cat2 createCat2(Cat2 cat2, Model model) throws Exception  {
		logger.info("Create Category2..........");
		logger.info("Category2: "+cat2);
		
		try {
			cat2Service.create(cat2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cat2;
	}
	
	
	
	//Category 1 수정
	@ResponseBody
	@RequestMapping(value="/updateCat1", method=RequestMethod.POST)
	public Cat1 updateCat1(Model model, String destCode, Cat1 cat1) throws Exception {
		logger.info("Update Category 1..........");
		cat1Service.update(destCode, cat1);
		return cat1;
	}
	
	//Category 2 수정
	@ResponseBody
	@RequestMapping(value="/updateCat2", method=RequestMethod.POST)
	public Cat2 updateCat2(Model model, String destCode, String destCat1, Cat2 cat2) throws Exception {
		logger.info("Update Category 2..........");
		cat2Service.update(destCode, destCat1, cat2);
		return cat2;
	}
		
	
	
	//Category 1 삭제
	@ResponseBody
	@RequestMapping(value="/removeCat1", method=RequestMethod.POST)
	public String removeCat1(Model model, String code) throws Exception {
		logger.info("Remove Category 1..........");

		//하위 Cat2 존재하면 삭제 불가
		if(cat2Service.readByCat1(code).size()!=0) {
			return "ERROR:cascade";
		}
		
		cat1Service.delete(code);
		return code;
	}
	
	//Category 2 삭제
	@ResponseBody
	@RequestMapping(value="/removeCat2", method=RequestMethod.POST)
	public ResponseEntity<String> removeCat2(Model model, String cat1, String code) {
		logger.info("Remove Category 2..........");
		ResponseEntity<String> entity = null;
		try {
			cat2Service.delete(code, cat1);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
		
		
	//Branch Duplecation Check
//	@ResponseBody
//	@RequestMapping(value="/checkBranch", method=RequestMethod.POST)
//	public int checkBranch(HttpServletRequest req, String areaCode, String branchCode) throws Exception{
//		logger.info("Check duplication Branch");
//		 
//		 Branch branch =  service.read(areaCode, branchCode);
//		 
//		 if(branch != null) { //아이디 중복시 0 반환
//			 return 0;
//		 } 
//		 return 1;
//	}
}

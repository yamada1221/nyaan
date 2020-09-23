package com.yaoroz.nyaan.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yaoroz.nyaan.service.HomeService;

@Controller
@RequestMapping("/nyaan")
public class HomeController {

	@Autowired
	HomeService homeService;

	/**
	 * 初期ページに訪れたときにviewを+1する。
	 */
	@GetMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		homeService.index(request, response);
		return "index";
	}

	/**
	 * ボタンを押下するたびにcountを+1する。
	 */
	@RequestMapping(value = "/json", method = { RequestMethod.GET, RequestMethod.POST })
	public void json(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		homeService.json(request, response);
	}

	/**
	 * グラフのjsonを返す。
	 */
	@GetMapping("/graph")
	public void graph(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		homeService.graph(request, response);
	}

}

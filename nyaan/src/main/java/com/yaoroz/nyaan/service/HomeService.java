package com.yaoroz.nyaan.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HomeService {

	/**
	 * 初期ページ。
	 * 
	 * @param request  リクエスト
	 * @param response レスポンス
	 */
	void index(HttpServletRequest request, HttpServletResponse response);

	/**
	 * jsonリクエスト。
	 * 
	 * @param request  リクエスト
	 * @param response レスポンス
	 */
	void json(HttpServletRequest request, HttpServletResponse response);

	/**
	 * グラフリクエスト。
	 * 
	 * @param request  リクエスト
	 * @param response レスポンス
	 */
	void graph(HttpServletRequest request, HttpServletResponse response);
}

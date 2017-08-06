package com.ybg.base.util;
/** 使用JS进行控制的翻页转换类. */
public class AJAXPaginate {
	
	protected int			curPage;	// 当前页
	protected int			totals;		// 总记录数
	protected int			perPage;	// 每页大小
	protected int			totalPages;	// 总页数
	protected StringBuffer	roll;		// 解析的结果
	
	public AJAXPaginate(int totals, int curPage, int perPage) {
		this.totals = totals;
		this.curPage = curPage;
		this.perPage = perPage;
		if (totals / this.perPage < 1) {
			this.totalPages = 1;
		}
		else {
			this.totalPages = this.totals / perPage;
			if (this.totals % perPage != 0) {
				this.totalPages += 1;
			}
		}
	}
}

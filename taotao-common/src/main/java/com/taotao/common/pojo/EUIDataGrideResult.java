package com.taotao.common.pojo;

import java.util.List;

public class EUIDataGrideResult {
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	private long total;
	private List<?> rows;
}

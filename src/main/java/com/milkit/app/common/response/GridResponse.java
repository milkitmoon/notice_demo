package com.milkit.app.common.response;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.milkit.app.common.Grid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GridResponse<T> extends GenericResponse {

	@ApiModelProperty(value="Grid 결과값")
	private Grid grid = null;
	
	public GridResponse() {
		grid = new Grid();
	}
	
	public GridResponse(int resultCode, String resultMessage) {
		super(Integer.toString(resultCode), resultMessage);
	}
	
	public GridResponse(String resultCode, String resultMessage) {
		super(resultCode, resultMessage);
	}
	
	public String getPage() {
		return grid.getPage();
	}
	public void setPage(String page) {
		grid.setPage(page);
	}
	public void setPage(int page) {
		grid.setPage(Integer.toString(page));
	}
	public String getTotal() {
		return grid.getTotal();
	}
	public void setTotal(String total) {
		grid.setTotal(total);
	}
	public void setTotal(long total) {
		grid.setTotal(Long.toString(total));
	}
	public String getRecords() {
		return grid.getRecords();
	}
	public void setRecords(String records) {
		grid.setRecords(records);
	}
	public void setRecords(long records) {
		grid.setRecords(Long.toString(records));
	}
	public List<T> getRows() {
		return grid.getRows();
	}
	public void setRows(List<T> rows) {
		grid.setRows(rows);
	}
	
    @Override  
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.SHORT_PREFIX_STYLE
		);
    }

}

package com.res.pc.code.vo;

import java.util.List;

public class BeginningCondition {
	private String name;
	private String warehouseName;
	private String  p_id;
	private String begin;
	private String end;
	private List<BeginingVo> itemsList;


	public List<BeginingVo> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<BeginingVo> itemsList) {
		if(name==null){
		this.itemsList = itemsList;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name== null ? null : name.trim();
	}

	
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName  == null ? null : warehouseName.trim();
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id  == null ? null : p_id.trim();
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	

}

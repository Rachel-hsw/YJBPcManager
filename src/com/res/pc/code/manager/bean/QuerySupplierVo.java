package com.res.pc.code.manager.bean;

import java.util.Date;

public class QuerySupplierVo {
		
	 private String fid;

	    private String fclass;

	    private String fcode;

	    private String fname;

	    private String fnick;

	    private String fpym;

	    private String faddress;

	    private String fcontacts;

	    private String ftel;

	    private String fstatus;

	    private String remark;
	    private String begin;
		private String end;
	    private  String	currentPage="1";
	  

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

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

		public String getFid() {
	        return fid;
	    }

	    public void setFid(String fid) {
	        this.fid = fid;
	    }

	    public String getFclass() {
	        return fclass;
	    }

	    public void setFclass(String fclass) {
	        this.fclass = fclass == null ? null : fclass.trim();
	    }

	    public String getFcode() {
	        return fcode;
	    }

	    public void setFcode(String fcode) {
	        this.fcode = fcode == null ? null : fcode.trim();
	    }

	    public String getFname() {
	        return fname;
	    }

	    public void setFname(String fname) {
	        this.fname = fname == null ? null : fname.trim();
	    }

	    public String getFnick() {
	        return fnick;
	    }

	    public void setFnick(String fnick) {
	        this.fnick = fnick == null ? null : fnick.trim();
	    }

	    public String getFpym() {
	        return fpym;
	    }

	    public void setFpym(String fpym) {
	        this.fpym = fpym == null ? null : fpym.trim();
	    }

	    public String getFaddress() {
	        return faddress;
	    }

	    public void setFaddress(String faddress) {
	        this.faddress = faddress == null ? null : faddress.trim();
	    }

	    public String getFcontacts() {
	        return fcontacts;
	    }

	    public void setFcontacts(String fcontacts) {
	        this.fcontacts = fcontacts == null ? null : fcontacts.trim();
	    }

	    public String getFtel() {
	        return ftel;
	    }

	    public void setFtel(String ftel) {
	        this.ftel = ftel == null ? null : ftel.trim();
	    }

	    public String getFstatus() {
	        return fstatus;
	    }

	    public void setFstatus(String fstatus) {
	        this.fstatus = fstatus;
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }
	
	
		
		
}

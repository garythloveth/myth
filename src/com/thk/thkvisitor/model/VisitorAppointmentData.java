package com.thk.thkvisitor.model;

public class VisitorAppointmentData extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1018394753006643832L;

	public static final String VISITORNAME = "visitorName";
	private String visitorName;
	
	public static final String VISITORSEX = "visitorSex";
	private String visitorSex;
	
	public static final String VISITORIDNO = "visitorIDNo";
	private String visitorIDNo;
	
	public static final String COMPANYNAME = "companyName";
	private String companyName;
	
	public static final String VISITORTELNO = "visitorTelNo";
	private String visitorTelNo;
	
	public static final String VISITTODONAME = "visitToDoName";
	private String visitToDoName;
	
	public static final String VEHICLENO = "vehicleNo";
	private String vehicleNo;
	
	public static final String VISITORNUM = "visitorNum";
	private Integer visitorNum;
	
	public static final String EMPNO = "empNo";
	private String empNo;
	
	public static final String EMPNAME = "empName";
	private String empName;
	
	public static final String DPTNAME = "dptName";
	private String dptName;

	public static final String EMPTELNO = "empTelNo";
	private String empTelNo;
	
	public static final String MOBILENO = "mobileNo";
	private String mobileNo;
	
	public static final String OFFICEROOM = "officeRoom";
	private String officeRoom;
	
	public static final String TITNAME = "titName";
	private String titName;
	
	public static final String GRDNAME = "grdName";
	private String grdName;
	
	public VisitorAppointmentData(String visitorName, String visitorSex, String visitorIDNo, 
			String companyName, String visitorTelNo, String visitToDoName, String vehicleNo, Integer visitorNum,
			String empNo, String empName, String dptName, String empTelNo, 
			String mobileNo, String officeRoom, String titName , String grdName){
		super();
		this.put(VISITORNAME, visitorName == null ? "" : visitorName);
		this.put(VISITORSEX, visitorSex == null ? "" : visitorSex);
		this.put(VISITORIDNO, visitorIDNo == null ? "" : visitorIDNo);
		this.put(COMPANYNAME, companyName == null ? "" : companyName);
		this.put(VISITORTELNO, visitorTelNo == null ? "" : visitorTelNo);
		this.put(VISITTODONAME, visitToDoName == null ? "" : visitToDoName);
		this.put(VEHICLENO, vehicleNo == null ? "" : vehicleNo);
		this.put(VISITORNUM, visitorNum == null ? 1 : visitorNum);
		this.put(EMPNO, empNo == null ? "" : empNo);
		this.put(EMPNAME, empName == null ? "" : empName);
		this.put(DPTNAME, dptName == null ? "" : dptName);
		this.put(EMPTELNO, empTelNo == null ? "" : empTelNo);
		this.put(MOBILENO, mobileNo == null ? "" : mobileNo);
		this.put(OFFICEROOM, officeRoom == null ? "" : officeRoom);
		this.put(TITNAME, titName == null ? "" : titName);
		this.put(GRDNAME, grdName == null ? "" : grdName);
	}
	
	public Integer getVisitorNum() {
		return (Integer) get(VISITORNUM);
	}

	public void setVisitorNum(Integer visitorNum) {
		this.put(VISITORNUM, visitorNum == null ? 1001 : visitorNum);
	}

	public String getVisitorName() {
		return (String) get(VISITORNAME);
	}

	public void setVisitorName(String visitorName) {
		this.put(VISITORNAME, visitorName == null ? "" : visitorName);
	}

	public String getVisitorSex() {
		return (String) get(VISITORSEX);
	}

	public void setVisitorSex(String visitorSex) {
		this.put(VISITORSEX, visitorSex == null ? "" : visitorSex);
	}

	public String getVisitorIDNo() {
		return (String) get(VISITORIDNO);
	}

	public void setVisitorIDNo(String visitorIDNo) {
		this.put(VISITORIDNO, visitorIDNo == null ? "" : visitorIDNo);
	}

	public String getCompanyName() {
		return (String) get(COMPANYNAME);
	}

	public void setCompanyName(String companyName) {
		this.put(COMPANYNAME, companyName == null ? "" : companyName);
	}

	public String getVisitorTelNo() {
		return (String) get(VISITORTELNO);
	}

	public void setVisitorTelNo(String visitorTelNo) {
		this.put(VISITORTELNO, visitorTelNo == null ? "" : visitorTelNo);
	}

	public String getVisitToDoName() {
		return (String) get(VISITTODONAME);
	}

	public void setVisitToDoName(String visitToDoName) {
		this.put(VISITTODONAME, visitToDoName == null ? "" : visitToDoName);
	}

	public String getVehicleNo() {
		return (String) get(VEHICLENO);
	}

	public void setVehicleNo(String vehicleNo) {
		this.put(VEHICLENO, vehicleNo == null ? "" : vehicleNo);
	}

	public String getEmpNo() {
		return (String) get(EMPNO);
	}

	public void setEmpNo(String empNo) {
		this.put(EMPNO, empNo == null ? "" : empNo);
	}

	public String getEmpName() {
		return (String) get(EMPNAME);
	}

	public void setEmpName(String empName) {
		this.put(EMPNAME, empName == null ? "" : empName);
	}

	public String getDptName() {
		return (String) get(DPTNAME);
	}

	public void setDptName(String dptName) {
		this.put(DPTNAME, dptName == null ? "" : dptName);
	}

	public String getEmpTelNo() {
		return (String) get(EMPTELNO);
	}

	public void setEmpTelNo(String empTelNo) {
		this.put(EMPTELNO, empTelNo == null ? "" : empTelNo);
	}

	public String getMobileNo() {
		return (String) get(MOBILENO);
	}

	public void setMobileNo(String mobileNo) {
		this.put(MOBILENO, mobileNo == null ? "" : mobileNo);
	}

	public String getOfficeRoom() {
		return (String) get(OFFICEROOM);
	}

	public void setOfficeRoom(String officeRoom) {
		this.put(OFFICEROOM, officeRoom == null ? "" : officeRoom);
	}

	public String getTitName() {
		return (String) get(TITNAME);
	}

	public void setTitName(String titName) {
		this.put(TITNAME, titName == null ? "" : titName);
	}

	public String getGrdName() {
		return (String) get(GRDNAME);
	}

	public void setGrdName(String grdName) {
		this.put(GRDNAME, grdName == null ? "" : grdName);
	}

	@Override
	protected void initMap() {

	}

}

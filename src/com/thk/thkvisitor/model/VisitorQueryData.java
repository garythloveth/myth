package com.thk.thkvisitor.model;

public class VisitorQueryData extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1018394753006643832L;

	public static final String SERACH = "serach";
	private String serach;

	public VisitorQueryData(String serach) {
		super();
		this.put(SERACH, serach == null ? "" : serach);
	}

	public String getSerach() {
		return (String) get(SERACH);
	}

	public void setSerach(String serach) {
		this.put(SERACH, serach == null ? "" : serach);
	}

	@Override
	protected void initMap() {

	}

}

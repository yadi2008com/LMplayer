package com.nuc.bean;

public class TypeBean {
	private String typeName;
	private int typeId;
	private int typeParentId;
	private String typeParentName;
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getTypeParentId() {
		return typeParentId;
	}

	public void setTypeParentId(int typeParentId) {
		this.typeParentId = typeParentId;
	}

	public String getTypeParentName() {
		return typeParentName;
	}

	public void setTypeParentName(String typeParentName) {
		this.typeParentName = typeParentName;
	}
	
}

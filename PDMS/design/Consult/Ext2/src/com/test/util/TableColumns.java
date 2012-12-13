package com.test.util;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public class TableColumns {

	//������
	private String tableName;
	//���ݿ��ֶ���
	private String dbColumnName;
	//������
	private String columnName;
	//���ݿ��ֶ�����
	private String columnType;
	//java����
	private String javaType;
	//�ֶγ���
	private int columnSize;
	//�ֶ�С��λ����
	private int columnDecimalDigits;
	//��ע
	private String remark;	
	//�Ƿ�Ϊ����
	private boolean prikey;
	//�Ƿ�����Ϊ��
	private boolean nullable;
	
	public String getFirstLetterUpper(String colname){
		String f = colname.substring(0,1);
		f = f.toUpperCase();
		return f.concat(colname.substring(1));
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public int getColumnDecimalDigits() {
		return columnDecimalDigits;
	}

	public void setColumnDecimalDigits(int columnDecimalDigits) {
		this.columnDecimalDigits = columnDecimalDigits;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}	

	public boolean isPrikey() {
		return prikey;
	}

	public void setPrikey(boolean prikey) {
		this.prikey = prikey;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String toString(){
		
		StringBuffer sb = new StringBuffer("TableColumns={\n");
		sb.append("tableName="+this.tableName+",\n");
		sb.append("dbColumnName="+this.dbColumnName+",\n");
		sb.append("columnName="+this.columnName+",\n");
		sb.append("columnType="+this.columnType+",\n");
		sb.append("javaType="+this.javaType+",\n");
		sb.append("columnSize="+this.columnSize+",\n");
		sb.append("columnDecimalDigits="+this.columnDecimalDigits+",\n");
		sb.append("remark="+this.remark+",\n");
		sb.append("prikey="+this.prikey+"\n");
		sb.append("nullable="+this.nullable+"\n");
		sb.append("}\n");
		
		return sb.toString();
	}
	
}

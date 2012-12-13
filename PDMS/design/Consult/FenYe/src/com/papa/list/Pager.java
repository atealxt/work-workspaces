/**
 * 
 */
package com.papa.list;

/**
 * @author ��Ծ�� [Oct 26, 2007]
 */
public class Pager
{
	private int pageno=1; //��ǰҳ��

	private boolean initial=true; //��ʼ��ʶ

	private int rowcount=0; //��������

	private int pagenum=0; //��ҳ��

	private int pagesize=10; //ÿҳ��ʾ����

	private byte cmd=0; //���� 1��ҳ 2��һҳ 3��һҳ 4βҳ

	private int first=0; //��һ����¼λ��

	
	
	public int getFirst()
	{
		return first;
	}

	public void setFirst(int first)
	{
		this.first=first;
	}

	public int getPagesize()
	{
		return pagesize;
	}

	public void setPagesize(int pagesize)
	{
		this.pagesize=pagesize;
	}

	public boolean isInitial()
	{
		return initial;
	}

	public void setInitial(boolean initial)
	{
		this.initial=initial;
	}

	/**
	 * ȡ�õ�ǰҳ��
	 * @return int ��ǰҳ��
	 */
	public int getPageno()
	{
		if (isInitial())
			this.pageno=1;
		return pageno;
	}

	/**
	 * ���õ�ǰҳ��
	 * @param pageno ��ǰҳ��
	 */
	public void setPageno(int pageno)
	{
		this.pageno=pageno;
	}

	/**
	 * ȡ����������
	 * @return int ��������
	 */
	public int getRowcount()
	{
		return rowcount;
	}

	/**
	 * ������������
	 * @param rowcount ��������
	 */
	public void setRowcount(int rowcount)
	{
		this.rowcount=rowcount;
	}

	public int getPagenum()
	{
		return pagenum;
	}

	public void setPagenum(int pagenum)
	{
		this.pagenum=pagenum;
	}

	public byte getCmd()
	{
		return cmd;
	}

	public void setCmd(byte cmd)
	{
		this.cmd=cmd;
	}

}

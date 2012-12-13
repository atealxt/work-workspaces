/**
 * 
 */
package com.papa.list;

/**
 * @author 林跃辉 [Oct 26, 2007]
 */
public class Pager
{
	private int pageno=1; //当前页数

	private boolean initial=true; //初始标识

	private int rowcount=0; //资料总数

	private int pagenum=0; //总页数

	private int pagesize=10; //每页显示条数

	private byte cmd=0; //动作 1首页 2上一页 3下一页 4尾页

	private int first=0; //第一条记录位置

	
	
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
	 * 取得当前页数
	 * @return int 当前页数
	 */
	public int getPageno()
	{
		if (isInitial())
			this.pageno=1;
		return pageno;
	}

	/**
	 * 设置当前页数
	 * @param pageno 当前页数
	 */
	public void setPageno(int pageno)
	{
		this.pageno=pageno;
	}

	/**
	 * 取得资料总数
	 * @return int 资料总数
	 */
	public int getRowcount()
	{
		return rowcount;
	}

	/**
	 * 设置资料总数
	 * @param rowcount 资料总数
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

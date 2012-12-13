/**
 * 
 */
package com.papa.list;

/**
 * @author 林跃辉 [Nov 2, 2007]
 */
public class ListBean
{
	/**
	 * 计算分页
	 * @param pager
	 * @return
	 */
	public Pager computePager(Pager pager)
	{
		int pagesize=pager.getPagesize();
		//初始标志为true时,重新计算
		if (pager.isInitial())
		{
			int rowcount=pager.getRowcount();
			pager.setRowcount(rowcount);
			//重新计算总页数
			int pagenum=rowcount%pagesize==0?rowcount/pagesize:rowcount
					/pagesize+1;
			pager.setPagenum(pagenum);
		}
		byte cmd=pager.getCmd();
		if (cmd!=0) //等于0代表是真接选择页数或搜索，不等于0则是点击首页、上一页、下一页、未页
		{
			switch (cmd)
			{
			case 1:
				pager.setPageno(1);
				break;
			case 2:
				pager.setPageno(pager.getPageno()-1>0?pager.getPageno()-1:1);
				break;
			case 3:
				pager.setPageno(pager.getPageno()+1<pager.getPagenum()?pager
						.getPageno()+1:pager.getPagenum());
				break;
			case 4:
				pager.setPageno(pager.getPagenum());
				break;
			}
		}
		//计算第一条记录位置
		int pageno=pager.getPageno();
		int first=(pageno-1)*pagesize;
		pager.setFirst(first);
		return pager;
	}
}

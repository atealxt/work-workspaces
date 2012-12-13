/**
 * 
 */
package com.papa.list;

/**
 * @author ��Ծ�� [Nov 2, 2007]
 */
public class ListBean
{
	/**
	 * �����ҳ
	 * @param pager
	 * @return
	 */
	public Pager computePager(Pager pager)
	{
		int pagesize=pager.getPagesize();
		//��ʼ��־Ϊtrueʱ,���¼���
		if (pager.isInitial())
		{
			int rowcount=pager.getRowcount();
			pager.setRowcount(rowcount);
			//���¼�����ҳ��
			int pagenum=rowcount%pagesize==0?rowcount/pagesize:rowcount
					/pagesize+1;
			pager.setPagenum(pagenum);
		}
		byte cmd=pager.getCmd();
		if (cmd!=0) //����0���������ѡ��ҳ����������������0���ǵ����ҳ����һҳ����һҳ��δҳ
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
		//�����һ����¼λ��
		int pageno=pager.getPageno();
		int first=(pageno-1)*pagesize;
		pager.setFirst(first);
		return pager;
	}
}

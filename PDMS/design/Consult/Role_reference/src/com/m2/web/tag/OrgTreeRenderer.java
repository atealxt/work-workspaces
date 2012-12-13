package com.m2.web.tag;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.apache.struts2.components.Component;
import org.json.*;
import com.opensymphony.xwork2.util.ValueStack;
import com.m2.service.OrgService;
import com.m2.entity.FuncTree;
import com.m2.entity.Org;



public class OrgTreeRenderer  extends Component{
	
	private String location;
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public OrgTreeRenderer(ValueStack arg0) {
		super(arg0);
	}	
	
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		OrgService service = (OrgService)this.getStack().findValue("orgService");
		List  orgs =service.findAllOrgs();
		String path=this.getLocation();
		StringBuffer sb=new StringBuffer();
		
      //�򿪺͹ر�	
	//	sb.append("<p><a href='javascript: d.openAll();'>չ��</a> | <a href='javascript: d.closeAll();'>�ر�</a></p>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("  d=new dTree('d');");
		//�趨ͼ������·��
		setImgPath(sb,path);
		
      //���ѡ��ĳ���ڵ�ʱ�򣬵���onNodeClickFunc�������ú�����ҳ�涨��

		sb.append(" d.onNodeClickHandler=onNodeClickFunc; ");
		
		for (Iterator i=orgs.iterator();i.hasNext();){
			Org node = (Org)i.next();
			sb.append("d.add(")
			  .append(toJSONString(node))
			  .append(",")
			  .append(node.getId())
			  .append(",")
			  .append(node.getParentId()) //dtree����Ҫ�����Ϊ-1����ʾ���ڵ�
			  .append(",");		
			
			  sb.append("'")
				.append(node.getName())
				.append("',")
				.append("'#');"); 
			
		}
		sb.append(" document.write(d);");
		sb.append("</script>");
		try{
		    writer.write(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	private void setImgPath(StringBuffer sb,String path){
		path=path+"img/";
		sb.append("d.icon={")
		  .append("root:'").append(path).append("tree.gif',")
		  .append("folder:'").append(path).append("folder-closed.gif',")
		  .append("folderOpen:'").append(path).append("folder-open.gif',")
		  .append("node:'").append(path).append("tree_root.gif',")
		  .append("empty:'").append(path).append("empty.gif',")
		  .append("line:'").append(path).append("empty.gif',")
		  .append("join:'").append(path).append("empty.gif',")
		  .append("joinBottom:'").append(path).append("empty.gif',")
		  .append("plus:'").append(path).append("nolines_plus.gif',")
		  .append("plusBottom:'").append(path).append("nolines_plus.gif',")
		  .append("minus:'").append(path).append("nolines_minus.gif',")
		  .append("minusBottom:'").append(path).append("nolines_minus.gif',")
		  .append("nlPlus:'").append(path).append("empty.gif',")
		  .append("nlMinus:'").append(path).append("nolines_minus.gif'};");
	}
	
	
	private String toJSONString(Org org){   //ת��ΪJSON��ʽ���ִ�
		JSONObject json = new JSONObject();
		try{
		    json.put("id", org.getId());
		    json.put("pid", org.getParentId());
		    json.put("name", org.getName());
		    json.put("description", org.getDescription());
		}catch(Exception e){
			
		}
		return json.toString();
		
	}
	
	
	

}

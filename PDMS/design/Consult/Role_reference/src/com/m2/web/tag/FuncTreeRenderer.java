package com.m2.web.tag;

import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.apache.struts2.components.Component;
import org.json.*;
import com.opensymphony.xwork2.util.ValueStack;
import com.m2.service.SysFunctionService;
import com.m2.entity.FuncTree;
import com.m2.exception.M2Exception;


public class FuncTreeRenderer  extends Component{
	
	private String location;
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public FuncTreeRenderer(ValueStack arg0) {
		super(arg0);
	}	
	
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		SysFunctionService service = (SysFunctionService)this.getStack().findValue("sysFunctionService");
		List  nodes = null;
		try{
		    nodes =service.findAllFuncTreeNodes();
		}catch(M2Exception e){
			
		}
		String path=this.getLocation();
		StringBuffer sb=new StringBuffer();
		
		sb.append("<script type=\"text/javascript\">");
		sb.append("  d=new dTree('d');");
		//�趨ͼ������·��
		setImgPath(sb,path);
		
      //���ѡ��ĳ���ڵ�ʱ�򣬵���onNodeClickFunc�������ú�����ҳ�涨��

		sb.append(" d.onNodeClickHandler=onNodeClickFunc; ");
		
		for (Iterator i=nodes.iterator();i.hasNext();){
			FuncTree node = (FuncTree)i.next();
			sb.append("d.add(")
			  .append(toJSONString(node))
			  .append(",")
			  .append(node.getId())
			  .append(",")
			  .append(node.getParentId())
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
		  .append("node:'").append(path).append("folder-closed.gif',")
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
	
	
	private String toJSONString(FuncTree node){   //ת��ΪJSON��ʽ���ִ�
		JSONObject json = new JSONObject();
		try{
		    json.put("id", node.getId());
		    json.put("pid", node.getParentId());
		    json.put("name", node.getName());
		    json.put("description", node.getDescription());
		}catch(Exception e){
			
		}
		return json.toString();
		
	}
	
	
	

}

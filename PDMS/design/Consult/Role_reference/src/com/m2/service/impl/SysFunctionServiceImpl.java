package com.m2.service.impl;

import java.io.StringWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.m2.service.RoleService;
import com.m2.service.SysFunctionService;
import com.m2.entity.FuncTree;
import com.m2.entity.Function;
import com.m2.entity.Role;
import com.m2.exception.M2Exception;
import com.m2.dao.FuncTreeDAO;
import com.m2.dao.FunctionDAO;
public class SysFunctionServiceImpl implements SysFunctionService{

	private FuncTreeDAO funcTreeDAO;
	
	private FunctionDAO  functionDAO;
	
	private RoleService   roleService;
	
	public FuncTreeDAO getFuncTreeDAO() {
		return funcTreeDAO;
	}

	public void setFuncTreeDAO(FuncTreeDAO funcTreeDAO) {
		this.funcTreeDAO = funcTreeDAO;
	}

	public FunctionDAO getFunctionDAO() {
		return functionDAO;
	}

	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}
	

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void addFuncTreeNode(FuncTree treeNode)throws M2Exception{
		try{
			this.getFuncTreeDAO().save(treeNode);
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	public void updateFuncTreeNode(FuncTree treeNode)throws M2Exception{
		try{
			this.getFuncTreeDAO().saveOrUpdate(treeNode);
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	public void removeFuncTreeNode(FuncTree treeNode)throws M2Exception{
		try{
			this.getFuncTreeDAO().delete(treeNode);
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	
	public boolean isLeaf(FuncTree treeNode) throws M2Exception{
		List list = null;
		try{
			list=this.getFuncTreeDAO().findByParam(FuncTreeDAO.AMOUNT_OF_CHILDS, treeNode.getId());
		}catch(Exception e){
			throw new M2Exception(e);
		}
		if((Integer)list.get(0)>0)
		    return false;
		return true;
		
	}
	
	public boolean hasSubFunctions(FuncTree treeNode) throws M2Exception{
		List list=null;
		try{
			list=this.getFuncTreeDAO().findByParam(FunctionDAO.AMOUNT_OF_FUNC, treeNode);
		}catch(Exception e){
			throw new M2Exception(e);
		}
		if((Integer)list.get(0)>0)
		    return true;
		return false;
	}
		
	public boolean hasFuncTreeNode() throws M2Exception{
		Integer count=null;
		try{
		    count = this.getFuncTreeDAO().calculateAmount(FuncTree.class, null);
		}catch(Exception e){
			throw new M2Exception(e);
		}
		if (count==0)
			return false;
		return true;
	}

	
	
	
	public List findAllFuncTreeNodes()throws M2Exception{
		try{
			return this.getFuncTreeDAO().findAll("FuncTree");
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	
	public List findAllChildNodes(FuncTree parentNode)throws M2Exception{
		return null;
	}
	
	
	public FuncTree findByFuncTreeId(int id)throws M2Exception{
		try{
			FuncTree treeNode=(FuncTree)this.getFuncTreeDAO().findById(FuncTree.class,id);
			return treeNode;
		}catch(Exception e){
			throw new M2Exception(e);
		}
	}
	
	
    public List findFunctionsByTreeNode(FuncTree treeNode) throws M2Exception{
    	try{
    		List functions = this.getFuncTreeDAO().findByParam(FunctionDAO.FIND_FUNTIONS_BY_TREE_NODE,treeNode);
    		return functions;
		}catch(Exception e){
			throw new M2Exception(e);
		}
    	
    }
	
    public List findAllFunctions() throws M2Exception{
    	try{
    		List functions = this.getFuncTreeDAO().findAll("Function");
    		return functions;
    	}catch(Exception e){
    		throw new M2Exception(e);
    	}
    }
    
    
    
	
    public void addFunction(Function func)throws M2Exception{
    	FuncTree treeNode = func.getFuncTree();
		treeNode = findByFuncTreeId(treeNode.getId());
		if (treeNode==null)
			throw new M2Exception("Error param treeNodeId.");
		func.setFuncTree(treeNode);
    	try{
    		this.getFuncTreeDAO().save(func);
		}catch(Exception e){
			throw new M2Exception(e);
		}
    }
    	
    
    public void updateFunction(Function func)throws M2Exception{
    	FuncTree treeNode = func.getFuncTree();
		treeNode = findByFuncTreeId(treeNode.getId());
		if (treeNode==null)
			throw new M2Exception("Error param treeNodeId.");
		func.setFuncTree(treeNode);
    	try{
    		this.getFuncTreeDAO().update(func);
		}catch(Exception e){
			throw new M2Exception(e);
		}
    }
    
    
    public void removeFunction(Function func)throws M2Exception{
    	func = findByFunctionId(func.getId());
    	try{
    		this.getFuncTreeDAO().delete(func);
		}catch(Exception e){
			throw new M2Exception(e);
		}
    }
    
    public Function findByFunctionId(int id)throws M2Exception{
    	Function func=null;
    	try{
    		func=(Function)this.getFuncTreeDAO().findById(Function.class, id);
    	}catch(Exception e){
    		throw new M2Exception(e);
    	}
    	return func;
    }
    

    
    
    
    
    public String  createXMLStringForRoleSet(int roleId)throws M2Exception{
    	Role role=this.getRoleService().findRoleById(roleId);
    	List selFunctions = role.getFunctions();
    	List funcTreeNodes = this.findAllFuncTreeNodes();
    	List functions  = this.findAllFunctions();
    	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        Document doc=null;
        FuncTree node = new FuncTree(); 
        node.setId(-1);
       
        try{
        	DocumentBuilder db=dbf.newDocumentBuilder();
        	doc=db.newDocument();
            Element topElem = createDOMTree(node,funcTreeNodes,functions,selFunctions,doc);
            doc.appendChild(topElem);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StringWriter out = new StringWriter();
            StreamResult result = new StreamResult(out);
           // transformer.setOutputProperty("encoding","GBK");
            transformer.transform(source, result);
            out.flush();
            return out.toString();
            
        }catch(Exception e){
        	throw new M2Exception(e);
        }
    }
    
    private  Element createDOMTree(FuncTree node,List nodes,List funcs,List selFunctions,Document doc){
    	String name = node.getName();
    	
    	int id=node.getId();
    	Element nodeElem=null;
    	if (id==-1)     //生成XML根节点,注意这不是实际显示在页面上的根节点。
    	   nodeElem=doc.createElement("tree");
    	else
    		nodeElem=doc.createElement("item");
    	Attr attrText=doc.createAttribute("text");
    	attrText.setValue(name);
        Attr attrId=doc.createAttribute("id");
        
        if (id==-1)
        	attrId.setValue("0");
        else
            attrId.setValue("node_"+id);
        Attr attrImg=doc.createAttribute("im0");
        attrImg.setValue("folderClosed.gif");
        nodeElem.setAttributeNode(attrId);
        nodeElem.setAttributeNode(attrText);
        nodeElem.setAttributeNode(attrImg);
        for (int k=0;k<funcs.size();k++){
        	Function func = (Function)funcs.get(k);
        	if (id == func.getFuncTree().getId()){
        		nodeElem.appendChild(createDOMFuncNode(func,doc,selFunctions));
        	}
        }
        for (int i=0;i<nodes.size();i++){
        	FuncTree treeNode = (FuncTree)nodes.get(i);
        	if (treeNode.getParentId()==id){
        		Element child = createDOMTree(treeNode,nodes,funcs,selFunctions,doc);   //递归调用
        		nodeElem.appendChild(child);
        	}
        }
        return nodeElem;
        
    }	
    /**
     * 
     * @param func   
     * @param doc
     * @param selFunctions  某角色已经指定的功能
     * @return
     */
    private  Element createDOMFuncNode(Function func,Document doc,List selFunctions){
    	Element nodeElem=doc.createElement("item");
    	Attr attrText=doc.createAttribute("text");
    	attrText.setValue(func.getName());
        Attr attrId=doc.createAttribute("id");
        attrId.setValue("func_"+func.getId());
        Attr attrImg=doc.createAttribute("im0");
        attrImg.setValue("func.gif");
        nodeElem.setAttributeNode(attrId);
        nodeElem.setAttributeNode(attrText);
        nodeElem.setAttributeNode(attrImg);
        if (selFunctions!=null){
            for(int k=0;k<selFunctions.size();k++){
            	if (((Integer)selFunctions.get(k)).intValue()==func.getId()){
            		Attr attrChecked= doc.createAttribute("checked");
                	attrChecked.setValue("1");
                	nodeElem.setAttributeNode(attrChecked);
                	break;
            	}
            }
        }
    	return nodeElem;
    }
		    
    

}

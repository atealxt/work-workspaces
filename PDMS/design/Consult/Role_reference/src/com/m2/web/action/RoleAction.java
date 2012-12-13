package com.m2.web.action;

import java.util.*;
import com.m2.entity.Function;
import com.m2.entity.Role;
import com.m2.entity.FuncTree;
import com.m2.exception.*;
import com.m2.service.RoleService;
import com.m2.service.SysFunctionService;


public class RoleAction  extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1379035949840959532L;
	
	private static final String FOR_ROLE_SET="forRoleSet"; 
	
	private static final String FOR_XML_DATA="forXMLData"; 
	
	private List<Integer> functions = new ArrayList<Integer>();
	
	//private List<Function> functionsAll = new ArrayList<Function>();
	
	private Integer roleId = -1;
	
	private Integer funcTreeNode=0;
	
	private String name ;
	
	private String funcs;
	
	private String description;
	
	private String xmlData;
	
	private RoleService roleService;
	
	private SysFunctionService sysFunctionService;
	


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Integer> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Integer> functions) {
		this.functions = functions;
	}

//	public List<Function> getFunctionsAll() {
//		return functionsAll;
//	}
//
//	public void setFunctionsAll(List<Function> functionsAll) {
//		this.functionsAll = functionsAll;
//	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	

	public String getFuncs() {
		return funcs;
	}

	public void setFuncs(String funcs) {
		this.funcs = funcs;
	}

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}

	public Integer getFuncTreeNode() {
		return funcTreeNode;
	}

	public void setFuncTreeNode(Integer funcTreeNode) {
		this.funcTreeNode = funcTreeNode;
	}

	public SysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(SysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@SuppressWarnings("unchecked")
	public String execute(){
		
	
		
		if ("add".equalsIgnoreCase(this.getAction())){
			return this.addRole();
		}
		
		if ("update".equalsIgnoreCase(this.getAction())){
			return this.updateRole();
		}
		
		if ("del".equalsIgnoreCase(this.getAction())){
			return this.delRole();
		}
		
		if ("forUpdate".equalsIgnoreCase(this.getAction())){
			this.setAction("update");
			Role role ;
			try{
				role  = this.getRoleService().findRoleById(this.getRoleId());
			}catch(M2Exception e){
				return sysError(e);
			}
			if (role!=null){
				this.setName(role.getName());
				this.setDescription(role.getDescription());
			}
			return FORUPDATE;
		}
		
		if ("forAdd".equalsIgnoreCase(this.getAction())){
			this.setAction("add");
			return FORADD;
		}
		
		if ("forRoleSet".equalsIgnoreCase(this.getAction())){
			return this.forRoleSet();
		}
		
		if ("forXMLData".equalsIgnoreCase(this.getAction())){
			return this.forXMLData();
		}		
		
		
		if ("roleSet".equalsIgnoreCase(this.getAction())){
			return this.roleSet();
		}
		return null;
		
		

	}
	
	public String addRole(){
		Role role  = new Role();
		role.setName(this.getName());
		role.setDescription(this.getDescription());
		try{
			this.getRoleService().addRole(role);
		}catch(M2Exception e){
			return sysError(e);
		}
		return SUCCESS;
	}
	
	public String updateRole(){
	
		try{
			Role role  = this.getRoleService().findRoleById(this.getRoleId());
			role.setName(this.getName());
			role.setDescription(this.getDescription());
			this.getRoleService().updateRole(role);
		}catch(M2Exception e){
			return sysError(e);
		}
		return SUCCESS;
	}
	 
	public String delRole(){
//		try{
//			Role role  = this.getRoleService().findRoleById(this.getroleId());
//			this.getRoleService().delRole(role);
//		}catch(M2Exception e){
//			e.printStackTrace();
//		}
		return SUCCESS;
	}
	
	public String index(){
		FuncTree node = new FuncTree();
		node.setId(this.getFuncTreeNode());
		List funcs =null;
		try{
			funcs = this.getSysFunctionService().findFunctionsByTreeNode(node);
		}catch(M2Exception e){
			return sysError(e);
		}
		this.setFunctions(funcs);
		return INDEX;

	}
	
	
	public String forRoleSet(){
		this.setAction("roleset");
		Role role =null;
		try{
			role  = this.getRoleService().findRoleById(this.getRoleId());
		}catch(M2Exception e){
			return sysError(e);
		}
		this.setName(role.getName());
		this.setDescription(role.getDescription());
		return FOR_ROLE_SET;
	}
	
	
	
	
	public String forXMLData(){
		
		//int roleid = this.getRoleId();
		try{
			this.setXmlData(this.getSysFunctionService().createXMLStringForRoleSet(this.getRoleId()));
		}catch(M2Exception e){
			return sysError(e);
		}
		return FOR_XML_DATA;
		
	}
	
	
	/******
	 * 设置角色的功能，成功则返回角色列表页面
	 * 
	 * 
	 * @return
	 */
	
	
	public String roleSet(){
		
		String [] funcsArr = this.getFuncs().split(",");
		List funcIds =new ArrayList();
		if (funcsArr!=null){
			for (int i = 0;i<funcsArr.length;i++){
				if (funcsArr[i].length()<5)
					continue;
				String pre=funcsArr[i].substring(0,4);
				if ("func".equals(pre)){
					try{
						Integer fid = Integer.valueOf(funcsArr[i].substring(5));
						funcIds.add(fid);
					}catch(NumberFormatException e){
						e.printStackTrace();
						return sysError(e);
					}
				}
			}
		}
		try{
			Role role  = this.getRoleService().findRoleById(this.getRoleId());
			role.setFunctions(funcIds);
			this.getRoleService().updateRole(role);
		}catch(M2Exception e){
			return sysError(e);
		}
		return SUCCESS;
		
		
	}
	
	

}

package com.test.gen;

import com.test.gen.interfc.IGenService;

/**
*
* <p>Title: </p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2008</p>
* <p>Company: �Ϻ�**��˾</p>
* @author ף����
* @version 1.0
*/
public class AutoGen {

	public static void main(String[] args){
			
		IGenService gs = GenFactory.getinstance().getGenService();
		gs.genZhuFiles();
		//gs.genIdealFiles();
	}
}

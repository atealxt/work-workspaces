package com.test.system.user.ibatis;

import java.util.List;
import com.test.system.user.model.UserModel;
 /**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public interface IUserDAO {
	
	//
	public int createUser(UserModel model);
	//
    public int updateUser(UserModel model);
    //
    public int deleteUser(UserModel model);
    //
    public List<UserModel> getUserList(UserModel model);
    //
    public int getUserCount(UserModel model);
    //
    public List<UserModel> getUserList(int start,int count,UserModel model);
    //
    public UserModel getUser(UserModel model);
    
}



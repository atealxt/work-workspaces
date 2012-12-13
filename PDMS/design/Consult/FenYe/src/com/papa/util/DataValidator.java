package com.papa.util;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class DataValidator
{
	public static ActionMessages isNull(String checkString,String fieldName,
			String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isRange(String checkString,int min,int max,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		if (!GenericValidator.isBlankOrNull(checkString)
				&&!GenericValidator.isInRange(checkString.getBytes().length,
						min,max))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		return messages;
	}

	public static ActionMessages isEmail(String checkString,boolean isRequired,
			String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (!GenericValidator.isBlankOrNull(checkString)
				&&!GenericValidator.isEmail(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isTelFormat(String checkString,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (messages.isEmpty()
				&&!(GenericValidator.matchRegexp(checkString,
						"^([0-9]{3,4}-[0-9]{7,8}(-[0-9]{2,6})?)?$")))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isDataFormat(String checkString,
			String dateFormat,boolean isRequired,String fieldName,
			String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
		{
			messages.add(fieldName,new ActionMessage(messageKey));
			return messages;
		}
		if (!GenericValidator.isBlankOrNull(checkString))
		{
			checkString=checkString.trim();
			if (!GenericValidator.isDate(checkString,dateFormat,false))
			{
				messages.add(fieldName,new ActionMessage(messageKey));
				return messages;
			}
			if (dateFormat.equals("yyyy-MM-dd")
					&&!(GenericValidator.matchRegexp(checkString,
							"^([0-9]{4}-[0-9]{1,2}-[0-9]{1,2})?$")))
			{
				messages.add(fieldName,new ActionMessage(messageKey));
				return messages;
			}
		}
		return messages;
	}

	public static ActionMessages isIntRange(int checkInt,int min,int max,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (!isRequired&&checkInt==0)
			return messages;
		if (!GenericValidator.isInRange(checkInt,min,max))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		return messages;
	}

	public static ActionMessages isPasswordFormat(String checkString,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		int min=6;
		int max=20;
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		if (messages.isEmpty()
				&&!GenericValidator.matchRegexp(checkString,new StringBuilder(
						"^([0-9A-Za-z_-]{").append(min).append(",").append(max)
						.append("})?$").toString()))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		return messages;
	}

	public static ActionMessages isPasswordFormat(String checkString,
			boolean isRequired,int min,int max,String fieldName,
			String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		if (messages.isEmpty()
				&&!GenericValidator.matchRegexp(checkString,new StringBuilder(
						"^([0-9A-Za-z_-]{").append(min).append(",").append(max)
						.append("})?$").toString()))
			messages.add(fieldName,new ActionMessage(messageKey,Integer
					.valueOf(min),Integer.valueOf(max)));
		return messages;
	}

	public static ActionMessages isZipCode(String checkString,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (messages.isEmpty()
				&&!GenericValidator.matchRegexp(checkString,
						"^([1-9][0-9]{5})?$"))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isIdCardNumber(String checkString,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (messages.isEmpty()
				&&!(GenericValidator.matchRegexp(checkString,
						"^([1-9][0-9]{14}|[1-9][0-9]{17}|[1-9][0-9]{16}x)?$")))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isMobilePhone(String checkString,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (messages.isEmpty()
				&&!GenericValidator.matchRegexp(checkString,"^(1[0-9]{10})?$"))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isIpAddress(String checkString,
			boolean isRequired,String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (messages.isEmpty()
				&&!(GenericValidator
						.matchRegexp(checkString,
								"^(((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?))?$")))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}

	public static ActionMessages isUrl(String checkString,boolean isRequired,
			String fieldName,String messageKey)
	{
		ActionMessages messages=new ActionMessages();
		if (isRequired&&GenericValidator.isBlankOrNull(checkString))
			messages.add(fieldName,new ActionMessage(messageKey));
		if (messages.isEmpty()
				&&!GenericValidator.isBlankOrNull(checkString)
				&&!(GenericValidator
						.matchRegexp(checkString,
								"^(http|https|ftp)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$")))
			messages.add(fieldName,new ActionMessage(messageKey));
		return messages;
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<script type="text/javascript">

function chk(){
    if(confirm("submit?")){
        return true;
    }
    return false;
}

</script>

</head>
<body>

<form name="myform" action="myaction">

<input type="text" id="text1" name="text1" />
<input type="submit" id="button1" name="button1" value="submit" onclick="return chk()" />

</form>

</body>
</html>
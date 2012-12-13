package book.dwr;

public class DwrTest {
	public String getPersonList(){
		StringBuffer jsonData = new StringBuffer();
		jsonData.append("{");
		jsonData.append("metaData : {totalProperty: 'results',");
		jsonData.append("root: 'rows',");
		jsonData.append("id: 'id' ,");
		jsonData.append("fields : [");
		jsonData.append("{name: 'id',mapping:'id'},");
		jsonData.append("{name: 'personName',mapping:'name'},");
		jsonData.append("{name: 'personAge',mapping:'age'}");
		jsonData.append("]},");
		jsonData.append("results : 5,");
		jsonData.append("rows : [");
		jsonData.append("{ id : 0 , name : 'tom' , age : 24 },");
		jsonData.append("{ id : 1 , name : 'jack' , age : 18 }");
		jsonData.append("]");
		jsonData.append("}");
		return jsonData.toString();
	}
}

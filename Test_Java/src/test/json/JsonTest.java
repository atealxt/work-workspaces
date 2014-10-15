package test.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import test.MyBean;

public class JsonTest {

    private JsonConfig jconfig;

    @Before
    public void setUp() {
        jconfig = new JsonConfig();
        final DefaultValueProcessor defaultValueProcessor = new MyDefaultValueProcessor();
        jconfig.registerDefaultValueProcessor(Date.class, defaultValueProcessor);
    }

    @Test
    public void json() throws JsonGenerationException, JsonMappingException, IOException {
        final MyBean mybean1 = new MyBean();
        mybean1.setDdd("strddd1");
        final JSONObject jsonObj = JSONObject.fromObject(mybean1, jconfig);
        System.out.println(jsonObj.toString());

        final ObjectMapper mapper = new ObjectMapper();
        final String argumentsJsonString = mapper.writeValueAsString(jsonObj.toString());
        System.out.println(argumentsJsonString);

        final String argumentsJsonStringArr = mapper.writeValueAsString(new String[] { jsonObj.toString() });
        System.out.println(argumentsJsonStringArr);
    }

    @Test
    public void filterProperty() throws JsonGenerationException, JsonMappingException, IOException {
        jconfig.registerPropertyExclusion(MyBean.class, "eeeee");
        final MyBean mybean = new MyBean();
        mybean.setDdd("strddd1");
        mybean.setCcc(123);
        final JSONObject jsonObj = JSONObject.fromObject(mybean, jconfig);
        System.out.println(jsonObj.toString());
    }

    @Test
    public void format() throws JsonGenerationException, JsonMappingException, IOException {
        final MyBean mybean = new MyBean();
        mybean.setDate(new Date());
        jconfig.registerJsonValueProcessor(Date.class, new DateFormartProcessor());
        final JSONObject jsonObj = JSONObject.fromObject(mybean, jconfig);
        System.out.println(jsonObj.toString());
    }
}

class MyDefaultValueProcessor implements DefaultValueProcessor {

    public MyDefaultValueProcessor() {}

    public Object getDefaultValue(@SuppressWarnings("rawtypes") final Class type) {
        if (JSONUtils.isArray(type)) {
            return new JSONArray();
        }
        if (Date.class == type) {
            return "没有值！";
        }
        return JSONNull.getInstance();
    }
}

class DateFormartProcessor implements JsonValueProcessor {

    public DateFormartProcessor() {}

    @Override
    public Object processArrayValue(final Object paramObject, final JsonConfig paramJsonConfig) {
        throw new UnsupportedOperationException();
    }

    /**
     * @param paramString 属性名
     * @param paramObject json对象的值
     * @param paramJsonConfig jsonConfig对象
     */
    @Override
    public Object processObjectValue(
            final String paramString,
            final Object paramObject,
            final JsonConfig paramJsonConfig) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(paramObject);
    }
}

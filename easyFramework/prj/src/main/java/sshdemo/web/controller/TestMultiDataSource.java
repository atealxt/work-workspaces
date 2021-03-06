package sshdemo.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sshdemo.core.DSContextHolder;
import sshdemo.core.DataSourceMap;
import sshdemo.entity.Father;
import sshdemo.service.MultiDataSourceService;

@Controller
public class TestMultiDataSource {

    @Autowired
    @Qualifier("MultiDataSourceService")
    protected MultiDataSourceService multiDataSourceService;

    @RequestMapping("/testMultiDataSource.action")
    public void hello(final HttpServletResponse resp) {

        Father f = new Father();
        f.setName("spring");

        multiDataSourceService.execute(f);
        System.out.println("f.getId(): " + f.getId());

        DSContextHolder.setDSContext(DataSourceMap.ORACLE);

        multiDataSourceService.execute(f);
        DSContextHolder.clearDSContext();

        multiDataSourceService.multiDSSearch(f.getId());

        try {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("test success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

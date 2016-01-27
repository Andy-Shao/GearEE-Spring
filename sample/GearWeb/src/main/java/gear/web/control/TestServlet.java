package gear.web.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshaox.servlet.mapping.FindingMapping;
import com.github.andyshaox.servlet.mapping.Mapping;
import com.github.andyshaox.servlet.mapping.MappingFactory;
import com.github.andyshaox.servlet.mapping.MappingProcess;
import com.github.andyshaox.servlet.mapping.ProcessType;
import com.github.andyshaox.servlet.mapping.View;

public class TestServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(TestServlet.class);
    private static final long serialVersionUID = 5706679490014479217L;
    private FindingMapping findingMapping;
    private MappingFactory mappingFactory;
    private MappingProcess mappingProcess;

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        TestServlet.LOG.info("======Request Info=========");
        TestServlet.LOG.info(req.getContextPath());
        TestServlet.LOG.info(req.getRequestURI());
        TestServlet.LOG.info(req.getRequestURL());
        TestServlet.LOG.info(req.getMethod());
        TestServlet.LOG.info("======Building Mapping=========");
        Bitree<Mapping> bitree = Bitree.defaultBitTree();
        this.mappingFactory.buildMappingMap(bitree);
        Bitree.preorder(bitree.root() , (mapping) -> {
            TestServlet.LOG.info(mapping);
        });
        TestServlet.LOG.info("======Finding Mapping=========");
        Mapping mapping = this.findingMapping.search(this.getServletConfig() , req , resp , bitree);
        TestServlet.LOG.info(mapping);
        TestServlet.LOG.info("======Mapping Process=========");
        View view = this.mappingProcess.doProcess(this.getServletConfig() , req , resp , mapping , new ProcessType());
        TestServlet.LOG.info(view);
        TestServlet.LOG.info(view.getResource());
        TestServlet.LOG.info(view.getViewProcess());
        view.process(this.getServletConfig() , req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        this.doGet(req , resp);
    }

    public void setFindingMapping(FindingMapping findingMapping) {
        this.findingMapping = findingMapping;
    }

    public void setMappingFactory(MappingFactory mappingFactory) {
        this.mappingFactory = mappingFactory;
    }

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }

}

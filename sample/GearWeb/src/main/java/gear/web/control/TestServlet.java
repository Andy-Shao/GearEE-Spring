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
    private static final long serialVersionUID = 5706679490014479217L;
    private static final Logger LOG = Logger.getLogger(TestServlet.class);
    private MappingFactory mappingFactory;
    private FindingMapping findingMapping;
    private MappingProcess mappingProcess;

    public void setMappingProcess(MappingProcess mappingProcess) {
        this.mappingProcess = mappingProcess;
    }

    public void setFindingMapping(FindingMapping findingMapping) {
        this.findingMapping = findingMapping;
    }

    public void setMappingFactory(MappingFactory mappingFactory) {
        this.mappingFactory = mappingFactory;
    }

    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        LOG.info("======Request Info=========");
        LOG.info(req.getContextPath());
        LOG.info(req.getRequestURI());
        LOG.info(req.getRequestURL());
        LOG.info(req.getMethod());
        Bitree<Mapping> bitree = Bitree.defaultBitTree();
        this.mappingFactory.buildMappingMap(bitree);
        LOG.info("======Building Mapping=========");
        Bitree.preorder(bitree.root() , (mapping) -> {
            LOG.info(mapping);
        });
        LOG.info("======Finding Mapping=========");
        Mapping mapping = this.findingMapping.search(getServletConfig() , req , resp , bitree);
        LOG.info(mapping);
        LOG.info("======Mapping Process=========");
        View view = this.mappingProcess.doProcess(getServletConfig() , req , resp , mapping , new ProcessType());
        LOG.info(view);
        LOG.info(view.getResource());
        LOG.info(view.getViewProcess());
        view.process(getServletConfig() , req , resp);
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException {
        this.doGet(req , resp);
    }

}

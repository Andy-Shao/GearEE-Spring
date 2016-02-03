package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshaox.servlet.mapping.annotation.AnnotationMappingFactory;

import gear.web.control.IndexControl;
import gear.web.control.LoginControl;

public class GenericFindingMappingEngineTest {
    private GenericFindingMappingEngine findingMappingEngine;

    @Before
    public void before() {
        this.findingMappingEngine = new GenericFindingMappingEngine();
    }

    @Test
    public void test() throws ServletException, IOException {
        Bitree<Mapping> bitree = Bitree.defaultBitTree();
        AnnotationMappingFactory factory = new AnnotationMappingFactory();
        factory.setClasses(new Class<?>[] { IndexControl.class , LoginControl.class });
        factory.buildMappingMap(bitree);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getContextPath()).thenReturn("/webName");
        Mockito.when(request.getRequestURI()).thenReturn("/webName/login/process.html");
        Mockito.when(request.getMethod()).thenReturn("POST");
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ServletConfig config = Mockito.mock(ServletConfig.class);
        
        List<Mapping> list = new ArrayList<>();
        this.findingMappingEngine.search(config , request , response , bitree , list);
        Assert.assertThat(list.size() , Matchers.greaterThan(0));
        Mapping firstOne = list.get(0);
        Assert.assertThat(firstOne.getUrl() , Matchers.is("/process"));
    }
}

package com.test.vendor.postgres;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.jsql.model.InjectionModel;
import com.jsql.model.MediatorModel;
import com.jsql.model.exception.JSqlException;
import com.jsql.model.injection.method.MethodInjection;
import com.jsql.model.injection.strategy.StrategyInjection;
import com.jsql.model.injection.vendor.Vendor;
import com.jsql.util.ConnectionUtil;
import com.jsql.util.ParameterUtil;
import com.jsql.view.terminal.SystemOutTerminal;

@TestInstance(Lifecycle.PER_CLASS)
public class PostgresTimeGetTestSuite extends ConcretePostgresTestSuite {

    public void initialize3() throws Exception {
        
        InjectionModel model = new InjectionModel();
        this.injectionModel = model;

        model.addObserver(new SystemOutTerminal());

        model.parameterUtil.initQueryString("http://localhost:8080/greeting-time");
        model.parameterUtil.initRequest("");
        model.parameterUtil.setQueryString(Arrays.asList(
            new SimpleEntry<String, String>("tenant", "postgres"), 
            new SimpleEntry<String, String>("name", "1'")
        ));
        model.connectionUtil.setMethodInjection(model.QUERY);
        model.connectionUtil.setTypeRequest("GET");
        
        model.setIsScanning(true);
        model.setStrategy(model.TIME);
        model.setVendorByUser(model.POSTGRESQL);
        model.beginInjection();
    }
    
    @Ignore
    @Override
    @Test
    public void listDatabases() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }
    
    @Ignore
    @Override
    @Test
    public void listTables() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }
    
    @Ignore
    @Override
    @Test
    public void listColumns() throws JSqlException {
        LOGGER.info("Ignore: too slow");
    }

}
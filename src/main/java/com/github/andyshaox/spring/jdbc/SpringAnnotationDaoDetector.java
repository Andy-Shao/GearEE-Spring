package com.github.andyshaox.spring.jdbc;

import com.github.andyshaox.jdbc.DaoDetector;
import com.github.andyshaox.jdbc.DaoDetectorCach;
import com.github.andyshaox.jdbc.FindSql;
import com.github.andyshaox.jdbc.GenericDaoFactory;
import com.github.andyshaox.jdbc.LoadingArgs;
import com.github.andyshaox.jdbc.RootSqlExecution;
import com.github.andyshaox.jdbc.SqlExecution;
import com.github.andyshaox.jdbc.annotation.AnnotationDaoDetector;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Apr 1, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class SpringAnnotationDaoDetector implements DaoDetector {
    private DaoDetector daoDetector;
    private String dbName = null;
    private String[] packageRegexes;
    private SqlExecution sqlExecution;

    @Override
    public <T> T finding(Class<T> clazz) {
        return this.daoDetector.finding(clazz);
    }

    public void init() {
        if (this.sqlExecution == null) throw new IllegalArgumentException("sqlExecution is null");
        if (this.packageRegexes == null) throw new IllegalArgumentException("packageRegex is null");
        FindSql findSql = new FindSql();
        LoadingArgs sqlAssembly = new LoadingArgs();
        sqlAssembly.setSqlAssembly(findSql);;
        RootSqlExecution sqlExcution = new RootSqlExecution();
        sqlExcution.setAssembly(sqlAssembly);
        sqlExcution.setExcution(this.sqlExecution);
        GenericDaoFactory daoFactory = new GenericDaoFactory();
        daoFactory.setSqlExecution(sqlExcution);
        AnnotationDaoDetector annotationDaoDetector = null;
        if (this.dbName == null) annotationDaoDetector = new AnnotationDaoDetector(this.packageRegexes);
        else annotationDaoDetector = new AnnotationDaoDetector(this.packageRegexes , this.dbName);
        annotationDaoDetector.setDaoFactory(daoFactory);
        DaoDetectorCach daoDetectorCach = new DaoDetectorCach();
        daoDetectorCach.setDaoDetector(annotationDaoDetector);
        this.daoDetector = daoDetectorCach;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setPackageRegexes(String[] packageRegexes) {
        this.packageRegexes = packageRegexes;
    }

    public void setSqlExecution(SqlExecution sqlExecution) {
        this.sqlExecution = sqlExecution;
    }

}

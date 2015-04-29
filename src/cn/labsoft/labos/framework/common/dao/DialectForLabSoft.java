package cn.labsoft.labos.framework.common.dao;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

public class DialectForLabSoft extends MySQL5Dialect{
	public DialectForLabSoft() { 
        super(); 
        registerHibernateType(Types.LONGVARCHAR, 65535, "text"); 
    } 
}

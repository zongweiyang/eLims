package cn.labsoft.labos.utils.exportexcel;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * <strong>Title : ExcelAnnotation Excel导出注解类</strong>. <br>
 * <strong>Description : Excel导出注解类</strong>. <br>
 * <strong>Create on : Jul 23, 2012 9:14:27 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi<br>
 * @version <strong>LabOS v1.1.1</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface ExcelAnnotation {
	// excel导出时标题显示的名字，如果没有设置Annotation属性，将不会被导出和导入
	public String exportName();
}

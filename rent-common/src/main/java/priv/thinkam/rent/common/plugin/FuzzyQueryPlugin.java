package priv.thinkam.rent.common.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
//模糊查询  插件
public class FuzzyQueryPlugin extends PluginAdapter {
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		InnerClass criteria = null;
		for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
			if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) {
				criteria = innerClass;
				break;
			}
		}
		if (criteria == null) {
			return true;
		}

		genTableColumnMethod(introspectedTable, criteria);
		genIsColumnExistMethod(criteria);
		genLikeMethod(criteria);
		genNotLikeMethod(criteria);

		return true;
	}

	private void genTableColumnMethod(IntrospectedTable introspectedTable, InnerClass criteria) {

		Method method = new Method();
		method.setVisibility(JavaVisibility.PROTECTED);
		method.setName("getStringColumns");
		method.setReturnType(FullyQualifiedJavaType.getStringInstance());

		StringBuilder sb = new StringBuilder();
		for (IntrospectedColumn introspectedColumn : introspectedTable.getNonBLOBColumns()) {
			if ((introspectedColumn.isJdbcCharacterColumn()) && (introspectedColumn.isStringColumn())) {
				String column = Ibatis2FormattingUtilities.getAliasedActualColumnName(introspectedColumn);
				sb.append(column).append(",");
			}
		}

		method.addBodyLine("return \"" + sb.toString() + "\";");

		criteria.addMethod(method);
	}

	private void genIsColumnExistMethod(InnerClass criteria) {

		Method method = new Method();
		method.setVisibility(JavaVisibility.PROTECTED);
		method.setName("isStringColumnExist");
		method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "value"));
		method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());

		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("String [] columns = getStringColumns().split(\",\");");
		method.addBodyLine(sb.toString());

		sb.setLength(0);
		sb.append("for (String s : columns) {");
		method.addBodyLine(sb.toString());

		sb.setLength(0);
		sb.append("if (s.equals(value)) {");
		method.addBodyLine(sb.toString());
		method.addBodyLine("return true;");
		method.addBodyLine("}");
		method.addBodyLine("}");

		sb.setLength(0);
		sb.append("return false;");
		method.addBodyLine(sb.toString());

		criteria.addMethod(method);
	}

	private void genLikeMethod(InnerClass criteria) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "field"));
		method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "value"));

		StringBuilder sb = new StringBuilder();
		sb.append("andGeneralLike");
		method.setName(sb.toString());
		method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());

		sb.setLength(0);
		sb.append("if (isStringColumnExist(field)) {");
		method.addBodyLine(sb.toString());

		sb.setLength(0);
		sb.append("addCriterion(field + \" like \", value, field);");
		method.addBodyLine(sb.toString());
		method.addBodyLine("}");
		method.addBodyLine("return (Criteria) this;");

		criteria.addMethod(method);
	}

	private void genNotLikeMethod(InnerClass criteria) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "field"));
		method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "value"));

		StringBuilder sb = new StringBuilder();
		sb.append("andGeneralNotLike");
		method.setName(sb.toString());
		method.setReturnType(FullyQualifiedJavaType.getCriteriaInstance());

		sb.setLength(0);
		sb.append("if (isStringColumnExist(field)) {");
		method.addBodyLine(sb.toString());

		sb.setLength(0);
		sb.append("addCriterion(field + \" not like \", value, field);");
		method.addBodyLine(sb.toString());

		method.addBodyLine("}");

		method.addBodyLine("return (Criteria) this;");

		criteria.addMethod(method);
	}
}

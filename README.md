# poc-java

# java-tiles
Exemplo do uso da biblioteca tiles para template de UI


0 - Adicionar a dependência no Maven do projeto

<dependency>
	<groupId>org.apache.tiles</groupId>
	<artifactId>tiles-extras</artifactId>
	<version>3.0.5</version>
</dependency>

1 - Incluir o listener do tiles no web.xml

<listener>
	<listener-class>
		org.apache.tiles.extras.complete.CompleteAutoloadTilesListener
	</listener-class>
</listener>

2 - Criar uma página que representa o layout

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
  <head>
    <title><tiles:getAsString name="title"/></title>
  </head>
  <body>
        <table>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="header" />
        </td>
      </tr>
      <tr>
        <td>
          <tiles:insertAttribute name="menu" />
        </td>
        <td>
          <tiles:insertAttribute name="body" />
        </td>
      </tr>
      <tr>
        <td colspan="2">
          <tiles:insertAttribute name="footer" />
        </td>
      </tr>
    </table>
  </body>
</html>

3 - Criar o tiles.xml em WEB-INF com a definição do template

<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE tiles-definitions PUBLIC
       "-//Apache Software Foundation//DTD Tiles Configuration 3.0//EN"
       "http://tiles.apache.org/dtds/tiles-config_3_0.dtd">
<tiles-definitions>
  <definition name="myapp.homepage" template="/layouts/classic.jsp">
    <put-attribute name="title" value="Tiles tutorial homepage" />
    <put-attribute name="header" value="/tiles/banner.jsp" />
    <put-attribute name="menu" value="/tiles/common_menu.jsp" />
    <put-attribute name="body" value="/tiles/home_body.jsp" />
    <put-attribute name="footer" value="/tiles/credits.jsp" />
  </definition>
</tiles-definitions>

4 - Criar cada uma das partes do template

5 - Utilizar o template em uma página da aplicação

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="myapp.homepage" />

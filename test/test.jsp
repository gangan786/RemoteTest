<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.meizhuo.myutils.*" %>
<%
    InputStream inputStream=new FileInputStream("D:\\Users\\mr.gan\\NormalJavaProject\\RemoteTest\\out\\production\\RemoteTest\\org\\meizhuo\\myutils\\Test.class");
    byte[] bytes = new byte[inputStream.available()];
    inputStream.read(bytes);
    inputStream.close();

    System.out.println("<textarea style='width:1000;height=800'>");
    System.out.println(JavaClassExecuter.execute(bytes));
    System.out.println("</textarea>");

%>
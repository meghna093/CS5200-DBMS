package edu.northeastern.cs5200.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import edu.northeastern.cs5200.model.HeadingWidget;
import edu.northeastern.cs5200.model.ImageWidget;
import edu.northeastern.cs5200.model.HtmlWidget;
import edu.northeastern.cs5200.model.Widget;
import edu.northeastern.cs5200.model.YoutubeWidget;

public class WidgetDao extends EstConnDao{
	private final String crtHeadingWidget = "INSERT into `widget` (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`size`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final String crtImageWidget = "INSERT into `widget` (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`src`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final String crtHtmlWidget = "INSERT into `widget` (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`html`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final String crtYoutubeWidget = "INSERT into `widget` (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`url`,`shareable`,`expandable`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String fndWidgetId = "SELECT * FROM `widget` WHERE `id`=?";
	private final String fndWidget = "SELECT * FROM `widget`";
	private final String fndWidgetName = "SELECT * FROM `widget` WHERE `name`=?";
	private final String selWidgetId = "SELECT * FROM `widget` JOIN `page` ON `widget`.`pageId`=`page`.`id` WHERE `page`.`title`= ? AND `widget`.`order`= (SELECT `x`.`value` FROM (SELECT max(`widget`.`order`) AS `value` FROM `widget`) AS `x`)";
	private final String fndWidgetPage = "SELECT * FROM `widget` WHERE `pageId`=?";
	private final String delWidget = "DELETE FROM `widget` WHERE `id`=?";
	private final String updtWidget = "UPDATE `widget` SET `widget`.`order`= CASE WHEN `widget`.`id`= ? THEN ?  WHEN (? < ? AND `widget`.`id` <> ? AND `widget`.`order`> ? AND `widget`.`order`<= ?) "
			+ "THEN `widget`.`order`- 1  WHEN (? > ? AND `widget`.`id` <> ? AND `widget`.`order`<= ? AND `widget`.`order`>= ? )  THEN `widget`.`order`+ 1  ELSE `widget`.`order` END WHERE `widget`.`pageId`= ?";
	
	public void dispWidgets(ArrayList<Widget>widgets) {
		for(Widget w : widgets) {
			System.out.println(w);
		}
	}
	private static WidgetDao instance = null;
	
	private WidgetDao() { }
	public static WidgetDao getInstance() {
	    if(instance == null) {
	        instance = new WidgetDao();
	    }
	    return instance;
	}
	public int fndFinalWidgetId(String pg_Title){
		int widget_id= -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(selWidgetId);
			statement.setString(1, pg_Title);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				widget_id=res.getInt("id");
			}
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return widget_id;
	}
	public int createWidgetForPage(int pageId,Widget widget) {
		int res = -1;
		if(widget instanceof HeadingWidget) {
			res = crtHeadingWidget(pageId,(HeadingWidget)widget);
		}
		else if(widget instanceof HtmlWidget) {
			res = crtHtmlWidget(pageId,(HtmlWidget)widget);
		}
		else if(widget instanceof ImageWidget) {
			res = crtImageWidget(pageId,(ImageWidget)widget);
		}
		else if(widget instanceof YoutubeWidget) {
			res = crtYoutubeWidget(pageId,(YoutubeWidget)widget);
		}
		return res;	
	}
	public int crtHtmlWidget(int pageId, HtmlWidget widget) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(crtHtmlWidget);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setString(9,widget.getHtml());
	    	statement.setInt(10,pageId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
			return res;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}
	public int crtHeadingWidget(int pageId, HeadingWidget widget) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(crtHeadingWidget);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setInt(9,widget.getSize());
	    	statement.setInt(10,pageId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
			return res;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	public Widget findWidgetById(int widgetId){
		Widget wdgt = new Widget();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWidgetId);
			statement.setInt(1, widgetId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String type = res.getString("type");
				int width = res.getInt("width");
				int height = res.getInt("height");
				String cssClass = res.getString("cssClass");
				String cssStyle = res.getString("cssStyle");
				String text = res.getString("text");
				int order = res.getInt("order");
				int size = res.getInt("size");
				String html = res.getString("html");
				String url = res.getString("url");
				boolean shareable = res.getBoolean("shareable");
				boolean expandable = res.getBoolean("expandable");
				String src = res.getString("src");
				int pageId = res.getInt("pageId");
				if(type.equals("heading")) {
					wdgt = new HeadingWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,size);
				}
				else if(type.equals("html")) {
					wdgt = new HtmlWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,html);
				}
				else if(type.equals("image")) {
					wdgt = new ImageWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,src);
				}
				else if(type.equals("youtube")) {
					wdgt = new YoutubeWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,
							url,shareable,expandable);
				}
			}
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return wdgt;
	}
	public int crtImageWidget(int pageId, ImageWidget widget) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(crtImageWidget);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setString(9,widget.getSrc());
	    	statement.setInt(10,pageId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
			return res;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}
	public ArrayList<Widget> findAllWidgets(){
		ArrayList<Widget>wgts = new ArrayList<Widget>();
		Widget widget = new Widget();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWidget);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String type = res.getString("type");
				int width = res.getInt("width");
				int height = res.getInt("height");
				String cssClass = res.getString("cssClass");
				String cssStyle = res.getString("cssStyle");
				String text = res.getString("text");
				int order = res.getInt("order");
				int size = res.getInt("size");
				String html = res.getString("html");
				String url = res.getString("url");
				boolean shareable = res.getBoolean("shareable");
				boolean expandable = res.getBoolean("expandable");
				String src = res.getString("src");
				int pageId = res.getInt("pageId");
				if(type.equals("heading")) {
					widget = new HeadingWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,size);
				}
				else if(type.equals("html")) {
					widget = new HtmlWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,html);
				}
				else if(type.equals("image")) {
					widget = new ImageWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,src);
				}
				else if(type.equals("youtube")) {
					widget = new YoutubeWidget(id,name,type,width,height,cssClass,cssStyle,text,order,
							pageId,url,shareable,expandable);
				}
				wgts.add(widget);
			}
			dispWidgets(wgts);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return wgts;
	}
	public int deleteWidget(int widgetId) {	
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(delWidget);
			statement.setInt(1,widgetId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}				
		return res;
	}
	public int crtYoutubeWidget(int pageId, YoutubeWidget widget) {
		int res = -1;
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(crtYoutubeWidget);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setString(9,widget.getUrl());
			statement.setBoolean(10,widget.isShareable());
			statement.setBoolean(11,widget.isExpandable());
	    	statement.setInt(12,pageId);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
			return res;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}
	public ArrayList<Widget> findWidgetsForPage(int pageId){
		ArrayList<Widget>widgets = new ArrayList<Widget>();
		Widget wdgt = new Widget();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWidgetPage);
			statement.setInt(1, pageId);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String type = res.getString("type");
				int width = res.getInt("width");
				int height = res.getInt("height");
				String cssClass = res.getString("cssClass");
				String cssStyle = res.getString("cssStyle");
				String text = res.getString("text");
				int order = res.getInt("order");
				int size = res.getInt("size");
				String html = res.getString("html");
				String url = res.getString("url");
				boolean shareable = res.getBoolean("shareable");
				boolean expandable = res.getBoolean("expandable");
				String src = res.getString("src");
				int pId = res.getInt("pageId");
				if(type.equals("heading")) {
					wdgt = new HeadingWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,size);
				}
				else if(type.equals("html")) {
					wdgt = new HtmlWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,html);
				}
				else if(type.equals("image")) {
					wdgt = new ImageWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,src);
				}
				else if(type.equals("youtube")) {
					wdgt = new YoutubeWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,url,shareable,expandable);
				}
				widgets.add(wdgt);
			}
			dispWidgets(widgets);
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return widgets;
	}
	public Widget findWidgetByName(String widgetName){
		Widget wdgt = new Widget();
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(fndWidgetName);
			statement.setString(1, widgetName);
			ResultSet res = statement.executeQuery(); 
			while(res.next()) {
				int id=res.getInt("id");
				String name = res.getString("name");
				String type = res.getString("type");
				int order = res.getInt("order");
				int pageId = res.getInt("pageId");
				wdgt = new Widget(id,name,type,order,pageId);
				}
			statement.close();
			conn.close();	
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return wdgt;
	}
	public int updateWidget(int widgetId,Widget widget) {
		int res = -1;
		int pg_Id = findWidgetById(widgetId).getPageId();
		int pre_order = findWidgetById(widgetId).getOrder();
		int post_order = widget.getOrder();	
		try {
			Class.forName(jdbc_drvr);
			Connection conn = DriverManager.getConnection(url, user_name, psswd);
			PreparedStatement statement = null;
			statement = conn.prepareStatement(updtWidget);
			statement.setInt(1, widgetId);
			statement.setInt(2, post_order);
			statement.setInt(3, pre_order);
		    statement.setInt(4, post_order);
		    statement.setInt(5, widgetId);
		    statement.setInt(6, pre_order);
		    statement.setInt(7, post_order);
		    statement.setInt(8, pre_order);
		    statement.setInt(9, post_order);
		    statement.setInt(10, widgetId);
		    statement.setInt(11, pre_order);
		    statement.setInt(12, post_order);
	    	statement.setInt(13, pg_Id);
			res = statement.executeUpdate(); 
			statement.close();
			conn.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return res;
	}
}

package edu.northeastern.cs5200;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import edu.northeastern.cs5200.model.Address;
import edu.northeastern.cs5200.model.AddressDao;
import edu.northeastern.cs5200.model.Developer;
import edu.northeastern.cs5200.model.HeadingWidget;
import edu.northeastern.cs5200.model.DeveloperDao;
import edu.northeastern.cs5200.model.HtmlWidget;
import edu.northeastern.cs5200.model.Page;
import edu.northeastern.cs5200.model.ImageWidget;
import edu.northeastern.cs5200.model.PageDao;
import edu.northeastern.cs5200.model.Phone;
import edu.northeastern.cs5200.model.PhoneDao;
import edu.northeastern.cs5200.model.Website;
import edu.northeastern.cs5200.model.RoleDao;
import edu.northeastern.cs5200.model.RoleEnumeratorDao;
import edu.northeastern.cs5200.model.User;
import edu.northeastern.cs5200.model.UserDao;
import edu.northeastern.cs5200.model.YoutubeWidget;
import edu.northeastern.cs5200.model.WebsiteDao;
import edu.northeastern.cs5200.model.WidgetDao;
import edu.northeastern.cs5200.model.Widget;

@SpringBootApplication
public class hw3_jdbc_venkatesha_meghna extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(hw3_jdbc_venkatesha_meghna.class);
	}
	private static DeveloperDao dev = DeveloperDao.getInstance();
	private static UserDao usr = UserDao.getInstance();
	private static RoleEnumeratorDao roleenum = RoleEnumeratorDao.getInstance();
	private static WebsiteDao web = WebsiteDao.getInstance();
	private static RoleDao role = RoleDao.getInstance();
	private static WidgetDao widget = WidgetDao.getInstance();
	private static PageDao page = PageDao.getInstance();
	private static AddressDao add = AddressDao.getInstance();
	private static PhoneDao phone = PhoneDao.getInstance();
	
	public static void main(String[] args) {
		System.out.println("Inserting Data.....");
		// Creating Developers:
		Developer alice = new Developer("Alice","Wonder","alice","alice","alice@wonder.com","4321rewq");
		dev.createDeveloper(alice);
		Developer bob = new Developer("Bob","Marley","bob","bob","bob@marley.com","5432trew");
		dev.createDeveloper(bob);
		Developer charlie = new Developer("Charles","Garcia","charlie","charlie","chuch@garcia.com","6543ytre");
		dev.createDeveloper(charlie);
		
		int aliceId = dev.findDeveloperByUsername("alice").getId();
		int charlieId = dev.findDeveloperByUsername("charlie").getId();
		int bobId = dev.findDeveloperByUsername("bob").getId();
		
		// Creating Users:
		User dan = new User("Dan", "Martin", "dan", "dan", "dan@martin.com","1","7654fda");
		usr.createUser(dan);
		User ed = new User("Ed","Karaz", "ed", "ed", "ed@kar.com","0","5678dfgh");
		usr.createUser(ed);
		
		Address alice1 = new Address("123","234","Boston","MA","02120",true);
		add.createAddress(1, alice1);
		
		Phone ph3 = new Phone ("222-333-4444",true);
		phone.createPhone(charlieId, ph3);
		
		roleenum.insrtPriviledges("delete");
		roleenum.insrtPriviledges("create");
		roleenum.insrtPriviledges("update");
		roleenum.insrtPriviledges("read");
		roleenum.insrtRoles("owner");
		roleenum.insrtRoles("writer");
		roleenum.insrtRoles("admin");
		roleenum.insrtRoles("reviewer");
		roleenum.insrtRoles("editor");
		
		// Creating Websites:
		Website facebook = new Website("Facebook","an online social media and social networking service",1234234);
		web.createWebsiteForDeveloper(aliceId, facebook);	
		Website twitter = new Website("Twitter","an online news and social networking service",4321543);
		web.createWebsiteForDeveloper(bobId, twitter);	
		Website wikipedia = new Website("Wikipedia","a free online encyclopedia",3456654);
		web.createWebsiteForDeveloper(charlieId, wikipedia);	
		Website cnn = new Website("CNN","an American basic cable and satellite television news channel",6543345);
		web.createWebsiteForDeveloper(aliceId, cnn);	
		Website cnet = new Website("CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",5433455);
		web.createWebsiteForDeveloper(bobId, cnet);	
		Website gizmodo = new Website("Gizmodo","a design, technology, science and science fiction website that also writes articles on politics",4322345);
		web.createWebsiteForDeveloper(charlieId, gizmodo);
				
		int ownerRoleId = roleenum.fndRoleIds("owner");
		int adminRoleId = roleenum.fndRoleIds("admin");	
		int editorRoleId = roleenum.fndRoleIds("editor");
		int writerRoleId = roleenum.fndRoleIds("writer");
		int reviewerRoleId = roleenum.fndRoleIds("reviewer");
		int facebookId = web.findWebsiteByName("Facebook").getId();
		int wikipediaId = web.findWebsiteByName("Wikipedia").getId();
		int twitterId = web.findWebsiteByName("Twitter").getId();
		int cnnId = web.findWebsiteByName("CNN").getId();
		int gizmodoId = web.findWebsiteByName("Gizmodo").getId();
		int cnetId = web.findWebsiteByName("CNET").getId();
		
		role.assignWebsiteRole(aliceId, facebookId, ownerRoleId);
		role.assignWebsiteRole(charlieId, wikipediaId, ownerRoleId);
		role.assignWebsiteRole(bobId, twitterId, ownerRoleId);
		role.assignWebsiteRole(aliceId, cnnId, ownerRoleId);
		role.assignWebsiteRole(charlieId, gizmodoId, ownerRoleId);
		role.assignWebsiteRole(bobId, cnetId, ownerRoleId);
		role.assignWebsiteRole(charlieId, facebookId, adminRoleId);
		role.assignWebsiteRole(bobId, wikipediaId, adminRoleId);
		role.assignWebsiteRole(aliceId, twitterId, adminRoleId);
		role.assignWebsiteRole(charlieId, cnnId, adminRoleId);
		role.assignWebsiteRole(bobId, gizmodoId, adminRoleId);
		role.assignWebsiteRole(aliceId, cnetId, adminRoleId);	
		role.assignWebsiteRole(bobId, facebookId, editorRoleId);
		role.assignWebsiteRole(aliceId, wikipediaId, editorRoleId);
		role.assignWebsiteRole(charlieId, twitterId, editorRoleId);
		role.assignWebsiteRole(bobId, cnnId, editorRoleId);
		role.assignWebsiteRole(aliceId, gizmodoId, editorRoleId);
		role.assignWebsiteRole(charlieId, cnetId, editorRoleId);
		
		// Creating Pages:
		Page cnetHome = new Page("Home","Landing page",123434);
		page.createPageForWebsite(cnetId,cnetHome);	
		Page gizmodoAbout = new Page("About","Website description",234545);
		page.createPageForWebsite(gizmodoId,gizmodoAbout);		
		Page wikiContact = new Page("Contact","Addresses, phones, and contact info",345656);
		page.createPageForWebsite(wikipediaId,wikiContact);		
		Page cnnPreferences = new Page("Preferences","Where users can configure their preferences",456776);
		page.createPageForWebsite(cnnId,cnnPreferences);		
		Page cnetProfile = new Page("Profile","Users can configure their personal information",567878);
		page.createPageForWebsite(cnetId,cnetProfile);
				
		int cnetHomeId = page.fndPagesTitle("Home", cnetId).getId();
		int wikiContactId = page.fndPagesTitle("Contact", wikipediaId).getId();
		int gizmodoAboutId = page.fndPagesTitle("About", gizmodoId).getId();
		int cnetProfileId = page.fndPagesTitle("Profile", cnetId).getId();
		int cnnPrefId = page.fndPagesTitle("Preferences", cnnId).getId();
		
		role.assignPageRole(aliceId, cnetHomeId, editorRoleId);
		role.assignPageRole(charlieId, wikiContactId, editorRoleId);
		role.assignPageRole(bobId, gizmodoAboutId, editorRoleId);
		role.assignPageRole(bobId, cnetProfileId, editorRoleId);
		role.assignPageRole(aliceId, cnnPrefId, editorRoleId);
		role.assignPageRole(charlieId, cnetHomeId, writerRoleId);
		role.assignPageRole(bobId, wikiContactId, writerRoleId);
		role.assignPageRole(aliceId, gizmodoAboutId, writerRoleId);
		role.assignPageRole(aliceId, cnetProfileId,writerRoleId);
		role.assignPageRole(charlieId, cnnPrefId, writerRoleId);
		role.assignPageRole(bobId, cnetHomeId, reviewerRoleId);
		role.assignPageRole(aliceId, wikiContactId, reviewerRoleId);
		role.assignPageRole(charlieId, gizmodoAboutId, reviewerRoleId);
		role.assignPageRole(charlieId, cnetProfileId, reviewerRoleId);
		role.assignPageRole(bobId, cnnPrefId, reviewerRoleId);
		
		// Creating Widgets:
		HeadingWidget headingWidget = new HeadingWidget("head123","heading","Welcome",0,2); 
		widget.createWidgetForPage(cnetHomeId, headingWidget);		
		HtmlWidget htmlWidget = new HtmlWidget("post234","html","<p>Lorem</p>",0); 
		widget.createWidgetForPage(gizmodoAboutId, htmlWidget);		
		HeadingWidget headingWidget2 = new HeadingWidget("head345","heading","Hi",1,2); 
		widget.createWidgetForPage(wikiContactId, headingWidget2);		
		HtmlWidget htmlWidget2 = new HtmlWidget("intro456","html","<h1>Hi</h1>",2); 
		widget.createWidgetForPage(wikiContactId, htmlWidget2);		
		ImageWidget imageWidget = new ImageWidget("image345","image",50,100,3,"/img/567.png"); 
		widget.createWidgetForPage(wikiContactId, imageWidget);		
		YoutubeWidget youtubeWidget = new YoutubeWidget("video456","youtube",400,300,0,"https://youtu.be/h67VX51QXiQ"); 
		widget.createWidgetForPage(cnnPrefId, youtubeWidget);
		
		System.out.println("Developres Inserted:");
		dev.findAllDevelopers();
		System.out.println("*******************\nUsers Inserted:");
		usr.findAllUsers();
		System.out.println("*******************\nWidgets Inserted:");
		widget.findAllWidgets();
		System.out.println("*******************\nWebsites Inserted:");
		web.findAllWebsites();
		System.out.println("*******************\nPages Inserted:");
		page.findAllPages();
		
		System.out.println("Executing update query 1");
		// Update Query 1:	
		Phone ph1 = phone.findPhoneById(1);
		ph1.setPhone("333-444-5555");
		phone.updatePhone(1, ph1);
		
		System.out.println("Executing update query 2");
		// Update Query 2:
		Widget wHead = new Widget("head345",2);
		widget.updateWidget(widget.findWidgetByName("head345").getId(),wHead);
		
		System.out.println("Executing update query 3");
		// Update Query 3: 
		ArrayList<Page> pages = page.findPagesForWebsite(cnetId);
		for(Page pg: pages) {
			page.updatePage(pg.getId(), new Page("CNET - "+pg.getTitle()));
		}
		
		System.out.println("Executing update query 4");
		// Update Query 4:
		role.switchRoles(bobId, charlieId, cnetHomeId);
		
		System.out.println("Executing delete query 1");
		// Delete Query 1: 
		for (Address add1 : add.findAddressesForPerson(aliceId)) {
			if (add1.isPrimary()) {
				add.deleteAddress(add1.getID());
			}
			else
				break;
			}
		
		System.out.println("Executing delete query 2");
		// Delete Query 2: 
		widget.deleteWidget(widget.fndFinalWidgetId("Contact"));
		
		System.out.println("Executing delete query 3");
		// Delete Query 3:  
		page.deletePage(page.fndRecentUpdtPage("Wikipedia"));
		
		System.out.println("Executing delete query 4");
		// Delete Query 4: 
		web.deleteWebsite(cnetId);
}
	
	
}

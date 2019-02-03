Retrieve developers:
Retrieve all developers:

SELECT `person`.`username`,`person`.`password`,`person`.`firstname`,`person`.`lastname`,`person`.`email`,`developer`.`developerKey`
FROM `person` JOIN `developer`
ON `person`.`id`= `developer`.`id`;

#########################################################################################

Retrieve a developer with id equal to 34:

SELECT `person`.`username`,`person`.`password`,`person`.`firstname`,`person`.`lastname`,`person`.`email`,`developer`.`developerKey`
FROM `person` JOIN `developer`
ON `person`.`id`= `developer`.`id`
WHERE `developer`.`id`=34;

#########################################################################################

Retrieve all developers who have a role in Twitter other than owner:

SELECT `person`.`username`,`person`.`password`,`person`.`firstname`,`person`.`lastname`,`person`.`email`,`developer`.`developerKey`
FROM `websiterole`,`website`,`developer`,`role`,`person`
WHERE `websiterole`.`webId`=`website`.`id` AND `websiterole`.`devId`=`developer`.`id` AND `websiterole`.`roleId`=`role`.`id` AND `developer`.`id`=`person`.`id`
AND `website`.`name`= 'Twitter' AND `role`.`name`<>'owner';

#########################################################################################

Retrieve all developers who are page reviewers of pages with less than 300000
visits:

SELECT `person`.`username`,`person`.`password`,`person`.`firstname`,`person`.`lastname`,`person`.`email`,`developer`.`developerKey`,`page`.`visits`
FROM `pagerole`,`page`,`developer`,`role`,`person`
WHERE `pagerole`.`pageId`=`page`.`id` AND `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`roleId`=`role`.`id` AND `developer`.`id`=`person`.`id`
AND `page`.`visits`<300000 AND `role`.`name`='reviewer';

#########################################################################################

Retrieve the writer developer who added a heading widget to CNETs home page:

SELECT `person`.`username`,`person`.`password`,`person`.`firstname`,`person`.`lastname`,`person`.`email`,`developer`.`developerKey`
FROM `pagerole`,`website`,`page`,`widget`,`developer`,`role`,`person`
WHERE `pagerole`.`pageId`=`page`.`id` AND `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`roleId`=`role`.`id` AND `page`.`webId`=`website`.`id`
AND `widget`.`pageId`=`page`.`id` AND `developer`.`id`=`person`.`id` AND `role`.`name`='writer' AND `widget`.`type`='heading' AND `page`.`title` ='Home' 
AND `website`.`name`='CNET';

#########################################################################################
#########################################################################################

Retrieve websites:
Retrieve the website with the least number of visits:

SELECT `website`.`name` 
FROM `website`
WHERE `website`.`visits`=(SELECT min(`website`.`visits`) FROM `website`);

#########################################################################################

Retrieve the name of a website whose id is 678:

SELECT `website`.`name` FROM `website`
WHERE `website`.`id`=678;

#########################################################################################

Retrieve all websites with videos reviewed by bob:

SELECT `website`.`name`
FROM `pagerole`,`website`,`page`,`widget`,`developer`,`role`,`person`
WHERE `pagerole`.`pageId`=`page`.`id` AND `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`roleId`=`role`.`id` AND `page`.`webId`=`website`.`id` AND `widget`.`pageId`=`page`.`id` AND `developer`.`id`=`person`.`id` AND `person`.`firstname`='Bob' AND `role`.`name`='reviewer' AND `widget`.`type`='youtube';

#########################################################################################

Retrieve all websites where alice is an owner:

SELECT `website`.`name`
FROM `websiterole`,`website`,`developer`,`role`,`person`
WHERE `websiterole`.`webId`=`website`.`id` AND `websiterole`.`devId`=`developer`.`id` AND `websiterole`.`roleId`=`role`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`= 'Alice' AND `role`.`name`='owner';

#########################################################################################

Retrieve all websites where charlie is an admin and get more than 6000000 visits:

SELECT `website`.`name` 
FROM `websiterole`,`website`,`developer`,`role`,`person`
WHERE `websiterole`.`webId`=`website`.`id` AND `websiterole`.`devId`=`developer`.`id` AND `websiterole`.`roleId`=`role`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`= 'Charles' AND `role`.`name`='admin' AND `website`.`visits`> 6000000;

#########################################################################################
#########################################################################################

Retrieve pages:
Retrieve the page with the most number of views:

SELECT * 
FROM `page`
WHERE `page`.`visits`=(SELECT max(`page`.`visits`) FROM `page`);

#########################################################################################

Retrieve the title of a page whose id is 234:

SELECT `page`.`title` 
FROM `page`
WHERE `page`.`id`= 234;

#########################################################################################

Retrieve all pages where alice is an editor:

SELECT `page`.`title`
FROM `pagerole`,`page`,`developer`,`role`,`person`
WHERE `pagerole`.`pageId`=`page`.`id` AND `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`roleId`=`role`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`='Alice' AND `role`.`name`='editor';

#########################################################################################

Retrieve the total number of pageviews in CNET:

SELECT `website`.`name`,SUM(`page`.`visits`) AS  'pageviews'
FROM `page` JOIN `website`
ON `page`.`webId`=`website`.`id`
WHERE `website`.`name`='CNET';

#########################################################################################

Retrieve the average number of page views in the Web site Wikipedia:

SELECT `website`.`name`,AVG(`page`.`visits`) AS  'average number of page views'
FROM `page` JOIN `website`
ON `page`.`webId`=`website`.`id`
where `website`.`name`='Wikipedia';

#########################################################################################
#########################################################################################

Retrieve widgets:
Retrieve all widgets in CNETs Home page:

SELECT `widget`.`name` AS 'Widgets',`widget`.`type`, `widget`.`text`,`widget`.`ordr`,`widget`.`height`,  `widget`.`width`, `widget`.`size`, `widget`.`url`
FROM  `widget` JOIN `page` JOIN `website`
ON `widget`.`pageId`=`page`.`id` AND `page`.`webId`=`website`.`id`
WHERE `website`.`name`='CNET' AND `page`.`title`='Home';

#########################################################################################

Retrieve all youtube widgets in CNN:

SELECT `widget`.`name` AS 'Widgets',`widget`.`type`, `widget`.`text`,`widget`.`ordr`,`widget`.`height`,  `widget`.`width`, `widget`.`size`, `widget`.`url`
FROM  `widget` JOIN `page` JOIN `website`
ON `widget`.`pageId`=`page`.`id` AND `page`.`webId`=`website`.`id`
WHERE `website`.`name`='CNN' AND `widget`.`type`='youtube';

#########################################################################################

Retrieve all image widgets on pages reviewed by Alice:

SELECT `widget`.`name` AS 'Widgets',`widget`.`type`, `widget`.`text`,`widget`.`ordr`,`widget`.`height`,  `widget`.`width`, `widget`.`size`, `widget`.`url`,`page`.`title` AS 'page'
FROM `pagerole`,`page`,`widget`,`developer`,`role`,`person`
WHERE `pagerole`.`pageId`=`page`.`id` AND `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`roleId`=`role`.`id` AND `widget`.`pageId`=`page`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`='Alice' AND `role`.`name`='reviewer' AND `widget`.`type`='image';

#########################################################################################

Retrieve how many widgets are in Wikipedia:

SELECT `website`.`name` AS 'website',COUNT(`widget`.`id`) AS 'Widgets Count'
FROM `website`,`page`,`widget`
WHERE `widget`.`pageId`=`page`.`id` AND `page`.`webId`=`website`.`id` AND `website`.`name`='Wikipedia';

#########################################################################################
#########################################################################################
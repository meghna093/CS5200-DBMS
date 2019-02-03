Creating the following developers and users.Inserting into the correct tables depending on the type:

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`person`
(`id`,`firstname`,`lastname`,`username`,`password`,`email`,`dob`)
VALUES
(12,'Alice','Wonder','alice','alice','alice@wonder.com',null),
(23,'Bob','Marley','bob','bob','bob@marley.com',null),
(34,'Charles','Garcia','charlie','charlie','chuch@garcia.com',null),
(45,'Dan','Martin','dan','dan','dan@martin.com',null),
(56,'Ed','Karaz','ed','ed','ed@kar.com',null);

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`user`
(`id`,`userAgreement`,`userKey`)
VALUES
((SELECT `id` FROM `person` WHERE `person`.`firstname`='Dan'),1,'7654fda'),
((SELECT `id` FROM `person` WHERE `person`.`firstname`='Ed'),1,'5678dfgh');

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`developer`
(`id`,`developerKey`)
VALUES
((SELECT `id` FROM `person` WHERE `person`.`firstname`='Alice'),'4321rewq'),
((SELECT `id` FROM `person` WHERE `person`.`firstname`='Bob'),'5432trew'),
((SELECT `id` FROM `person` WHERE `person`.`firstname`='Charles'),'6543ytre');

#########################################################################################
#########################################################################################
Creating the following web sites for the developers above:

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`website`
(`id`,`name`,`description`,`created`,`updated`,`visits`,`devId`)
VALUES
(123,'Facebook','an online social media and social networking service',curdate(),curdate(),1234234,(SELECT `id` FROM `person` WHERE `person`.`firstname`='Alice')),
(234,'Twitter','an online news and social networking service',curdate(),curdate(),4321543,(SELECT `id` FROM `person` WHERE `person`.`firstname`='Bob')),
(345,'Wikipedia','a free online encyclopedia',curdate(),curdate(),3456654,(SELECT `id` FROM `person` WHERE `person`.`firstname`='Charles')),
(456,'CNN','an American basic cable and satellite television news channel',curdate(),curdate(),6543345,(SELECT `id` FROM `person` WHERE `person`.`firstname`='Alice')),
(567,'CNET','an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics',curdate(),curdate(),5433455, (SELECT `id` FROM `person` WHERE `person`.`firstname`='Bob')),
(678,'Gizmodo','a design, technology, science and science fiction website that also writes articles on politics',curdate(),curdate(),4322345, (SELECT `id` FROM `person` WHERE `person`.`firstname`='Charles'));

#########################################################################################
#########################################################################################
Creating the following pages for the web sites above:

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`page`
(`id`,`title`,`description`,`created`,`updated`,`visits`,`webId`)
VALUES
(123, 'Home','Landing Page','2018-01-09','2018-02-11',123434,(SELECT `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`="CNET")),
(234, 'About','Website description','2018-01-09','2018-02-11',234545,(SELECT `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`="Gizmodo")),
(345, 'Contact','Addresses, phones, and contact info','2018-01-09','2018-02-11',345656,(SELECT `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`="Wikipedia")),
(456, 'Preferences','Where users can configure their preferences','2018-01-09','2018-02-11',456776,(SELECT `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`="CNN")),
(567, 'Profile','Users can configure their personal information','2018-01-09','2018-02-11',567878,(SELECT `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`="CNET"));

#########################################################################################
#########################################################################################
Creating the following widgets for the pages shown:

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`widget`
(`id`,`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`ordr`,`size`,`html`,`url`,`shareable`,`expandable`,`src`,`pageId`)
VALUES
(123,'head123','heading',null,null,null,null,'Welcome',0,null,null,null,null,null,null,(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Home' AND `website`.`name`='CNET')),
(234,'post234','html',null,null,null,null,'<p>Lorem</p>',0,null,null,null,null,null,null,(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo')),
(345,'head345','heading',null,null,null,null,'Hi',1,null,null,null,null,null,null,(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia')),
(456,'intro456','html',null,null,null,null,'<h1>Hi</h1>',2,null,null,null,null,null,null,(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia')),
(567,'image345','image',50,100,null,null,null,3,null,'/img/567.png',null,null,null,null,(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia')),
(678,'video456','youtube',400,300,null,null,null,0,null,'https://youtu.be/h67VX51QXiQ',null,null,null,null,(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'));

#########################################################################################
#########################################################################################
Creating the following phones and addresses for the users or developers shown:

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`address`
(`id`,`street1`,`street2`,`city`,`state`,`zip`,`primry`,`personId`)
VALUES
(1,'123 Adam St.',null,'Alton',null,'01234',1,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice')),
(2,'234 Birch St.',null,'Boston',null,'02345',0,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice')),
(3,'345 Charles St.',null,'Chelms',null,'03455',1,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob')),
(4,'456 Down St.',null,'Dalton',null,'04566',0,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob')),
(5,'543 East St.',null,'Everett',null,'01112',0,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob')),
(6,'654 Frank St.',null,'Foulton',null,'04322',1,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person`WHERE `person`.`firstname`='Charles'));

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`phone`
(`id`,`phone`,`primry`,`personId`)
VALUES
(1,'123-234-3456',1,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice')),
(2,'234-345-4566',0,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice')),
(3,'345-456-5677',1,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob')),
(4,'321-432-5435',1,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles')),
(5,'432-432-5433',0,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles')),
(6,'543-543-6544',0,(SELECT `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'));

#########################################################################################
#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
SELECT  `website`.`devId`, `website`.`id`,`role`.`id`
FROM `hw2_venkatesha_meghna_spring_2018`.`website`,`hw2_venkatesha_meghna_spring_2018`.`role`
WHERE `website`.`name`='Twitter' AND `role`.`name`='owner';

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
SELECT  `website`.`devId`, `website`.`id`,`role`.`id`
FROM `hw2_venkatesha_meghna_spring_2018`.`website`,`hw2_venkatesha_meghna_spring_2018`.`role`
WHERE `website`.`name`='Facebook' AND `role`.`name`='owner';

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
SELECT  `website`.`devId`, `website`.`id`,`role`.`id`
FROM `hw2_venkatesha_meghna_spring_2018`.`website`,`hw2_venkatesha_meghna_spring_2018`.`role`
WHERE `website`.`name`='Wikipedia' AND `role`.`name`='owner';

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
SELECT  `website`.`devId`, `website`.`id`,`role`.`id`
FROM `hw2_venkatesha_meghna_spring_2018`.`website`,`hw2_venkatesha_meghna_spring_2018`.`role`
WHERE `website`.`name`='CNET' AND `role`.`name`='owner';

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
SELECT  `website`.`devId`, `website`.`id`,`role`.`id`
FROM `hw2_venkatesha_meghna_spring_2018`.`website`,`hw2_venkatesha_meghna_spring_2018`.`role`
WHERE `website`.`name`='CNN' AND `role`.`name`='owner';

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
SELECT  `website`.`devId`, `website`.`id`,`role`.`id`
FROM `hw2_venkatesha_meghna_spring_2018`.`website`,`hw2_venkatesha_meghna_spring_2018`.`role`
WHERE `website`.`name`='Gizmodo' AND `role`.`name`='owner';

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`role`
(`Id`,`name`)
VALUES
(1,'owner'),(2,'admin'),(3,'writer'),(4,'editor'),(5,'reviewer');

#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`websiterole`
(`devId`,`webId`,`roleId`)
VALUES
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Twitter'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Facebook'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Wikipedia'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='CNN'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Facebook'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='admin')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Gizmodo'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Wikipedia'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='admin')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Twitter'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='admin')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='admin')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='CNN'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='admin')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Gizmodo'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='admin'))
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Facebook'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='owner')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='CNN'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='owner')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Twitter'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='owner')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='owner')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Wikipedia'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='owner')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT  `website`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`website` WHERE `website`.`name`='Gizmodo'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='owner'));

#########################################################################################
#########################################################################################

INSERT INTO `hw2_venkatesha_meghna_spring_2018`.`pagerole`
(`devId`,`pageId`,`roleId`)
VALUES
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Home' AND `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Home' AND `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='reviewer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Profile' AND `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='editor')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='reviewer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='reviewer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Profile' AND `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='reviewer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='reviewer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='About' AND `website`.`name`='Gizmodo'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='writer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Home' AND `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='writer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Charles'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Preferences' AND `website`.`name`='CNN'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='writer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Bob'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Contact' AND `website`.`name`='Wikipedia'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='writer')),
((SELECT  `person`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`person` WHERE `person`.`firstname`='Alice'),
(SELECT `page`.`id` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` WHERE `page`.`title`='Profile' AND `website`.`name`='CNET'),
(SELECT `role`.`id` FROM `hw2_venkatesha_meghna_spring_2018`.`role` WHERE `role`.`name`='writer'));

#########################################################################################
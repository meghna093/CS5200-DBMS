#(SET SQL_SAFE_UPDATES=0;)

->Delete developer - Delete Alices primary address:

DELETE `address` FROM `address` JOIN `person` ON `address`.`personId`=`person`.`id`
WHERE `person`.`firstname`='Alice' AND `address`.`primry`=1;

#########################################################################################

->Delete widget - Remove the last widget in the Contact page. The last widget is
the one with the highest value in the order field:

DELETE `widget` FROM `widget` JOIN `page` ON `widget`.`pageId`=`page`.`id`
WHERE `page`.`title`='Contact' AND `widget`.`ordr`= (SELECT `a`.`value` FROM (SELECT max(`widget`.`ordr`) AS `value` FROM `widget`) AS `a`);

#########################################################################################

->Delete page - Remove the last updated page in Wikipedia:

DELETE `page` FROM `page` JOIN `website` ON `page`.`webId`=`website`.`id` 
WHERE `website`.`name`='Wikipedia' AND `page`.`updated`= (SELECT `a`.`maxdate` FROM (SELECT max(`page`.`updated`) AS `maxdate` FROM `page`) AS `a`);

#########################################################################################

->Delete website - Remove the CNET web site, as well as all related roles and
privileges relating developers to the Website and Pages:

DELETE FROM `website` WHERE `website`.`name`='CNET';

#########################################################################################
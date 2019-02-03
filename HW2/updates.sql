->Update developer - Update Charlies primary phone number to 333-444-5555:

UPDATE `phone` SET `phone` = '333-444-5555'
WHERE `phone`.`primry` = 1 AND `phone`.`personId`=(SELECT `person`.`id` FROM `person` WHERE `person`.`firstname`='Charles');

#########################################################################################

->Update widget - Update the relative order of widget head345 on the page so that
its new order is 3. Note that the other widgets order needs to update as well:

SELECT `widget`.`pageId` FROM `widget` WHERE `widget`.`name`='head345' INTO @store;
SELECT `widget`.`ordr` FROM `widget` WHERE `widget`.`name`='head345' INTO @pre;
set @post:=3;
UPDATE `widget` SET `widget`.`ordr`= CASE WHEN `widget`.`name`= 'head345' THEN @post WHEN (@pre < @post AND `widget`.`name` <>'head345' AND `widget`.`ordr`> @pre AND `widget`.`ordr`<= @post) THEN `widget`.`ordr`- 1 WHEN (@pre > @post AND `widget`.`name` <>'head345' AND `widget`.`ordr`<= @pre) THEN `widget`.`ordr`+ 1 ELSE `widget`.`ordr` END WHERE `widget`.`pageId`= @store;

#########################################################################################

->Update page - Append 'CNET - ' to the beginning of all CNETs page titles:

UPDATE `page` SET `page`.`title`=CONCAT('CNET',`page`.`title`)
WHERE `page`.`webId`=(SELECT `website`.`id` FROM `website` WHERE `website`.`name`='CNET');

#########################################################################################

->Update roles - Swap Charlie's and Bob's role in CNETs Home page:

SELECT `pagerole`.`roleId` FROM `pagerole`,`developer`,`page`,`website`,`person`
WHERE `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`pageId`=`page`.`id` AND `page`.`webId`=`website`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`='Bob' AND `website`.`name`='CNET' AND `page`.`title`='CNETHome' INTO @bobrole;
SELECT `pagerole`.`id` FROM `pagerole`,`developer`,`page`,`website`,`person`
WHERE `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`pageId`=`page`.`id` AND `page`.`webId`=`website`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`='Bob' AND `website`.`name`='CNET' AND `page`.`title`='CNETHome' INTO @bobid;
SELECT `pagerole`.`id` FROM `pagerole`,`developer`,`page`,`website`,`person`
WHERE `pagerole`.`devId`=`developer`.`id` AND `pagerole`.`pageId`=`page`.`id` AND `page`.`webId`=`website`.`id` AND `developer`.`id`=`person`.`id`
AND `person`.`firstname`='Charles' AND `website`.`name`='CNET'AND `page`.`title`='CNETHome' INTO @charlieid;
UPDATE `pagerole` AS `pr1`, `pagerole` AS `pr2` 
SET `pr1`.`roleId` = `pr2`.`roleId`, `pr2`.`roleId`= @bobrole
WHERE (`pr1`.`id`,`pr2`.`id`)=(@bobid,@charlieid);
#########################################################################################	
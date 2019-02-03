 CREATE SCHEMA hw2_venkatesha_meghna_spring_2018 DEFAULT CHARACTER SET utf8;

#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `dob` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  UNIQUE INDEX `username_uniq` (`username` ASC),
  UNIQUE INDEX `email_uniq` (`email` ASC));
  
#########################################################################################  
 
  CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userAgreement` BOOLEAN NOT NULL,
  `userKey` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  UNIQUE INDEX `userKey_uniq` (`userKey` ASC),
  CONSTRAINT `user_person_generalization`
    FOREIGN KEY (`id`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################
	
 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`developer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `developerKey` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  UNIQUE INDEX `developerKey_uniq` (`developerKey` ASC),
  CONSTRAINT `developer_person_generalization`
    FOREIGN KEY (`id`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NOT NULL,
  `primry` BOOLEAN NULL,
  `personId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `person_ind` (`personId` ASC),
  CONSTRAINT `phone_person`
    FOREIGN KEY (`personId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);	
	
#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street1` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `primry` BOOLEAN NULL,
  `personId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `address-person_ind` (`personId` ASC),
  CONSTRAINT `address_person`
    FOREIGN KEY (`personId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################	
 
 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`website` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `visits` INT NULL,
  `devId` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  UNIQUE KEY `name_uniq` (`name`),
  INDEX `developer_ind` (`devId` ASC),
  CONSTRAINT `website_developer`
    FOREIGN KEY (`devId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`developer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`page` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `created` DATE NULL,
  `updated` DATE NULL,
  `visits` INT NULL,
  `webId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `website_ind` (`webId` ASC),
  CONSTRAINT `page_website`
    FOREIGN KEY (`webId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`widget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `width` INT NULL,
  `height` INT NULL,
  `cssClass` VARCHAR(45) NULL,
  `cssStyle` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `ordr` INT NULL,
  `size` INT NULL DEFAULT 2,
  `html` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `shareable` BOOLEAN NULL,
  `expandable` BOOLEAN NULL,
  `src` VARCHAR(45) NULL,
  `pageId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `page_ind` (`pageId` ASC),
  CONSTRAINT `widget_page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  UNIQUE INDEX `name_uniq` (`name` ASC));

#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`priviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_unq` (`id` ASC),
  UNIQUE INDEX `name_unq` (`name`));

#########################################################################################	
	
CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`websiterole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `devId` INT NOT NULL,
  `webId` INT NOT NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `websiterole_developer_ind` (`devId` ASC),
  INDEX `websiterole_website_ind` (`webId` ASC),
  INDEX `websiterole_role_ind` (`roleId` ASC),
  CONSTRAINT `websiterole_developer`
    FOREIGN KEY (`devId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_website`
    FOREIGN KEY (`webId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websiterole_role`
    FOREIGN KEY (`roleId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  
#########################################################################################
  
CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`websitepriviledge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `devId` INT NOT NULL,
  `webId` INT NOT NULL,
  `priviledgeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `websitepriviledge_developer_ind` (`devId` ASC),
  INDEX `websitepriviledge_website_ind` (`webId` ASC),
  INDEX `websitepriviledge_priviledge_ind` (`priviledgeId` ASC),
  CONSTRAINT `websitepriviledge_developer`
    FOREIGN KEY (`devId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websitepriviledge_website`
    FOREIGN KEY (`webId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`website` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `websitepriviledge_priviledge`
    FOREIGN KEY (`priviledgeId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`priviledge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################

 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`pagepriviledge` (
`id` INT NOT NULL AUTO_INCREMENT,
  `devId` INT NOT NULL,
  `pageId` INT NOT NULL,
  `priviledgeId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `pagepriviledge_developer_ind` (`devId` ASC),
  INDEX `pagepriviledge_page_ind` (`pageId` ASC),
  INDEX `pagepriviledge_priviledge_ind` (`priviledgeId` ASC),
  CONSTRAINT `pagepriviledge_developer`
    FOREIGN KEY (`devId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagepriviledge_priviledge`
    FOREIGN KEY (`priviledgeId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`priviledge` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################
	
 CREATE TABLE `hw2_venkatesha_meghna_spring_2018`.`pagerole` (
`id` INT NOT NULL AUTO_INCREMENT,
  `devId` INT NOT NULL,
  `pageId` INT NOT NULL,
  `roleId` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_uniq` (`id` ASC),
  INDEX `pagerole_developer_ind` (`devId` ASC),
  INDEX `pagerole_page_ind` (`pageId` ASC),
  INDEX `pagerole_role_ind` (`roleId` ASC),
  CONSTRAINT `pagerole_developer`
    FOREIGN KEY (`devId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`developer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_page`
    FOREIGN KEY (`pageId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `pagerole_role`
    FOREIGN KEY (`roleId`)
    REFERENCES `hw2_venkatesha_meghna_spring_2018`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

#########################################################################################
	
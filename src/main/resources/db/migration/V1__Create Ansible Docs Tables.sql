CREATE TABLE `module_ref` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(150) NOT NULL,
  `module_description` varchar(1000) DEFAULT NULL,
  `ref_url` varchar(1500) DEFAULT NULL,
  `insert_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`module_id`),
  UNIQUE KEY `module_id_UNIQUE` (`module_id`),
  UNIQUE KEY `module_name_UNIQUE` (`module_name`)
);

CREATE TABLE `sub_module_ref` (
  `sub_module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `sub_module_name` varchar(45) NOT NULL,
  `sub_module_description` varchar(1000) DEFAULT NULL,
  `ref_url` varchar(1500) DEFAULT NULL,
  `insert_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`sub_module_id`),
  UNIQUE KEY `sub_module_id_UNIQUE` (`sub_module_id`),
  UNIQUE KEY `sub_module_name_UNIQUE` (`sub_module_name`,`module_id`),
  KEY `module_id_FKEY_ sub_module_ref_idx` (`module_id`),
  CONSTRAINT `module_id_FKEY_sub_module_ref` FOREIGN KEY (`module_id`) REFERENCES `module_ref` (`module_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `command_ref` (
  `command_ref_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `sub_module_id` int(11) DEFAULT NULL,
  `command` varchar(150) NOT NULL,
  `command_description` varchar(1000) DEFAULT NULL,
  `synopsis` varchar(5000) DEFAULT NULL,
  `requirements` varchar(5000) DEFAULT NULL,
  `ref_url` varchar(1500) DEFAULT NULL,
  `insert_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`command_ref_id`),
  UNIQUE KEY `command_ref_id_UNIQUE` (`command_ref_id`),
  UNIQUE KEY `command_UNIQUE` (`command`,`module_id`,`sub_module_id`),
  KEY `module_id_FKEY_command_ref_idx` (`module_id`),
  KEY `sub_module_id_FKEY_command_ref_idx` (`sub_module_id`),
  CONSTRAINT `module_id_FKEY_command_ref` FOREIGN KEY (`module_id`) REFERENCES `module_ref` (`module_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sub_module_id_FKEY_command_ref` FOREIGN KEY (`sub_module_id`) REFERENCES `sub_module_ref` (`sub_module_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `input_field_ref` (
  `input_field_id` int(11) NOT NULL AUTO_INCREMENT,
  `command_id` int(11) NOT NULL,
  `field_name` varchar(150) NOT NULL,
  `field_type` varchar(45) DEFAULT NULL,
  `field_description` varchar(15000) NOT NULL,
  `default_value` varchar(1000) DEFAULT NULL,
  `choices` varchar(1500) DEFAULT NULL,
  `ref_url` varchar(1500) DEFAULT NULL,
  `insert_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`input_field_id`),
  UNIQUE KEY `input_field_id_UNIQUE` (`input_field_id`),
  UNIQUE KEY `input_field_name_UNIQUE` (`field_name`,`command_id`),
  KEY `command_id_FKEY_ input_field_ref_idx` (`command_id`),
  CONSTRAINT `command_id_FKEY_input_field_ref` FOREIGN KEY (`command_id`) REFERENCES `command_ref` (`command_ref_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `output_field_ref` (
  `output_field_id` int(11) NOT NULL AUTO_INCREMENT,
  `command_id` int(11) NOT NULL,
  `parent_output_field_id` int(11) DEFAULT NULL,
  `field_name` varchar(150) NOT NULL,
  `field_type` varchar(45) DEFAULT NULL,
  `field_description` varchar(15000) NOT NULL,
  `returned_always` varchar(1) DEFAULT 'N',
  `ref_url` varchar(1500) DEFAULT NULL,
  `insert_timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`output_field_id`),
  UNIQUE KEY `output_field_id_UNIQUE` (`output_field_id`),
  UNIQUE KEY `output_field_name_UNIQUE` (`field_name`,`command_id`,`parent_output_field_id`),
  KEY `command_id_FKEY_output_field_ref_idx` (`command_id`),
  KEY `parent_output_field_id_FKEY_output_field_ref_idx` (`parent_output_field_id`),
  CONSTRAINT `command_id_FKEY_output_field_ref` FOREIGN KEY (`command_id`) REFERENCES `command_ref` (`command_ref_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parent_output_field_id_FKEY_output_field_ref` FOREIGN KEY (`parent_output_field_id`) REFERENCES `output_field_ref` (`output_field_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
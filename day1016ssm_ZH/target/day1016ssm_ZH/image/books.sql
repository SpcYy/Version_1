/*
Navicat MySQL Data Transfer

Source Server         : test1
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : xueyuan

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2018-10-16 08:45:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `books`
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(255) DEFAULT NULL,
  `Issuedcount` int(11) DEFAULT NULL,
  `issueddate` date DEFAULT NULL,
  `bookstate` varchar(255) DEFAULT NULL,
  `bookremark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------

-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `classid` int(11) NOT NULL AUTO_INCREMENT,
  `classnum` varchar(255) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `majorid` int(11) DEFAULT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `classbegin` date DEFAULT NULL,
  `classend` date DEFAULT NULL,
  `classcontent` varchar(255) DEFAULT NULL,
  `classqq` varchar(255) DEFAULT NULL,
  `tagline` varchar(255) DEFAULT NULL,
  `classteacher` varchar(11) DEFAULT NULL,
  `peoplecount` int(11) DEFAULT NULL,
  `classstate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', 'cla101', '1', '1', '矿产分析1班', '2017-10-01', '2018-10-01', 'aaaaabbbbccccddd', '1234567', 'dasds', 'user5', '50', '审核中');
INSERT INTO `classes` VALUES ('2', 'cla102', '1', '1', '矿产分析2班', '2006-01-01', '2007-01-01', 'bbbbb', '158683', 'dsadasda', '小红', '60', '审核中');
INSERT INTO `classes` VALUES ('3', '201', '1', '1', 'test1', '2013-10-10', '2013-10-10', 'fsdfdsfdsfsd', '11111', 'dadsfsdf', 'user4', '40', '已毕业');
INSERT INTO `classes` VALUES ('4', '202', '1', '1', 'aaa', '2013-10-10', '2014-10-10', 'fsdfsdfs', '3432423', 'fsf', 'user', '22', '审核通过');
INSERT INTO `classes` VALUES ('5', '301', '1', '1', 'www', '2013-10-10', '2015-10-10', 'fsfdsfdsfafasfa', '33333', 'dasdas', 'user5', '23', '未审核');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `departid` int(11) NOT NULL AUTO_INCREMENT,
  `departname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`departid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '矿冶工程学院');
INSERT INTO `department` VALUES ('2', '政治学院');
INSERT INTO `department` VALUES ('3', '计算机学院');

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `examid` int(11) DEFAULT NULL,
  `examnum` varchar(255) DEFAULT NULL,
  `examsubject` varchar(255) DEFAULT NULL,
  `examtime` date DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `majorid` int(11) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `examcount` int(11) DEFAULT NULL,
  `examstate` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------

-- ----------------------------
-- Table structure for `information`
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `informationid` int(11) NOT NULL AUTO_INCREMENT,
  `informationname` varchar(255) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `filetype` varchar(255) DEFAULT NULL,
  `uploader` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`informationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------

-- ----------------------------
-- Table structure for `infotype`
-- ----------------------------
DROP TABLE IF EXISTS `infotype`;
CREATE TABLE `infotype` (
  `infoid` int(11) NOT NULL AUTO_INCREMENT,
  `infotype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`infoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of infotype
-- ----------------------------

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `majorid` int(11) NOT NULL AUTO_INCREMENT,
  `majorname` varchar(255) DEFAULT NULL,
  `departid` int(11) DEFAULT NULL,
  PRIMARY KEY (`majorid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '矿产分析', '1');
INSERT INTO `major` VALUES ('2', '矿产挖掘', '1');
INSERT INTO `major` VALUES ('3', '矿产冶炼', '1');
INSERT INTO `major` VALUES ('4', '毛泽东思想', '2');
INSERT INTO `major` VALUES ('5', '邓小平理论', '2');
INSERT INTO `major` VALUES ('6', '习近平治国', '2');
INSERT INTO `major` VALUES ('7', 'java', '3');
INSERT INTO `major` VALUES ('8', 'h5', '3');
INSERT INTO `major` VALUES ('9', 'c', '3');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuid` int(11) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(255) DEFAULT NULL,
  `upmenuid` int(11) DEFAULT NULL,
  `menupath` varchar(255) DEFAULT NULL,
  `menustate` int(11) DEFAULT NULL,
  `menuremark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for `middle`
-- ----------------------------
DROP TABLE IF EXISTS `middle`;
CREATE TABLE `middle` (
  `middleid` int(11) NOT NULL DEFAULT '0',
  `menuid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`middleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of middle
-- ----------------------------

-- ----------------------------
-- Table structure for `problem`
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `problemid` int(11) DEFAULT NULL,
  `problemtype` varchar(255) DEFAULT NULL,
  `problemcontent` varchar(255) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of problem
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleid` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL,
  `rolestate` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '班主任', '1');
INSERT INTO `role` VALUES ('2', '讲师', '1');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `middleid` int(11) NOT NULL AUTO_INCREMENT,
  `examid` int(11) DEFAULT NULL,
  `stuid` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`middleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `studentid` int(11) NOT NULL AUTO_INCREMENT,
  `studentno` varchar(255) DEFAULT NULL,
  `stuname` varchar(255) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `majorid` int(11) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `stusex` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `registered` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `politics` varchar(255) DEFAULT NULL,
  `cardid` varchar(255) DEFAULT NULL,
  `stucontent` varchar(255) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  `stustate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('101', 's1001', '张三', null, null, '1', '1', '123@qq.com', '11234', '北京', '昌平区', null, null, null, null, null);
INSERT INTO `student` VALUES ('102', 's1002', '李四', null, null, '1', '1', '123@qq.com', '2345', '上海', '宝山区', null, null, null, null, null);
INSERT INTO `student` VALUES ('103', 's1003', '王五', null, null, '2', '0', '123@qq.com', '32324', '广州', '不知道', null, null, null, null, null);
INSERT INTO `student` VALUES ('104', 's1004', '赵柳', null, null, '2', '0', '123@qq.com', '432423', '深圳', '不知道', null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_class_major`
-- ----------------------------
DROP TABLE IF EXISTS `user_class_major`;
CREATE TABLE `user_class_major` (
  `user_class_major_id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `departid` int(11) DEFAULT NULL,
  `majorid` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_class_major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_class_major
-- ----------------------------
INSERT INTO `user_class_major` VALUES ('1', '1', '1', '1');
INSERT INTO `user_class_major` VALUES ('2', '2', '2', '2');
INSERT INTO `user_class_major` VALUES ('3', '3', '3', '3');
INSERT INTO `user_class_major` VALUES ('4', '4', '1', '1');
INSERT INTO `user_class_major` VALUES ('5', '5', '1', '1');

-- ----------------------------
-- Table structure for `user_tb`
-- ----------------------------
DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_ps` varchar(255) DEFAULT NULL,
  `user_realname` varchar(255) DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_idcard` varchar(255) DEFAULT NULL,
  `user_content` varchar(255) DEFAULT NULL,
  `logincount` int(11) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_tb
-- ----------------------------
INSERT INTO `user_tb` VALUES ('1', '101', '1', 'user', '456', '张三', '男', '123@qq.com', '1234567', '上海', '12221121212121', '一个大帅哥', '33', '2018-10-11');
INSERT INTO `user_tb` VALUES ('2', '102', '1', 'user2', '123', '李四', '女', null, null, null, null, null, null, null);
INSERT INTO `user_tb` VALUES ('3', '103', '1', 'user3', '111', null, null, null, null, null, null, null, null, null);
INSERT INTO `user_tb` VALUES ('4', '104', '1', 'user4', '222', null, null, null, null, null, null, null, null, null);
INSERT INTO `user_tb` VALUES ('5', '105', '1', 'user5', '333', null, null, null, null, null, null, null, null, null);

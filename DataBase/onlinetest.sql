/*
 Navicat Premium Data Transfer

 Source Server         : dasda
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : onlinetest

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 05/11/2023 14:14:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exams
-- ----------------------------
DROP TABLE IF EXISTS `exams`;
CREATE TABLE `exams`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `teacher_id` int NOT NULL,
  `end_time` datetime NOT NULL,
  `start_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exams
-- ----------------------------
INSERT INTO `exams` VALUES (1, 'java考试', 1457, '2023-11-05 14:02:29', '2023-11-10 14:02:35');
INSERT INTO `exams` VALUES (2, 'c语言考试', 6740, '2023-11-06 14:07:08', '2023-11-11 14:07:14');

-- ----------------------------
-- Table structure for question_exams
-- ----------------------------
DROP TABLE IF EXISTS `question_exams`;
CREATE TABLE `question_exams`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int NOT NULL,
  `exam_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question_exams
-- ----------------------------
INSERT INTO `question_exams` VALUES (1, 1, 1);
INSERT INTO `question_exams` VALUES (2, 2, 1);
INSERT INTO `question_exams` VALUES (3, 3, 1);
INSERT INTO `question_exams` VALUES (4, 1, 2);
INSERT INTO `question_exams` VALUES (5, 4, 1);
INSERT INTO `question_exams` VALUES (6, 5, 2);

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type` int NOT NULL DEFAULT 0,
  `opa` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `opb` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `opc` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `opd` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `correntAnswer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (1, 'It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud. Features in Navicat are sophisticated enough to provide professional developers for all their specific needs, yet easy to learn for users who are new                   ', 0, 'Creativity is intelligence having fun. You can select any connections, objects or                   ', 'Secure SHell (SSH) is a program to log in into another computer over a network, execute             ', 'In other words, Navicat provides the ability for data in different databases and/or                 ', 'After comparing data, the window shows the number of records that will be inserted,                 ', 'C');
INSERT INTO `questions` VALUES (2, 'Navicat authorizes you to make connection to remote servers running on different platforms (i.e. Windows, macOS, Linux and UNIX), and supports PAM and GSSAPI authentication.', 0, 'There is no way to happiness. Happiness is the way. The reason why a great man is                   ', 'To clear or reload various internal caches, flush tables, or acquire locks, control-click           ', 'A man is not old until regrets take the place of dreams. Secure Sockets Layer(SSL)                  ', 'Champions keep playing until they get it right. A comfort zone is a beautiful place,                ', 'D');
INSERT INTO `questions` VALUES (3, 'A comfort zone is a beautiful place, but nothing ever grows there. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration.', 0, 'The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server,                  ', 'To clear or reload various internal caches, flush tables, or acquire locks, control-click           ', 'All the Navicat Cloud objects are located under different projects. You can share                   ', 'Actually it is just in an idea when feel oneself can achieve and cannot achieve.                    ', 'A');
INSERT INTO `questions` VALUES (4, 'I will greet this day with love in my heart. It wasn’t raining when Noah built the ark. Navicat provides a wide range advanced features, such as compelling code editing capabilities, smart code-completion, SQL formatting, and more.', 0, 'Navicat Monitor requires a repository to store alerts and metrics for historical analysis.          ', 'Navicat 15 has added support for the system-wide dark mode. If opportunity doesn’t                ', 'Navicat Cloud could not connect and access your databases. By which it means, it                    ', 'If the plan doesn’t work, change the plan, but never the goal. I may not have gone                ', 'A');
INSERT INTO `questions` VALUES (5, 'I will greet this day with love in my heart. A man’s best friends are his ten fingers. I destroy my enemies when I make them my friends.', 0, 'If you wait, all that happens is you get older. In the Objects tab, you can use the                 ', 'To clear or reload various internal caches, flush tables, or acquire locks, control-click           ', 'The Navigation pane employs tree structure which allows you to take action upon the                 ', 'Anyone who has ever made anything of importance was disciplined. Navicat 15 has added               ', 'D');
INSERT INTO `questions` VALUES (6, 'I will greet this day with love in my heart. If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution.', 1, 'To open a query using an external editor, control-click it and select Open with External            ', 'If the plan doesn’t work, change the plan, but never the goal. Export Wizard allows               ', '', '     ', 'B');
INSERT INTO `questions` VALUES (7, 'If your Internet Service Provider (ISP) does not provide direct access to its server, Secure Tunneling Protocol (SSH) / HTTP is another solution.', 0, 'After logged in the Navicat Cloud feature, the Navigation pane will be divided into                 ', 'It can also manage cloud databases such as Amazon Redshift, Amazon RDS, Alibaba Cloud.              ', 'The past has no power over the present moment. Typically, it is employed as an encrypted            ', 'If the plan doesn’t work, change the plan, but never the goal. To successfully                    ', 'C');
INSERT INTO `questions` VALUES (8, 'A man’s best friends are his ten fingers. Anyone who has ever made anything of importance was disciplined. The past has no power over the present moment. A comfort zone is a beautiful place, but nothing ever grows there.', 0, 'Navicat Cloud could not connect and access your databases. By which it means, it                    ', 'To start working with your server in Navicat, you should first establish a connection               ', 'Remember that failure is an event, not a person. The reason why a great man is great                ', 'Instead of wondering when your next vacation is, maybe you should set up a life you                 ', 'A');
INSERT INTO `questions` VALUES (9, 'In the Objects tab, you can use the List List, Detail Detail and ER Diagram ER Diagram buttons to change the object view. If you wait, all that happens is you get older.', 0, 'To clear or reload various internal caches, flush tables, or acquire locks, control-click           ', 'All journeys have secret destinations of which the traveler is unaware.                             ', 'If you wait, all that happens is you get older. All journeys have secret destinations               ', 'It wasn’t raining when Noah built the ark. It wasn’t raining when Noah built the ark.           ', 'D');
INSERT INTO `questions` VALUES (10, 'To open a query using an external editor, control-click it and select Open with External Editor. You can set the file path of an external editor in Preferences. Navicat Data Modeler is a powerful and cost-effective database design tool                    ', 0, 'In a Telnet session, all communications, including username and password, are transmitted           ', 'You will succeed because most people are lazy. After logged in the Navicat Cloud                    ', 'I may not have gone where I intended to go, but I think I have ended up where I needed to be.       ', 'Navicat is a multi-connections Database Administration tool allowing you to connect                 ', 'B');

-- ----------------------------
-- Table structure for student_exam
-- ----------------------------
DROP TABLE IF EXISTS `student_exam`;
CREATE TABLE `student_exam`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `exam_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_exam
-- ----------------------------

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '123456',
  `class` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`id`, `student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES (1, '孔致远', '123456', 4, 24729);
INSERT INTO `students` VALUES (2, '方嘉伦', '123456', 1, 60928);
INSERT INTO `students` VALUES (3, '常安琪', '123456', 3, 1525);
INSERT INTO `students` VALUES (4, '谭秀英', '123456', 3, 35679);
INSERT INTO `students` VALUES (5, '姜晓明', '123456', 5, 10510);
INSERT INTO `students` VALUES (6, '宋晓明', '123456', 4, 42432);
INSERT INTO `students` VALUES (7, '马璐', '123456', 2, 26586);
INSERT INTO `students` VALUES (8, '周晓明', '123456', 5, 41373);
INSERT INTO `students` VALUES (9, '谢杰宏', '123456', 2, 23517);
INSERT INTO `students` VALUES (10, '陆秀英', '123456', 4, 87475);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `teacher_id` int NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '456789',
  PRIMARY KEY (`id`, `teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '廖震南', 1457, '456789');
INSERT INTO `teacher` VALUES (1, '阎震南', 6740, '456789');
INSERT INTO `teacher` VALUES (2, '侯致远', 1253, '456789');
INSERT INTO `teacher` VALUES (3, '邓睿', 4108, '456789');
INSERT INTO `teacher` VALUES (4, '唐震南', 1141, '456789');

SET FOREIGN_KEY_CHECKS = 1;

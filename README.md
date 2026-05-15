# 在线教育管理系统（后端）

## 项目简介

本项目是一个基于 Spring Boot + MyBatis + MySQL 开发的在线教育管理系统后端服务，采用前后端分离架构，主要面向在线教学、课程管理、在线考试和成绩统计等场景。

系统支持管理员、教师、学生三类角色，包含用户认证、课程管理、学生选课、课程资源、在线考试、自动评分、成绩统计、讨论互动等核心功能。

## 技术栈

- Spring Boot 3.3.0
- Spring Security
- JWT
- MyBatis
- MySQL
- PageHelper
- Knife4j / Swagger
- Lombok
- Maven
- 阿里云 OSS

## 核心功能

### 用户认证与权限控制

- 用户注册、登录
- JWT Token 生成与校验
- 基于 Spring Security 的接口鉴权
- 管理员、教师、学生三类角色权限控制

### 课程管理

- 教师发布课程
- 课程信息查询
- 课程资源管理
- 学生选课
- 学习进度展示

### 在线考试

- 教师创建考试
- 自动生成试卷
- 题目关联与试卷管理
- 学生在线答题
- 系统自动评分

### 成绩统计

- 按课程查询成绩
- 按学生查询成绩
- 按考试查询成绩
- 教师端成绩管理

### 讨论互动

- 课程讨论发布
- 评论回复
- 师生互动交流

## 项目亮点

1. 基于 Spring Security + JWT 实现无状态登录认证和角色权限控制。
2. 使用 MyBatis 编写多表关联 SQL，完成课程、考试、成绩等复杂业务查询。
3. 结合 PageHelper 实现分页查询，并处理多表关联导致的分页数据重复问题。
4. 实现在线考试自动组卷、学生提交答案、系统自动评分的完整流程。
5. 使用 Knife4j / Swagger 维护接口文档，方便前后端联调。
6. 项目采用 Controller、Service、Mapper 分层结构，代码职责清晰，便于维护和扩展。

## 运行环境

- JDK 21
- MySQL 8.x
- Maven 3.8+
- Redis：后期可以加
- Node.js：前端项目需要

## 运行说明

### 1. 克隆项目

```bash
git clone https://github.com/Sunny-clouds/Online-education-system.git
```

2. 导入数据库

将项目中的 eduspring.sql 导入 MySQL 数据库。

3. 修改配置

修改 application.properties 中的数据库连接信息：

#数据库基本配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/数据库名?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=你的密码
4. 启动后端服务
mvn spring-boot:run

默认访问地址：

http://localhost:8080

接口文档地址：

http://localhost:8080/doc.html
前端仓库
https://github.com/Sunny-clouds/vue-eduspring

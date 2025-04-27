# SpringBoot + MyBatisPlus 项目示例

这是一个使用 Spring Boot 和 MyBatis-Plus 构建的简单后端项目示例，实现了基本的用户管理功能，包括增删改查操作。

## 技术栈

- Spring Boot 2.7.5
- MyBatis-Plus 3.5.2
- H2 数据库（开发和测试环境）
- MySQL（生产环境可替换）
- Swagger / Knife4j（API文档）
- Lombok

## 项目结构

```
src/main/java/com/example/demo
├── common              // 通用类
│   ├── GlobalExceptionHandler.java  // 全局异常处理
│   └── Result.java     // 统一返回结果封装
├── config              // 配置类
│   ├── MybatisPlusConfig.java  // MyBatisPlus配置
│   └── SwaggerConfig.java  // Swagger配置
├── controller          // 控制器
│   └── UserController.java  // 用户控制器
├── entity              // 实体类
│   └── User.java       // 用户实体
├── mapper              // Mapper接口
│   └── UserMapper.java  // 用户Mapper
├── service             // 服务接口
│   ├── UserService.java  // 用户服务接口
│   └── impl            // 服务实现
│       └── UserServiceImpl.java  // 用户服务实现
└── DemoApplication.java  // 应用启动类
```

## 功能特点

- 基于MyBatisPlus实现基础的CRUD操作
- 使用H2内存数据库，便于快速开发和测试
- 统一的API返回格式
- 全局异常处理
- Swagger API文档
- 参数验证

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+

### 运行项目

1. 克隆项目到本地
   ```bash
   git clone https://github.com/yourusername/springboot-mybatisplus.git
   cd springboot-mybatisplus
   ```

2. 使用Maven编译并运行
   ```bash
   mvn clean package
   java -jar target/springboot-mybatisplus-0.0.1-SNAPSHOT.jar
   ```

   或者直接使用Maven运行
   ```bash
   mvn spring-boot:run
   ```

3. 访问API
   - 应用地址：http://localhost:8080
   - Swagger文档：http://localhost:8080/doc.html
   - H2控制台：http://localhost:8080/h2-console
     - JDBC URL: jdbc:h2:mem:testdb
     - 用户名: sa
     - 密码: 空

## API 接口说明

### 用户管理

| 方法   | URL                 | 描述         |
|------|---------------------|------------|
| GET  | /users              | 获取所有用户     |
| GET  | /users/page         | 分页查询用户     |
| GET  | /users/{id}         | 根据ID获取用户   |
| POST | /users              | 创建新用户      |
| PUT  | /users/{id}         | 更新用户       |
| DELETE | /users/{id}         | 删除用户       |

## 配置MySQL数据库

如需将H2替换为MySQL，修改`application.yml`中的数据库配置：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    username: root
    password: root
```

## 开发和调试

- 开发时可访问H2控制台查看和操作数据库
- 使用Swagger/Knife4j文档测试API接口
- 查看日志输出，了解SQL执行情况

## 扩展建议

- 添加Spring Security实现身份认证和授权
- 实现更复杂的业务逻辑和关联查询
- 添加Redis缓存提高性能
- 配置Docker和CI/CD流程 
## Shiro 的特点
<img src="https://shiro.apache.org/assets/images/ShiroFeatures.png"><br>
四个主要基石
 - **Authentication**：登录，验证用户的登录信息
 - **Authorization**：访问流程的控制，即用户登录成功后具有的角色与权限
 - **Session Management**：管理用户的会话
 - **Cryptography**：通过某种加密算法保证数据的安全

在不同的环境下 Shiro 也提供了支持，比如 WEB 应用，缓存，并发等。

## Shiro 的认证流程

 1. 通过 `SecurityUtils.getSubject()` 方法，获取当前 Subject 对象 
 2. 调用 `subject.isAuthenticated()` 方法，判断用户是否已经被认证，若没有认证，将用户信息（在 Controller 中获取前端表单中的数据）封装成 `UsernamePasswordToken` 对象
 3. 调用 `subject.login(token);` 方法执行认证动作，这个动作通常有相应的异常处理
 4. 自定义 `Realm`，获取到用户名，根据用户名获取到该用户的登录信息（密码等），返回给 Shiro
 5. 由 Shiro 完成用户信息的认证

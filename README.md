# FastEnum

2020.09.27 - 搭建项目框架。
* 统一数据格式
* 统一异常

2020.09.28 - 整合redis
* 读取枚举值时，先读取redis再读取数据库，再存入redis。
* 一直查询的数据为空，redis一直查询不到，会直接请求到数据库，造成缓存穿透。
    * 目前采取回种空值的方式解决此问题。

2020.09.29 - 增加eureka集群
* 开始接入时存在com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server错误
    * 由于默认开启了防止跨域攻击导致，给大家科普一下跨域定义：协议、域名、端口。
    * 解决方案：http.csrf().disable();
    * 后面介绍csrf

2020.09.30 - 增加ribbon
* 增加增加ribbon，封装了ribbon。封装后使用方式类似fegin（这里使用了fcall模块）后续会把该模块完善的更强大。目前仅支持get类型调用。
    
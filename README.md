# dic_manager
自己写的一个简单Web项目

包含JavaScript jQuery的简单用法
使用了两种AJAX连接servlet的语法，直接使用JavaScript + Ajax 和 jQuery + Ajax

js代码动态创建表格，每一行增加一个删除和修改按钮，动态为每一个按钮添加监听，闭包函数的形式实现监听

采用JDBC连接数据库，BaseData是连接类，连接数据库和关闭数据库函数，DicData继承BaseData，实现具体的数据库操作

添加 jackson 包，实现将具体类转换为 json 格式数据

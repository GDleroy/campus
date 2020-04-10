# campus
develope plan:

- Step one    - 产品设计 需求设计
- Step two    - UI设计
- Step three - 后端架构设计与搭建
- Step four   - 前端代码设计及开发

 




 1. git操作项目：

   ```
   a、cd github(github为要上传的文件目录)
   b、git init （要事先安装git工具）
   c、git add . (.不能忘记，add后面有个空格)（将项目添加到仓库）
   d、git commit -m "注释语句"  （将项目托管给仓库）
   e、到github上创建自己的Repository
   f、git remote add origin https://仓库的url地址  （将本地的仓库关联到github上）
   g、git push-u origin master （上传代码到github远程仓库）
   h、git pull --rebase origin master （如果第7步失败，报failed to push some refs，先执行第8步，再执行第7步。是因为本地仓库没有readme文件）
   另外：更新上传代码 第一步、git status  第二步：更新全部 git add *   第三步：接着输入git commit -m "更新说明"
   第四步：先git pull,拉取当前分支最新代码 git pull  第五步：push到远程master分支上 git push origin master
   ```
 2. 注意事项：
   ```
   如果aspect类报错， 缺少依赖
   <dependency>
   <groupId>aspectj</groupId>
   <artifactId>aspectjweaver</artifactId>
   <version>1.9.5</version>
   </dependency>
   ```

 

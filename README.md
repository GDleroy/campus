# campus

### 1、cd github(github为要上传的文件目录)
### 2、git init （要事先安装git工具）
### 3、git add . (.不能忘记，add后面有个空格)（将项目添加到仓库）
### 4、git commit -m "注释语句"  （将项目托管给仓库）
### 5、到github上创建自己的Repository
### 6、git remote add origin https://仓库的url地址  （将本地的仓库关联到github上）
### 7、git push-u origin master （上传代码到github远程仓库）
### 8、git pull --rebase origin master （如果第7步失败，报failed to push some refs，先执行第8步，再执行第7步。是因为本地仓库没有readme文件）
#### 更新上传代码 第一步、git status 第二步：更新全部 git add *  第三步：接着输入git commit -m "更新说明" git commit -m "更新说明" 
#### 第四步：先git pull,拉取当前分支最新代码 git pull 第五步：push到远程master分支上 git push origin master

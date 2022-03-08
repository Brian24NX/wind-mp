# 项目介绍

## 目录介绍
```
├─sql # 发布上线的脚本 根据 ${yyyyMMdd}_CR_XXX.sql | ${yyyyMMdd}_HOTFIX_XXX.sql 命名
├─YYY-XXX-api 供三方服务调用的api接口
├─YYY-XXX-client 调用三方的cliet接口
├─YYY-XXX-common 通用的工具类、常量等
├─YYY-XXX-dao 数据层
├─YYY-XXX-service 服务层
└─YYY-XXX-start 供展示层调用的接口
```

## 打包
```
#执行 
cd $workspace/YYY-XXX
mvn install
```
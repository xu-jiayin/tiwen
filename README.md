# 福建水利电力职业技术学院体温自动条包脚本（可部署到腾讯云云函数中，支持pushplus推送加推送功能）
---
## 使用教程
---
- 安装
  python <= 3.9

  模块 ddddocr	requests	pytz

  模块如何安装自行百度
  
- 配置脚本

  [福建水利电力职业技术学院-疫情防控管理平台 (fjsdxy.com)](http://xg.fjsdxy.com/SPCP/Web/Account/ChooseType)

  1. 输入学号密码登录平台
  2. 按F12 -->Network -->ChooseType 在Request Headers中复制cookie
  3. 将cookei粘贴至cookies.text中
  4. 关注pushplus推送加公众号获得token （需要向公众号发送“激活消息”）
  5. 在config.py中粘贴token
  
- 运行
  
  python index.py
  
  
  
  


# -*- coding: utf-8 -*-

import requests
import ddddocr
import re
import time
import datetime
import pytz
import random
import json
import config

# 数据接口地址
url = 'http://localhost:8082/tiwen/getUserInfo'
headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"}

# 参数校验方法
def isTrue(request,str):
    mo = r'[\u4e00-\u9fa5]+'
    s = request.text
    config.msg = re.findall(mo, s)
    # print(config.msg)
    key = str in config.msg
    return key

# 登录方法
def login():
    # 图片验证码接口地址
    code_url = 'http://xg.fjsdxy.com/SPCP/Web/Account/GetLoginVCode'
    # 登录接口地址
    login_url = 'http://xg.fjsdxy.com/SPCP/Web/'
    # 体温填报表单地址
    info_url = 'http://xg.fjsdxy.com/SPCP/Web/Temperature/StuTemperatureInfo'
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"}

    # 通过ocr获取图片验证码中的文字
    r = requests.get(url=code_url,timeout=5)
    ocr = ddddocr.DdddOcr()
    img_bytes = r.content
    res = ocr.classification(img_bytes)

    # 登录的表单信息
    login = {
        'ReSubmiteFlag': '4fc1bb12-1a5b-477e-b176-757e7050349a',
        'StuLoginMode': '1',
        'txtUid': config.userID,
        'txtPwd': config.passWord,
        'code': res
    }

    # 修改时区
    hour = datetime.datetime.now(pytz.timezone('Asia/Shanghai')).hour
    minute = datetime.datetime.now().minute
    t2 = random.randint(4,9)
    # 体温信息
    keywords = {
         'TimeNowHour': hour,
         'TimeNowMinute': minute,
         'Temper1':'36',
         'Temper2': t2,
        }

    session = requests.Session()
    session.post(url=login_url, headers=headers, data=login, cookies=requests.utils.dict_from_cookiejar(r.cookies))
    request = session.post(url=info_url, headers=headers, data=keywords)
    return request

def run():
    request = login()
    if isTrue(request,"请先登录"):
        print("验证码错误正在重试...")
        time.sleep(1)
        run()
    else:
        print("登录成功")


def push():
    run()
    print(config.msg)
    push_url = 'http://www.pushplus.plus/send'
    data = {
        "token" : config.token,
        "title" : "体温填报情况",
        "content" : config.msg,
        "template" : 'json'
    }
    body=json.dumps(data).encode(encoding='utf-8')
    headers = {'Content-Type':'application/json'}
    requests.post(url=push_url,data=body,headers=headers) 

def start():
    res = requests.get(url=url,headers=headers)
    text = res.text
    jsonobj = json.loads(text)
    print(jsonobj['data'])

    for item in jsonobj['data']:

        config.userID = item['userId']
        config.passWord = item['passWord']
        config.token = item['token']
        time.sleep(1)
        print(config.userID)
        login()
        push()

start()
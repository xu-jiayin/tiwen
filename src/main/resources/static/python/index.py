import requests
import ddddocr
import re
import time
import datetime
import pytz
import random
import json
import config

def isTrue(request,str):
    mo = r'[\u4e00-\u9fa5]+'
    s = request.text
    config.msg = re.findall(mo, s)
    # print(config.msg)
    key = str in config.msg
    return key

def login():
    code_url = 'http://xg.fjsdxy.com/SPCP/Web/Account/GetLoginVCode'
    login_url = 'http://xg.fjsdxy.com/SPCP/Web/'
    info_url = 'http://xg.fjsdxy.com/SPCP/Web/Temperature/StuTemperatureInfo'
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"}

    r = requests.get(url=code_url,timeout=5)
    ocr = ddddocr.DdddOcr()
    img_bytes = r.content
    res = ocr.classification(img_bytes)

    login = {
        'ReSubmiteFlag': '4fc1bb12-1a5b-477e-b176-757e7050349a',
        'StuLoginMode': '1',
        'txtUid': config.txtUid,
        'txtPwd': config.txtPwd,
        'code': res
    }

    hour = datetime.datetime.now(pytz.timezone('Asia/Shanghai')).hour
    minute = datetime.datetime.now().minute
    t2 = random.randint(4,9)
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
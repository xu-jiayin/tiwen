import requests
import json
import config
import re

from data import keywords

def isTrue(request,str):
    mo = r'[\u4e00-\u9fa5]+'
    s = request.text
    text = re.findall(mo, s)
    key = str in text
    return key

def addForm():
    f = open(r'cookies.txt', 'r')
    cookies = {}
    for line in f.read().split(';'):
        name, value = line.strip().split('=', 1)
        cookies[name] = value
    request = requests.post(url=config.url, cookies=cookies, data=keywords)
    return request

def run():
    request = addForm()
    if isTrue(request,'勿需再次填报'):
        return '今天填报次数已完成'
    if isTrue(request,'填报成功'):
        return '填报成功'
    if isTrue(request,'小时'):
        return '间隔不能小于5小时'


def main_handler(event,context):
    msg = run()
    data = {
    "token":config.token,
    "title":config.title,
    "content":msg,
    "template":'json'
    }
    body=json.dumps(data).encode(encoding='utf-8')
    headers = {'Content-Type':'application/json'}
    requests.post(config.pushurl,data=body,headers=headers)

    return msg
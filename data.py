import datetime
import pytz
import random

hour = datetime.datetime.now(pytz.timezone('Asia/Shanghai')).hour
minute = datetime.datetime.now().minute

# print(hour)
# print(minute)

t2 = random.randint(4,9)

keywords = {
         'TimeNowHour': hour,
         'TimeNowMinute': minute,
         'Temper1':'36',
         'Temper2': t2,
        }

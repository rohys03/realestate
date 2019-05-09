# python3 
import requests
import json
import pymysql
import datetime
from bs4 import BeautifulSoup

today = str(datetime.datetime.now().strftime('%Y%m%d%H%M%S'))
print(today)

conn = pymysql.connect(host='127.0.0.1', user='landcrawler', password='landcrawler',
            db='landcrawler', charset='utf8')

deleteSql = """delete From landcrawler.realestate_apt_complex where complexNo =%s"""

insertSql = """insert into landcrawler.realestate_apt_complex(complexNo, complexName, complexTypeName, totalHouseHoldCount, totalDongCount, constructYearMonth, minArea, maxArea)
         values (%s, %s, %s, %s, %s, %s, %s, %s)"""

def deleteComplexData (complexNo) :
    curs = conn.cursor()
    curs.execute(deleteSql, complexNo)
    conn.commit()

def insertComplexData (data) :
    curs = conn.cursor()
    curs.execute(insertSql, (
            data['complexNo']
        , data['complexName'].encode('utf-8')
        , data['complexTypeName'].encode('utf-8')
        , data['totalHouseHoldCount']
        , data['totalDongCount']
        , data['constructYearMonth'].encode('utf-8')
        , data['minArea']
        , data['maxArea']
        )
    )
    conn.commit()

complexNoList=[27355,102329,104917,111515,111964,867,22627,23759,11698,113292,107613,22463,22788,109379,110125,19127,15011,107240,104190,109910,219,659,664,669,22675,22570,22853,108064,108187,110656,101410,22522,104225,27064,111552,113302]
# complexNoList=[111515]

for complexNo in complexNoList:     
    print('Today: ', today, complexNo)
    url='https://new.land.naver.com/api/complexes/overview/' + str(complexNo)
    resp_data = requests.get(url)
    if resp_data.status_code == 200:
        print('complexNo: ' + str(complexNo))
        json_data=resp_data.text
        parsed_data = json.loads(json_data)
        deleteComplexData(complexNo)
        insertComplexData(parsed_data)

conn.close()


# python3 
# url='https://new.land.naver.com/api/complexes/overview/27355?complexNo=27355' 아파트 단지 정보.
# url='https://new.land.naver.com/api/articles/complex/27355?realEstateType=APT&tradeType=A1&showArticle=false&sameAddressGroup=true&priceType=RETAIL&page=1&complexNo=27355&type=list&order=rank'

import requests
import json
import pymysql
from datetime import date
from bs4 import BeautifulSoup
sql = """insert into naver_realestate(clct_dt, articleNo, articleName, tradeTypeName, floorInfo, dealOrWarrantPrc, areaName, area1, area2, buildingName)
         values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""

today = str(date.today().strftime('%Y%m%d'))

def printData (jsonList) :
    lineCnt=1
    for data in jsonList:
        conn = pymysql.connect(host='127.0.0.1', user='landcrawler', password='landcrawler',
                        db='landcrawler', charset='utf8')
        curs = conn.cursor()
        itemCnt=20 * ( pageCnt - 1) + lineCnt
        print(str(itemCnt) + ': ' , today, data['articleNo'], data['articleName'], data['tradeTypeName'], data['floorInfo'], data['dealOrWarrantPrc'], data['areaName'], data['area1'], data['area2'], data['buildingName'])
        curs.execute(sql, (
              today
            , data['articleNo']
            , data['articleName'].encode('utf-8')
            , data['tradeTypeName'].encode('utf-8')
            , data['floorInfo'].encode('utf-8')
            , data['dealOrWarrantPrc'].encode('utf-8')
            , data['areaName'].encode('utf-8')
            , data['area1']
            , data['area2']
            , data['buildingName'].encode('utf-8')
            )
        )
        conn.commit()
        lineCnt+=1
    conn.close()

complexNoList=[27355,102329,104917,111515,111964,867,22627,23759,11698,113292,107613,22463,22788,109379,110125,19127,15011,107240,104190,109910,219,659,664,669,22675,22570,22853,108064,108187]
# complexNoList=[111515]


tradeTypeList=['A1','B1']       # A1 : 매매, B1 : 전세, C1 : 월세

for complexNo in complexNoList:
    for tradeType in tradeTypeList:        
        print('Today: ', today, complexNo, tradeType)
        moreData=True
        pageCnt=1
        while bool(moreData) :                
                # print('!!! page=' + str(pageCnt))
                url='https://new.land.naver.com/api/articles/complex/' + str(complexNo) + '?realEstateType=APT&tradeType=' + str(tradeType) + '&showArticle=false&sameAddressGroup=true&priceType=RETAIL&page=' + str(pageCnt) + '&complexNo=' + str(complexNo) + '&type=list&order=rank'
                resp_data = requests.get(url)
                json_data=resp_data.text
                parsed_data = json.loads(json_data)
                articleList=parsed_data['articleList']
                #     print("Resp_data: ", resp_data.status_code, resp_data.headers['content-type'], resp_data.encoding)
                # print(url)
                printData(articleList)
                moreData=parsed_data['isMoreData']
                print('MoreData: '+ str(moreData), 'Page: ' + str(pageCnt))
                pageCnt+=1


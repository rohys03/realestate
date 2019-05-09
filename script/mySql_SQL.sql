mysql -h 127.0.0.1 -u landcrawler -p landcrawler
mysql -h 127.0.0.1 -u root -p root

create user 'landcrawler'@'%' identified by 'landcrawler';
grant all privileges on *.* to 'landcrawler'@'%';

create database landcrawler;

drop table landcrawler.naver_realestate;
create table landcrawler.naver_realestate (
    clct_dt varchar(8)
, articleNo bigint not null
, articleName varchar(500)
, tradeTypeName varchar(500)
, floorInfo varchar(500)
, dealOrWarrantPrc varchar(500)
, areaName varchar(500)
, area1 float
, area2 float
, buildingName varchar(500)
);


# SQL script
Select * From mysql.user;
/


select 
	clct_dt, complexName
	, area2
	, sum(case tradeTypeName when '매매' then cnt end ) as PURCHASE
	, sum(case tradeTypeName when '전세' then cnt end ) as RENT
	, sum(case tradeTypeName when '매매' then discint_Cnt end ) as DISTINCT_PURCHASE
	, sum(case tradeTypeName when '전세' then discint_Cnt end ) as DISTINCT_RENT
	, sum(case tradeTypeName when '매매' then avgPrc end ) as Avg_PURCHASE
	, sum(case tradeTypeName when '전세' then avgPrc end ) as Avg_RENT
From (
	Select 
		complexName, clct_dt, tradeTypeName
		, area2
		, count(*) Cnt
		, avg(prc) avgPrc
		, count(distinct clct_dt, floorInfo, tradeTypeName, dealOrWarrantPrc,AreaName, buildingName) discint_Cnt
	From (
select a.complexName, b.*
, cast((substr(dealOrWarrantPrc,1, instr(dealOrWarrantPrc, '억')- 1) * 10000) as UNSIGNED) + cast(replace(substr(dealOrWarrantPrc, instr(dealOrWarrantPrc, '억') + 1), ',' ,'') as UNSIGNED) prc
From realestate_apt_complex a, realestate_article_link_esxi b
where 1=1
-- and b.clct_dt >= '20190120'
and a.complexName like '%헬리오%'
and a.complexNo = b.complexNo	
	) B
	Where 1=1
	-- and tradeTypeName = '전세'
    and area2 = 84
	group by complexName, CLCT_DT, tradeTypeName, area2
	) dat
Group by clct_dt, complexName, area2
order by clct_dt desc, complexName, area2 desc;


select a.complexName, b.*
, cast((substr(dealOrWarrantPrc,1, instr(dealOrWarrantPrc, '억')- 1) * 10000) as UNSIGNED) + cast(replace(substr(dealOrWarrantPrc, instr(dealOrWarrantPrc, '억') + 1), ',' ,'') as UNSIGNED) prc
From realestate_apt_complex a, realestate_article_link_esxi b
where a.complexName like '%엘스%'
and a.complexNo = b.complexNo	
	
select * From realestate_article_link_esxi where complexNo = 111515 and clct_dt = '20190201';


select * From realestate_apt_complex order by complexNo;

select * From realestate_apt_complex where instr(complexName '헬리') > 0;

select count(*) , clct_Dt from realestate_article_link_esxi where clct_dt in ('20190123', '20190124', '20190125')
and complexNo in ('11698')
group by clct_dt order by clct_dt desc;
SELECT * FROM TABLE_NAME

SELECT * FROM realestate_apt_complex
WHERE complexName LIKE CONCAT('%', #{searchKeyword}, '%');

use landcrawler;
drop table realestate_apt_complex;

create table realestate_apt_complex (
complexNo int not null primary key ,
complexName varchar(500) not null,
complexTypeName varchar(500),
totalHouseHoldCount int,
totalDongCount int,
constructYearMonth varchar(20),
minArea float,
maxArea float
);

rollback;

select * From realestate_apt_complex;

insert into realestate_apt_complex values(109379, '경희궁자이2단지');
insert into realestate_apt_complex values(110125, '경희궁자이3단지');
insert into realestate_apt_complex values(11698, '도곡렉슬');
insert into realestate_apt_complex values(23759, '래미안퍼스티지');
insert into realestate_apt_complex values(22463, '롯데캐슬루나');
insert into realestate_apt_complex values(104917, '마포래미안푸르지오');
insert into realestate_apt_complex values(111515, '송파헬리오시티(가락시영)');
insert into realestate_apt_complex values(107613, '아크로리버파크');
insert into realestate_apt_complex values(113292, '아크로리버하임');
insert into realestate_apt_complex values(27355, '용산e-편한세상');
insert into realestate_apt_complex values(102329, '용산더프라임');
insert into realestate_apt_complex values(22788, '인왕산현대아이파크');
insert into realestate_apt_complex values(22627, '잠실엘스');
insert into realestate_apt_complex values(867, '한가람');
insert into realestate_apt_complex values(111964, '힐스테이트녹번');

commit;

select * from realestate_apt_complex;

use landcrawler;

drop table landcrawler.realestate_article;

create table landcrawler.realestate_article (
    clctDt varchar(8)
, articleNo bigint not null
, tradeTypeName varchar(500)
, floorInfo varchar(500)
, dealOrWarrantPrc varchar(500)
, areaName varchar(500)
, area1 float
, area2 float
, buildingName varchar(500)
);

alter table realestate_article add constraint pk_realestate_article primary key (clctDt, articleNo);




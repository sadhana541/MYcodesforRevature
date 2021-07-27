select * from myproducts ;
select name,price from myproducts 
group by name
having avg(price)>3000
order by price desc ;










 
select  name,count(productid)count from myproducts 
group by name 
having count(productid)<2
order by count desc;

select productid,name,price,rating from myproducts  where rating=(select min(rating) from myproducts);
select productid,name,price from myproducts  where price>(select avg(price) from myproducts);
select productid,name,price,rating from myproducts  where rating<(select avg(rating) from myproducts);
select * from myproducts ;
select productid,name,price,rating from myproducts  where name like 'f%';

-- print product with min rating
-- print products which are above the avg cost of the total products
-- print products which are having ratings below average ratings
-- explore set operations like (union,unionall,intersect)
-- explore like operator


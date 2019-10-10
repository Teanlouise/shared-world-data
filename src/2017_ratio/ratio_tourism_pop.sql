-- QUERY 3 - Tourists to Local Ratio
    -- Used to create table '2017' for map on home in frontend

SELECT tourism.country_name, 
    tourism.year, 
    tourism.value AS tourism_value, 
    pop.value AS pop_value, 
    ROUND((tourism.value/pop.value)*100, 0) as tourist_pop_percentage, 
    ROUND((tourism.value/pop.value), 2) as tourist_per_local
FROM `shared-world.tourism_regression.international_tourism` as tourism, 
    `shared-world.tourism_regression.population_total` as pop
WHERE tourism.country_name=pop.country_name 
    AND tourism.year=pop.year
ORDER BY tourism.country_name, tourism.year



-- QUERY 3 - View of all countries that have all of the factors from QUERY 2 views
  -- Creates 'regression_table' for machine learning

SELECT pop.country_name, pop.year,          
        ROUND(pop.value, 2) AS pop_thous, 
        ROUND(gdp.value, 2) AS gdp_US_mil, 
        homicides.value AS homicides_per_100_000,
        ROUND(life_expectancy.value, 2) AS life_expectancy_years,
        ROUND(unemployment.value, 2) AS unemployment_per,        
        departures.value AS departures_thous,
        ROUND(tourism_expenditures.value, 2) AS tourism_expenditures_US_mil,
        ROUND(arrivals.value, 2) AS arrivals_thous,        
        ROUND(tourism_receipts.value, 2) AS tourism_receipts_US_mil       
        
FROM `shared-world.tourism_regression.population_total` as pop,
      `shared-world.tourism_regression.GDP` as gdp,
      `shared-world.tourism_regression.homicides` as homicides,
      `shared-world.tourism_regression.life_expectancy` as life_expectancy,
      `shared-world.tourism_regression.unemployment` as unemployment,
      `shared-world.tourism_regression.tourism_departures` as departures,
      `shared-world.tourism_regression.tourism_expenditures` as tourism_expenditures,
      `shared-world.tourism_regression.international_tourism` as arrivals,
      `shared-world.tourism_regression.tourism_receipts` as tourism_receipts
 
      
WHERE pop.country_name=gdp.country_name AND pop.year=gdp.year
  AND pop.country_name=homicides.country_name AND pop.year=homicides.year
  AND pop.country_name=life_expectancy.country_name AND pop.year=life_expectancy.year   
  AND pop.country_name=unemployment.country_name AND pop.year=unemployment.year
  AND pop.country_name=departures.country_name AND pop.year=departures.year
  AND pop.country_name=tourism_expenditures.country_name AND pop.year=tourism_expenditures.year
  AND pop.country_name=arrivals.country_name AND pop.year=arrivals.year
  AND pop.country_name=tourism_receipts.country_name AND pop.year=tourism_receipts.year
  
  
ORDER BY pop.country_name, pop.year
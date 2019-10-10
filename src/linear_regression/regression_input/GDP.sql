-- QUERY 2 - GDP
    -- All countries from QUERY 1 with this factor

SELECT *
FROM `bigquery-public-data.world_bank_wdi.indicators_data` 
WHERE indicator_name="GDP (current US$)"
ORDER BY country_name, year
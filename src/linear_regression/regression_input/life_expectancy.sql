-- QUERY 2 - Life_Expectancy
    -- All countries from QUERY 1 with this factor

SELECT * 
FROM `shared-world.tourism_regression.country_data`
WHERE indicator_name="Life expectancy at birth, total (years)"
ORDER BY country_name, year
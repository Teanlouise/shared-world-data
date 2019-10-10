-- QUERY 2 - unemployment
    -- All countries from QUERY 1 with this factor

SELECT * 
FROM `shared-world.tourism_regression.country_data`
WHERE indicator_name="Unemployment, total (% of total labor force) (modeled ILO estimate)"
ORDER BY country_name, year
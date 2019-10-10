-- QUERY 2 - Homicides
    -- All countries from QUERY 1 with this factor

SELECT * 
FROM `shared-world.tourism_regression.country_data` 
WHERE indicator_name="Intentional homicides (per 100,000 people)"
ORDER BY country_name, year
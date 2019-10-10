-- QUERY 2 - Tourism Departures
    -- All countries from QUERY 1 with this factor

SELECT * 
FROM `shared-world.tourism_regression.country_data` 
WHERE indicator_name="International tourism, number of departures"
ORDER BY country_name, year
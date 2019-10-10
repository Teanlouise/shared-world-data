-- QUERY 2 - Tourism Receipts
    -- All countries from QUERY 1 with this factor

SELECT * 
FROM `shared-world.tourism_regression.country_data` 
WHERE indicator_name="International tourism, receipts (current US$)"
ORDER BY country_name, year
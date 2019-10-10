-- QUERY 2 - tourism
    -- All countries from world bank indicators with this factor
    -- Used in regression_input query AND for 2017 tourist-local-ratio

SELECT * 
FROM `bigquery-public-data.world_bank_wdi.indicators_data` 
WHERE indicator_name="International tourism, number of arrivals" 
ORDER BY country_name, year
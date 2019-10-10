-- QUERY 2 - Population
    -- All countries from world bank indicators with this factor
    -- Used in regression_input query AND for 2017 tourist-local-ratio

SELECT * FROM `bigquery-public-data.world_bank_wdi.indicators_data` 
WHERE indicator_name="Population, total" 
ORDER BY country_name, year
-- QUERY 1
    -- From 'World Bank - Indicators' public dataset on BigQuery
    -- View of all countries with indicators that match chosen factors

SELECT *
FROM `bigquery-public-data.world_bank_wdi.indicators_data` 
WHERE 
  indicator_name="International tourism, number of arrivals" 
  OR indicator_name="Population, total" 
  OR indicator_name="GDP (current US$)"
  OR indicator_name="GDP per capita (current US$)"
  OR indicator_name="International tourism, receipts (current US$)"
  OR indicator_name="Intentional homicides (per 100,000 people)"
  OR indicator_name="International tourism, number of departures"
  OR indicator_name="International tourism, expenditures (current US$)"
  OR indicator_name="Unemployment, total (% of total labor force) (modeled ILO estimate)"
  OR indicator_name="Poverty headcount ratio at national poverty lines (% of population)"
  OR indicator_name="Life expectancy at birth, total (years)"
  
  ORDER BY country_name, year 
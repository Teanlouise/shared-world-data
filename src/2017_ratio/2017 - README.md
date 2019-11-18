# TOURIST-TO-RESIDENT RATIO 
(BigQuery, GoogleMapsAPI -GeoChart)

*https://www.un.org/esa/sustdev/natlinfo/indicators/methodology_sheets/demographics/ratio_localresidents_tourists.pdf*
*https://data.worldbank.org/indicator/SP.POP.TOTL*

Use BigQuery to query World Bank public dataset and calculate the tourist-to-local ratio to be displayed on the homepage of the shared-world app.

- Ratio: (tourist arrivals / population)
- Percentage: Ratio * 100

### STEPS    
1. population.sql - Get the "Populaion, total" from the indicator data for all countries and years available.
2. tourism.sql - Get the "International tourism, number of arrivals" from the indicator data for all countries and years available.
3. ratio_tourism_pop.sql - Join these two views for all countries that have both data and create a column for the percentage and the 1 local to resident ratio. 
4. Create a table using this view and filter by the latest year (2017) and order by the highest percentage in descending.
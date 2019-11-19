[Overview](https://teanlouise.github.io/shared-world)     |     [Develop](https://teanlouise.github.io/shared-world/develop)    |  [Deploy](https://teanlouise.github.io/shared-world/deploy)    |   [Data](https://teanlouise.github.io/shared-world-data)

# TOURIST-TO-RESIDENT RATIO 

![tourist_map](https://user-images.githubusercontent.com/19520346/69103727-e2df5e00-0ab1-11ea-9223-63bd3d42d1a0.png)

_World Bank, Google BigQuery, Google Cloud Storage, GoogleMapsAPI-GeoChart_

Use BigQuery to query [World Bank public dataset](https://data.worldbank.org/indicator/SP.POP.TOTL) and calculate the [tourist-to-local ratio](https://www.un.org/esa/sustdev/natlinfo/indicators/methodology_sheets/demographics/ratio_localresidents_tourists.pdf) for all countries in 2017 to be displayed on the homepage of the shared-world app using Google Maps API.

- Ratio: (tourist arrivals / population)
- Percentage: Ratio * 100

### Steps  

**(1) Get Ratio from WorldBank using BigQuery:**
- Get the "Populaion, total" from the indicator data for all countries and years available.
- Get the "International tourism, number of arrivals" from the indicator data for all countries and years available.
- Join these two views for all countries that have both data and create a column for the percentage and the 1 local to resident ratio.

**(2) Create and save table on Cloud Storage:**
- Create a table using ratio view and filter by the latest year (2017) and order by the highest percentage in descending.
- Save table by exporting as csv to Cloud Storage

**(3) Display with GeoChart Map:**
- Enable GeoChartAPI 
- Enter data in [Google GeoChart in React](https://react-google-charts.com/data-sources/from-api)
- Display [GeoChart](https://developers.google.com/chart/interactive/docs/gallery/geochart) map with countries coloured according to ratio

![ratiooutput](https://user-images.githubusercontent.com/19520346/69106523-5dac7700-0aba-11ea-9d28-c799cfe8ab68.png)
_snippet_
